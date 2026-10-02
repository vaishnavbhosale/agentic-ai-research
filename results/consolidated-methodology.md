# Consolidated Methodology (Factual Only — No Analysis)

## 1. Common starting environment

- Frozen baseline `60365d2ea80a51c0744d3bcbfcadd26a62f285bd` (`EXPERIMENT-BASELINE-FROZEN`): Maven project with H2 in-memory configuration, `experiment-specification.md`, `evaluation-tests/StudentApiEvaluationTest.java` (Boot 3 import form), default application source and `contextLoads` test.
- Common harness `7c88e756afcd9f2e0126bd65c345de5fdc80f529` (`COMMON-EVALUATION-HARNESS-BOOT4`): baseline plus Boot 4 test-compatibility only. Conditions B and C started from the harness; Condition A started from the baseline and encountered the same compatibility issue first.

## 2. Common Java version

Java 21 (`java.version=21` in `pom.xml`; runtime `21.0.9` observed). Unchanged in all conditions.

## 3. Common Spring Boot version

Spring Boot `4.1.1` (`spring-boot-starter-parent:4.1.1`). Unchanged in all conditions.

## 4. Common evaluation suite

Fixed HTTP-level suite `StudentApiEvaluationTest` (12 tests: POST valid/invalid-email/blank-name/age-below-18; GET all/existing/nonexistent; PUT valid/nonexistent/invalid; DELETE existing/nonexistent) plus default `StudentManagementApplicationTests.contextLoads` (1 test). Tests use `TestRestTemplate` with `Map` payloads, random e-mails per test, `4xx` (not exact code/schema) for validation, exact `404`/`201`/`200`/`204` where specified. Copied by hash into `src/test` per condition; contents semantically unchanged.

## 5. Common task

Implement the Student Management REST API per `experiment-specification.md`: Student (`id` generated, `name` not-blank, `email` valid, `age` 18–100) with `POST /students`, `GET /students`, `GET /students/{id}`, `PUT /students/{id}`, `DELETE /students/{id}` and the specified status codes. Sensible Spring Boot architecture; no prescribed package/class/service/exception/JSON-error details.

## 6. The three architectural conditions

- Condition A (`experiment/direct-llm`): Direct LLM / baseline, single pass, no ReAct loop, no sub-agents.
- Condition B (`experiment/react`): Single ReAct agent operating through explicit reason → action → observation cycles (7 logged cycles), no sub-agents.
- Condition C (`experiment/multi-agent`): Exactly three specialized subagents — Manager/Planner, Developer, Tester — coordinated as Manager → Developer → Tester → Manager (1 coordination cycle, 4 logged interactions), no additional roles.

## 7. The Boot 4 evaluation-harness compatibility change

Infrastructure only; functional criteria unchanged:
- `TestRestTemplate` moved from `org.springframework.boot.test.web.client` to `org.springframework.boot.resttestclient` (artifact `spring-boot-resttestclient`, test scope).
- `org.springframework.boot.restclient` (test scope) required for `RestTemplateBuilder` (`NoClassDefFoundError` otherwise).
- `@AutoConfigureTestRestTemplate` required on the test class to expose the bean with `RANDOM_PORT`.
- Applied first in Condition A (2 compat fixes), then standardized in `COMMON-EVALUATION-HARNESS-BOOT4` and reused unchanged by B and C.

## 8. Known measurement limitations

- Tool/action counts: not reliably exposed in this environment in any condition; reports explicitly state unavailability.
- Elapsed time: only Maven-reported times (`22.045s`, `21.753s`, `26.963s` plus `6.456s` compile check in B) and log timestamp intervals are available; no independent wall timer, no token/cost counters.
- Single run per condition; single machine (`Windows 11`, Maven `3.9.12`, JDK `21.0.9`); H2 in-memory database; no load or concurrency measurement.
- No missing values have been invented; unavailable cells are labeled as such.

## 9. Why the current results must not yet be treated as statistically significant

- Sample size is one run per condition; no replication, no randomization, no counterbalancing.
- No variance, confidence interval, or significance test has been computed (deliberately omitted here).
- Maven times reflect one machine state and include JVM/Gradle-cache-independent noise; they are not controlled performance benchmarks.
- All three conditions achieved the same functional outcome (13/13 pass), so the table alone cannot support any superiority, equivalence, or generality claim.
- Any future inference would require pre-registered hypotheses, repeated trials, fixed harness reuse, and appropriate statistical methods — none of which are performed in this raw-results record.
