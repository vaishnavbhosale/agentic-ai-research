# Consolidated Raw Results (Factual Only — No Analysis)

Sources:
- `experiment/direct-llm` (`de0314808093b1b50f4d0aaadbb3fbab5d382b33`): `results/direct-llm/experiment-report.md`
- `experiment/react` (`7298adeef1fc61500dd78df8e01331a324ba20aa`): `results/react/experiment-report.md`
- `experiment/multi-agent` (`a030a196fb03c3e5be2726b470f196aa5b3f0f6d`): `results/multi-agent/experiment-report.md`
- Common harness: `7c88e756afcd9f2e0126bd65c345de5fdc80f529` (`COMMON-EVALUATION-HARNESS-BOOT4`)

Only values already recorded in those reports are reproduced below. Nothing is averaged, no additional percentages are calculated, no significance testing is performed, and no superiority claim is made.

| Metric | Direct LLM | ReAct | Multi-Agent |
|---|---:|---:|---:|
| Evaluation tests passed | 12 | 12 | 12 |
| Evaluation tests failed | 0 | 0 | 0 |
| Overall tests passed | 13 | 13 | 13 |
| Overall tests failed | 0 | 0 | 0 |
| Implementation/fix iterations | 3 | 1 | 1 |
| Human interventions | 0 | 0 | 0 |
| Agent cycles/interactions | Not applicable (direct single pass; no ReAct cycles; no Manager/Developer/Tester roles) | 7 ReAct cycles | 4 interactions (Manager 2, Developer 1, Tester 1); 1 coordination cycle |
| Full Maven test time | 22.045 seconds | 21.753 seconds | 26.963 seconds |
| Tool/action count | unavailable / reliably unmeasurable | Not reliably measurable in this environment | Not reliably measurable in this environment |
| Execution-time measurement limitation | Approximate wall-clock interval 17:53:28 to 18:06:06 only; no independent wall timer; no additional timing invented | Maven-measured test-compile 6.456s plus full test; wall-clock approximately 18:16 to 18:18:59; no independent wall timer | Maven-measured full suite only; wall-clock approximately 18:25 to 18:28:52; no independent wall timer |

Notes:
- All three report `BUILD SUCCESS` with `0 failures, 0 errors, 0 skipped` on the final run.
- Direct LLM `3` iterations include 1 initial implementation plus 2 Boot 4 harness-compatibility fixes encountered before the common harness existed; ReAct and Multi-Agent started from the common harness and required `0` correction cycles.
- Tool/action counts were not reliably exposed in any condition; the table reproduces the reports' explicit statements without invention.
