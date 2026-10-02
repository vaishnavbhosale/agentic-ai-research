# Condition B — ReAct Agent — Experiment Report (Factual, no comparison)

## A. Starting commit

`7c88e756afcd9f2e0126bd65c345de5fdc80f529` (`COMMON-EVALUATION-HARNESS-BOOT4`)
Branch at start: `experiment/react` (created from harness commit; Direct LLM branch untouched).

## B. Final commit

Created by commit message `EXPERIMENT-B-REACT-COMPLETE` on branch `experiment/react`.
See `git log -1 --pretty=format:"%H %s"` after completion for the exact hash (reported in the completion message alongside this file; self-hash cannot be written before commit without stale reference).

## C. Files created

- `src/main/java/com/agenticai/studentmanagement/Student.java` — JPA entity with `id IDENTITY`, `name @NotBlank`, `email @NotBlank @Email`, `age @NotNull @Min(18) @Max(100)`
- `src/main/java/com/agenticai/studentmanagement/StudentRepository.java` — `extends JpaRepository<Student, Long>`
- `src/main/java/com/agenticai/studentmanagement/StudentController.java` — `POST /students->201`, `GET /students->200`, `GET /{id}->200/404`, `PUT /{id}->200/404 + @Valid`, `DELETE /{id}->204/404` via `ResponseStatusException`
- `src/test/java/com/agenticai/studentmanagement/StudentApiEvaluationTest.java` — byte-identical copy of harness evaluation test (hash `35E7D2E3...D2BBCC50C` both sides)
- `results/react/react-log.md` — 7 ReAct cycles (reason/action/observation)
- `results/react/experiment-report.md` — this file

## D. Files modified

None. `git diff --stat` empty at verification time.
Specifically untouched: `experiment-specification.md`, `evaluation-tests/StudentApiEvaluationTest.java`, `pom.xml` (harness with `resttestclient`/`restclient` kept), `application.properties`, Java 21, Boot 4.1.1.

## E. Files deleted, if any

None.

## F. Number of ReAct cycles

7 (see `results/react/react-log.md`):
1. Inspect spec/harness/state
2. Create entity
3. Create repository + controller
4. Compile check
5. Copy evaluation test (hash verify)
6. Full test run
7. Diff/file verification + reporting

## G. Number of implementation/fix iterations

1. Initial implementation passed the full suite on first run; 0 correction cycles required.

## H. Commands executed

- `mvn -B test-compile -DskipTests` -> `BUILD SUCCESS` in `6.456s`
- `Copy-Item evaluation-tests/... -> src/test/...` + `Get-FileHash` both sides -> `HASH-MATCH`
- `mvn -B test` -> `BUILD SUCCESS` in `21.753s`
- `git status --short`, `git diff --stat`, `Get-ChildItem src/main + src/test`, `git log/branch` verifications
- `git add .` + `git commit -m "EXPERIMENT-B-REACT-COMPLETE"` (completion step)

## I. Final evaluation tests passed

12

## J. Final evaluation tests failed

0 (0 failures, 0 errors, 0 skipped in `StudentApiEvaluationTest`)

## K. Overall tests passed

13 (12 evaluation + 1 `StudentManagementApplicationTests.contextLoads`)

## L. Overall tests failed

0

## M. Human interventions

0. No human wrote or repaired code. Autonomous ReAct loop only.

## N. Tool/action count if reliably available

Not reliably measurable in this environment. No single counter is exposed; no value invented. Observable actions were: reads (spec/pom/properties/evaluation test/log), writes (entity/repository/controller/log/report), 2 Maven executions + 1 compile check, copy+hash check, git verifications. No sub-agent Tasks used (single ReAct agent by design).

## O. Elapsed execution time if reliably available

Maven-measured: `test-compile 6.456s`, full `mvn -B test 21.753s` (timestamps `18:18:45` start to `18:18:59` finish in log). Wall-clock for Condition B implementation (results dir `18:16` to final test `18:18:59`) approximately 3 minutes plus inspection; no independent wall timer was run. No further estimate invented.

## P. Errors encountered

None requiring correction. Compilation succeeded first attempt; all 13 tests passed first full run. Log contains expected `WARN ... MethodArgumentNotValidException` lines for negative validation cases (blank name, bad email, age 16) resolving to 4xx — these are correct behavior, not failures.

## Q. Description of any implementation corrections

None. No implementation file was edited after its initial creation. No test, spec, harness, or dependency change was needed on this branch (harness already fixed in `COMMON-EVALUATION-HARNESS-BOOT4`).

## R. Final Maven test result

```
[INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0 -- in StudentApiEvaluationTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0 -- in StudentManagementApplicationTests
[INFO] Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
[INFO] Total time: 21.753 s, Finished at: 2026-10-02T18:18:59+05:30
```

No comparison with Direct LLM or Multi-Agent is made. No statistical analysis performed. Raw Condition B observations only.
