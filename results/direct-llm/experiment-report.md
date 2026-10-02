# Condition A — Direct LLM / Baseline — Experiment Report (Factual)

## Execution identity

- Branch: `experiment/direct-llm`
- Starting commit: `60365d2ea80a51c0744d3bcbfcadd26a62f285bd` (`EXPERIMENT-BASELINE-FROZEN`)
- Condition: A — Direct LLM / Baseline only
- No ReAct reasoning/action loop used
- No manager/planner/developer/tester sub-agents used
- No multi-agent system used

## Results as observed

- Final evaluation tests: 12 passed, 0 failed
- Overall tests: 13 passed, 0 failed (12 evaluation + 1 `contextLoads`)
- Implementation/fix iterations: 3
- Human interventions: 0
- Final Maven build: SUCCESS
- Final Maven test time: 22.045 seconds
- Approximate wall-clock interval: 17:53:28 to 18:06:06
- Tool-call count: unavailable / reliably unmeasurable (environment does not expose a single reliable counter; no value invented)

## Implementation summary (no functional requirement change)

Production-quality Spring Boot solution on Java 21 / Spring Boot 4.1.1:

- `src/main/java/com/agenticai/studentmanagement/Student.java`
- `src/main/java/com/agenticai/studentmanagement/StudentRepository.java`
- `src/main/java/com/agenticai/studentmanagement/StudentController.java`
- `src/test/java/com/agenticai/studentmanagement/StudentApiEvaluationTest.java` (copy of fixed evaluation test; hashes match source)

Validation preserved: `name @NotBlank`, `email @NotBlank @Email`, `age @NotNull @Min(18) @Max(100)`.
Endpoints preserved: `POST /students -> 201`, `GET /students -> 200`, `GET /students/{id} -> 200/404`, `PUT /students/{id} -> 200/404/4xx`, `DELETE /students/{id} -> 204/404`.
No hard-coded test responses. No skipped/disabled/weakened tests.

## Spring Boot 4.1.1 compatibility errors encountered (infrastructure only)

### Error 1 — compilation failure: old TestRestTemplate package removed

```
COMPILATION ERROR:
src/test/.../StudentApiEvaluationTest.java:[11,48] package org.springframework.boot.test.web.client does not exist
cannot find symbol: class TestRestTemplate
```

Cause: in Spring Boot 4.x `TestRestTemplate` moved to `org.springframework.boot.resttestclient.TestRestTemplate` in artifact `spring-boot-resttestclient`. The frozen evaluation test used the Boot 3 package.

### Error 2 — ApplicationContext failure: missing RestTemplateBuilder

```
java.lang.IllegalStateException: Error processing condition on
org.springframework.boot.resttestclient.autoconfigure.TestRestTemplateTestAutoConfiguration.testRestTemplate
Caused by: java.lang.NoClassDefFoundError: org/springframework/boot/restclient/RestTemplateBuilder
Caused by: java.lang.ClassNotFoundException: org.springframework.boot.restclient.RestTemplateBuilder
```

Cause: `spring-boot-resttestclient` alone does not bring `spring-boot-restclient` on this project's dependency set. All 12 evaluation tests errored with `ApplicationContext failure threshold (1) exceeded` until the missing artifact was added.

## Evaluation-test compatibility modifications (no functional change)

To make the fixed suite runnable on Spring Boot 4.1.1 without changing assertions, URLs, or status expectations:

1. `evaluation-tests/StudentApiEvaluationTest.java`:
   - `import org.springframework.boot.test.web.client.TestRestTemplate`
     -> `import org.springframework.boot.resttestclient.TestRestTemplate`
   - added `import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate`
   - added class annotation `@AutoConfigureTestRestTemplate` (required in Boot 4 to expose the `TestRestTemplate` bean with `RANDOM_PORT`)
2. `pom.xml` (test scope only, versions managed by `spring-boot-starter-parent:4.1.1`):
   - added `org.springframework.boot:spring-boot-resttestclient`
   - added `org.springframework.boot:spring-boot-restclient`
3. Copied fixed evaluation file unchanged (by hash) to `src/test/java/com/agenticai/studentmanagement/StudentApiEvaluationTest.java`.

No validation rule was weakened. No error format was fixed. No test was skipped, disabled, or hard-coded. The same fixed harness must be used unchanged for Conditions B and C to preserve comparability.

## Measurements not available

No additional timing, token, cost, or tool-call metrics are recorded here because they were not reliably exposed. No values are estimated or invented.
