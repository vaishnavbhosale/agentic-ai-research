# Condition C — Multi-Agent System — Experiment Report (Factual, no comparison)

## A. Starting commit

`7c88e756afcd9f2e0126bd65c345de5fdc80f529` (`COMMON-EVALUATION-HARNESS-BOOT4`)
Branch: `experiment/multi-agent` (from harness; no reuse from `direct-llm` or `react`).

## B. Final commit

Created by commit message `EXPERIMENT-C-MULTI-AGENT-COMPLETE` on branch `experiment/multi-agent`.
See `git log -1 --pretty=format:"%H %s"` after completion for the exact hash (reported alongside this file; self-hash cannot be pre-written without stale reference).

## C. Files created

- `src/main/java/com/agenticai/studentmanagement/Student.java` (Developer; JPA entity, validation)
- `src/main/java/com/agenticai/studentmanagement/StudentRepository.java` (Developer; `JpaRepository`)
- `src/main/java/com/agenticai/studentmanagement/StudentController.java` (Developer; `/students` CRUD, 201/200/404/204, `@Valid`)
- `src/test/java/com/agenticai/studentmanagement/StudentApiEvaluationTest.java` (Tester; hash-identical copy, `35E7D2E3...D2BBCC50C`)
- `results/multi-agent/agent-log.md` (coordination log, 4 interactions)
- `results/multi-agent/experiment-report.md` (this file)

## D. Files modified

None. `git diff --stat` empty at verification. Untouched: `experiment-specification.md`, `evaluation-tests/StudentApiEvaluationTest.java` source, `pom.xml` versions/deps, `application.properties`, Java 21, Boot 4.1.1.

## E. Files deleted, if any

None.

## F. Number of Manager interactions

2 (Interaction 1: planning; Interaction 4: final review/approval). Plus 1 delegation (Manager -> Developer) and 0 correction requests.

## G. Number of Developer interactions

1 (Interaction 2: implementation of 3 files; no fixes needed).

## H. Number of Tester interactions

1 (Interaction 3: inspect + copy + `mvn -B test` + PASS report).

## I. Number of agent coordination cycles

1 full cycle: Manager -> Developer -> Tester -> Manager (PASS, finish). No repeat cycles.

## J. Number of implementation/fix iterations

1 (initial implementation passed; 0 fix iterations).

## K. Commands executed

- Developer: file writes (3) + read-backs (via subagent).
- Tester: `Copy-Item` + `Get-FileHash` x2 (match), `mvn -B test` (full suite).
- Manager/harness verification: `git status/diff`, `Get-ChildItem src/main + src/test`, hash re-check, `Get-Content target/surefire-reports/*.txt`, `git add` + `git commit`.
- Maven: full `mvn -B test` -> `BUILD SUCCESS`, `Total time: 26.963 s`, `Finished at: 2026-10-02T18:28:52+05:30` (Tester-observed; surefire confirms `15.19s` eval + `1.317s` context).

## L. Evaluation tests passed

12

## M. Evaluation tests failed

0 (0 failures, 0 errors, 0 skipped in `StudentApiEvaluationTest`).

## N. Overall tests passed

13 (12 evaluation + 1 `contextLoads`).

## O. Overall tests failed

0

## P. Human interventions

0. No human implementation or repair requested.

## Q. Tool/action count, if reliably available

Not reliably measurable in this environment. No single counter is exposed; no value invented. Observable: 3 subagent Tasks (Manager, Developer, Tester), plus harness reads/writes, 1 full Maven test execution, git verifications. No additional specialized agents beyond the three.

## R. Elapsed execution time, if reliably available

Maven-measured: full suite `26.963 s` (eval `15.19 s` + context `1.317 s` in surefire). Wall-clock: Tester run finished `2026-10-02T18:28:52+05:30`; branch work spanned approximately `18:25` (branch creation) to `18:28:52`. No independent wall timer was run; no further estimate invented.

## S. Errors encountered

None requiring correction. No compilation errors; no test failures. Surefire logs show expected `MethodArgumentNotValidException` warnings for negative cases resolving to 4xx (correct behavior).

## T. Correction cycles

0. Tester verdict PASS on first run; Manager issued 0 correction requests; Developer performed 0 fixes; Tester performed 0 retests.

## U. Final Maven result

```
[INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0 -- in StudentApiEvaluationTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0 -- in StudentManagementApplicationTests
[INFO] Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
[INFO] Total time: 26.963 s, Finished at: 2026-10-02T18:28:52+05:30
```

No comparison with Direct LLM or ReAct is made. No claim of better/worse. Raw Condition C observations only.
