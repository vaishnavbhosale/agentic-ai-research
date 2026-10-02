# Experiment Specification — Student Management REST API (Frozen)

This specification is shared **unchanged** with all three experimental approaches:

1. Direct LLM
2. ReAct Agent
3. Multi-Agent System

Do not modify this specification per-approach. The evaluation test suite will be created separately and must not be changed by an experimental implementation.

---

## STUDENT MANAGEMENT REST API

### Technology

- Java 21
- Spring Boot 4.1.1
- Maven
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- H2
- JUnit / Spring Boot testing

### Entity: Student

Fields:

- `id`: Long, generated automatically
- `name`: String, required and not blank
- `email`: String, required and valid email format
- `age`: Integer, required, minimum 18, maximum 100

### REST endpoints

#### 1. POST /students

Creates a student.

Expected behavior:

- Valid request creates a student.
- Return HTTP 201 Created.
- Return the created student including its generated ID.
- Invalid name/email/age must be rejected.

#### 2. GET /students

Returns all students.

Expected behavior:

- HTTP 200 OK.
- Return a JSON array.
- Empty database returns an empty array.

#### 3. GET /students/{id}

Returns one student.

Expected behavior:

- Existing ID → HTTP 200 OK.
- Nonexistent ID → HTTP 404 Not Found.

#### 4. PUT /students/{id}

Updates an existing student.

Expected behavior:

- Existing ID + valid data → HTTP 200 OK.
- Nonexistent ID → HTTP 404 Not Found.
- Invalid data → appropriate validation error.

#### 5. DELETE /students/{id}

Deletes a student.

Expected behavior:

- Existing ID → HTTP 204 No Content.
- Nonexistent ID → HTTP 404 Not Found.

### Validation

- `name` must not be blank
- `email` must be a valid email address
- `age` must be between 18 and 100 inclusive

### Architecture guidance

The implementation should use a sensible Spring Boot architecture, but the specification must NOT prescribe:

- exact package structure
- exact class names
- exact service/repository architecture
- exact exception-handling implementation
- exact JSON error format

The purpose is to evaluate how each approach independently solves the same requirements.

---

## EXPERIMENTAL CONSTRAINTS

All three approaches must receive exactly the same task specification.

The evaluation test suite will be created separately and must not be changed by an experimental implementation.

Experimental implementations MUST NOT:

- modify evaluation tests
- weaken validation requirements
- remove requirements
- hard-code test-specific responses
- skip tests
- disable tests
- alter the evaluation criteria

The evaluation criteria will be defined independently of the agents.
