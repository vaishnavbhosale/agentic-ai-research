package com.agenticai.studentmanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * FIXED evaluation test suite for the Student Management REST API.
 *
 * <p>Copy this file unchanged into
 * {@code src/test/java/com/agenticai/studentmanagement/} of each experimental
 * implementation (Direct LLM, ReAct, Multi-Agent) and run with
 * {@code mvn -B test}. Do not modify it per-approach.
 *
 * <p>Design notes:
 * <ul>
 *   <li>HTTP-level only: exercises {@code /students} via {@link TestRestTemplate}.
 *       No reference to Student entity / repository / service / controller class names.</li>
 *   <li>Uses {@code Map} / {@code List} payloads, not implementation DTOs.</li>
 *   <li>Each test creates its own data with a random e-mail, so no execution-order
 *       dependency and no assumption about empty DB row counts.</li>
 *   <li>Validation failures assert {@code 4xx} (not an exact code or error-body
 *       schema) because the spec leaves error formatting open.</li>
 * </ul>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentApiEvaluationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final long NON_EXISTENT_ID = 999999999L;

    private String uniqueEmail() {
        return "student-" + UUID.randomUUID() + "@example.com";
    }

    private Map<String, Object> payload(String name, String email, int age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("age", age);
        return map;
    }

    private HttpEntity<Map<String, Object>> jsonEntity(Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    private Long createStudent(String name, String email, int age) {
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "/students", jsonEntity(payload(name, email, age)), Map.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                "setup: expected 201 when creating fixture student");
        assertNotNull(response.getBody(), "setup: create response body must not be null");
        assertNotNull(response.getBody().get("id"), "setup: created student must contain generated id");
        return ((Number) response.getBody().get("id")).longValue();
    }

    // ---- POST /students ----

    @Test
    void postValidStudent_returns201AndGeneratedId() {
        String email = uniqueEmail();
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "/students", jsonEntity(payload("Alice", email, 20)), Map.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("id"), "created student must include generated ID");
        assertEquals("Alice", response.getBody().get("name"));
        assertEquals(email, response.getBody().get("email"));
        assertEquals(20, ((Number) response.getBody().get("age")).intValue());
    }

    @Test
    void postInvalidEmail_returns4xx() {
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "/students", jsonEntity(payload("Bob", "not-an-email", 22)), Map.class);

        assertTrue(response.getStatusCode().is4xxClientError(),
                "invalid email must be rejected with 4xx, got: " + response.getStatusCode());
    }

    @Test
    void postBlankName_returns4xx() {
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "/students", jsonEntity(payload("   ", uniqueEmail(), 22)), Map.class);

        assertTrue(response.getStatusCode().is4xxClientError(),
                "blank name must be rejected with 4xx, got: " + response.getStatusCode());
    }

    @Test
    void postAgeBelow18_returns4xx() {
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "/students", jsonEntity(payload("Charlie", uniqueEmail(), 16)), Map.class);

        assertTrue(response.getStatusCode().is4xxClientError(),
                "age below 18 must be rejected with 4xx, got: " + response.getStatusCode());
    }

    // ---- GET /students ----

    @Test
    void getAllStudents_returns200AndJsonArray() {
        // Create one fixture so the array contains at least one known element,
        // without assuming the DB was empty before this test.
        String email = uniqueEmail();
        createStudent("Dave", email, 25);

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                "/students", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody(), "GET /students must return a JSON array");
        assertTrue(response.getBody().stream().anyMatch(s -> email.equals(s.get("email"))),
                "GET /students array must contain the just-created student");
    }

    @Test
    void getExistingStudent_returns200AndCorrectData() {
        String email = uniqueEmail();
        Long id = createStudent("Eve", email, 30);

        ResponseEntity<Map> response = restTemplate.getForEntity("/students/" + id, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Eve", response.getBody().get("name"));
        assertEquals(email, response.getBody().get("email"));
        assertEquals(30, ((Number) response.getBody().get("age")).intValue());
    }

    @Test
    void getNonexistentStudent_returns404() {
        ResponseEntity<Map> response =
                restTemplate.getForEntity("/students/" + NON_EXISTENT_ID, Map.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // ---- PUT /students/{id} ----

    @Test
    void putValidUpdate_returns200AndUpdatedData() {
        Long id = createStudent("Frank", uniqueEmail(), 22);
        String updatedEmail = uniqueEmail();

        ResponseEntity<Map> response = restTemplate.exchange(
                "/students/" + id, HttpMethod.PUT,
                jsonEntity(payload("Frank Updated", updatedEmail, 28)),
                Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Frank Updated", response.getBody().get("name"));
        assertEquals(updatedEmail, response.getBody().get("email"));
        assertEquals(28, ((Number) response.getBody().get("age")).intValue());

        // Confirm persistence via GET.
        ResponseEntity<Map> fetched = restTemplate.getForEntity("/students/" + id, Map.class);
        assertEquals(HttpStatus.OK, fetched.getStatusCode());
        assertEquals("Frank Updated", fetched.getBody().get("name"));
    }

    @Test
    void putNonexistentStudent_returns404() {
        ResponseEntity<Map> response = restTemplate.exchange(
                "/students/" + NON_EXISTENT_ID, HttpMethod.PUT,
                jsonEntity(payload("Ghost", uniqueEmail(), 30)),
                Map.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void putInvalidData_returns4xx() {
        Long id = createStudent("Grace", uniqueEmail(), 24);

        ResponseEntity<Map> response = restTemplate.exchange(
                "/students/" + id, HttpMethod.PUT,
                jsonEntity(payload("Grace", "bad-email", 24)),
                Map.class);

        assertTrue(response.getStatusCode().is4xxClientError(),
                "invalid update data must be rejected with 4xx, got: " + response.getStatusCode());
    }

    // ---- DELETE /students/{id} ----

    @Test
    void deleteExistingStudent_returns204() {
        Long id = createStudent("Heidi", uniqueEmail(), 26);

        ResponseEntity<Void> response = restTemplate.exchange(
                "/students/" + id, HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        ResponseEntity<Map> fetched =
                restTemplate.getForEntity("/students/" + id, Map.class);
        assertEquals(HttpStatus.NOT_FOUND, fetched.getStatusCode(),
                "deleted student must no longer be found");
    }

    @Test
    void deleteNonexistentStudent_returns404() {
        ResponseEntity<Void> response = restTemplate.exchange(
                "/students/" + NON_EXISTENT_ID, HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
