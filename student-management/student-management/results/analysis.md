# Analysis — Agentic AI Pilot Experiment (Observations Only)

Scope: one run per condition (Direct LLM, ReAct, Multi-Agent) of the same Student Management REST API task on Java 21 / Spring Boot 4.1.1 with a fixed 12-test HTTP evaluation suite plus 1 context test. Sources: `results/consolidated-results.md`, `results/consolidated-methodology.md`, and the three condition reports. No new data was collected for this analysis.

## 1. Experimental observations

All three conditions completed the task and reported `BUILD SUCCESS` on the final `mvn -B test` run. The recorded outcomes are identical on functional measures: 12/12 evaluation tests passed and 13/13 overall tests passed in every condition, with 0 failures, 0 errors, 0 skipped, and 0 human interventions. Recorded process measures differ: implementation/fix iterations were 3 (Direct LLM), 1 (ReAct), and 1 (Multi-Agent); logged process structure was not applicable (Direct LLM), 7 ReAct reason→action→observation cycles (ReAct), and 4 agent interactions across 1 coordination cycle — Manager 2, Developer 1, Tester 1 (Multi-Agent). Recorded full-suite Maven times were 22.045 s (Direct LLM), 21.753 s (ReAct), and 26.963 s (Multi-Agent). Tool/action counts were explicitly unavailable in all conditions. These sentences reproduce the consolidated table; interpretation follows separately below.

## 2. Test correctness comparison

Observed: no differentiation. Each condition passed the same fixed 12-test evaluation suite (POST valid/invalid-email/blank-name/age-below-18; GET all/existing/nonexistent; PUT valid/nonexistent/invalid; DELETE existing/nonexistent) and the same default context test. Because the task's acceptance bar was fully met by all three implementations, functional correctness — as defined by this suite — cannot rank the architectures in this pilot. This is an observation about the suite's ceiling on this task, not a claim of general equivalence: a more demanding task (e.g., larger surface area, ambiguous requirements, or stricter non-functional constraints) might separate approaches where this one did not.

## 3. Implementation/fix iteration comparison

Observed: Direct LLM recorded 3 iterations; ReAct and Multi-Agent each recorded 1. Interpretation (kept distinct from observation): the Direct LLM count is inflated by history, not necessarily by architecture. Condition A ran before the common harness existed and absorbed the two Boot 4 test-infrastructure fixes (missing `TestRestTemplate` package and missing `RestTemplateBuilder` bean); Conditions B and C started from the already-fixed harness (`COMMON-EVALUATION-HARNESS-BOOT4`) and required zero correction cycles. The iteration gap therefore reflects experimental sequencing at least as much as agentic strategy, and must not be read as an efficiency ranking.

## 4. ReAct cycle observations

Observed: Condition B logged 7 explicit cycles (inspect → entity → repository+controller → compile check → hash-verified copy → full test → verify/report), with the full suite passing on the first test execution. Interpretation: the ReAct structure provided an auditable trace from reasoning to action to observation at each step, which is valuable for reproducibility of the experimental record. Whether that trace improved the outcome cannot be determined here, because the outcome (13/13) was also achieved without it. The cost of the trace — extra reads, a separate compile check, and log maintenance — was not measured against any benefit in this run.

## 5. Multi-agent coordination observations

Observed: Condition C logged 4 interactions across 1 coordination cycle (Manager planning → Developer implementation of 3 files → Tester hash-verified copy and test run → Manager review and approval), with 0 correction requests, 0 fixes, and 0 retests. Interpretation: role separation executed cleanly when no failure occurred, and the handoff artifacts (plan document, file list, hash match, Surefire numbers) made each transition checkable. The design's intended value — structured recovery via Tester→Manager→Developer loops — was not exercised, because there was nothing to recover from. A pilot in which the coordination machinery idles cannot speak to how well that machinery handles genuine failure.

## 6. Execution-time observations

Observed: single-run Maven full-suite times of 22.045 s, 21.753 s, and 26.963 s. Interpretation: these numbers are reported as recorded, but they must not be treated as architecture benchmarks. They reflect one JVM run each on one machine, including framework startup, H2 initialization, and Tomcat on a random port — all sources of run-to-run noise that a single sample cannot average out. The ordering (ReAct fastest, Multi-Agent slowest here) is a raw observation of this run only; with n=1 per condition, any difference is indistinguishable from noise, and no timing claim beyond the recorded values is warranted.

## 7. Human-intervention observations

Observed: 0 in all conditions; no human wrote or repaired code. Interpretation: within the narrow scope of this well-specified CRUD task, each approach proceeded autonomously from specification to green suite. This supports the feasibility of autonomous completion for routine scoped tasks in this environment, but says nothing about supervision needs on tasks requiring judgment under ambiguity, where intervention rates would be the metric of interest.

## 8. Reliability observations

Observed: every condition reached `BUILD SUCCESS` with identical functional results, and every evaluation-test copy was hash-verified against its source. Interpretation: the reliability signal in this pilot concerns the harness rather than the agents — hash checks, fixed assertions, and the shared Boot 4 compatibility fix held across all runs. Agent-level reliability (e.g., variance across repeated runs of the same condition) was not measured and cannot be inferred.

## 9. Cost/measurement limitations

Observed gaps, reproduced without invention: tool/action counts are unavailable in all conditions (no reliable counter exposed); elapsed time rests solely on Maven-reported durations plus coarse log timestamp intervals (Direct LLM 17:53:28–18:06:06; ReAct ≈18:16–18:18:59 including a 6.456 s compile check; Multi-Agent ≈18:25–18:28:52); no token, cost, memory, or concurrency measurements exist. Interpretation: without cost and effort instrumentation, this pilot cannot address efficiency trade-offs — only that all three reached the same functional endpoint with unmeasured and non-comparable effort accounting.

## 10. Threats to validity

- Small sample: one run per condition; no replication, randomization, or counterbalancing. Order effects are concrete here (Condition A absorbed harness fixes; B and C inherited them).
- Task ceiling: a 12-test CRUD suite that all approaches pass cannot discriminate on correctness; construct validity for "software-engineering ability" is therefore narrow.
- Single environment: one OS/JDK/Maven/DB combination (Windows 11, JDK 21.0.9, Maven 3.9.12, H2 in-memory); no generalization to other stacks.
- Single task instance: one specification, one domain, one framework; agentic differences may emerge only on larger or less-specified tasks.
- Measurement gaps: no effort, cost, or repeated-timing data; Maven times are uncontrolled single samples.
- Researcher degrees of freedom: harness fixes, though functionally neutral and shared by B and C, originated in Condition A; any residual asymmetry is documented but cannot be undone post hoc.

## 11. What can and cannot be concluded from this experiment

Can be concluded (observations): on this specified task, in this environment, with the shared Boot 4 harness, each of the three approaches produced a passing implementation (12/12 evaluation, 13/13 overall) with zero human interventions and zero test-criteria modifications. The ReAct run is documented in 7 explicit cycles; the multi-agent run in 4 interactions over 1 coordination cycle with no corrections; the Direct LLM run required 3 iterations including 2 pre-harness infrastructure fixes. Maven single-run times were 22.045 s, 21.753 s, and 26.963 s.

Cannot be concluded: no statistical significance; no generalization to all LLMs, agent frameworks, tasks, or environments; no ranking of architectures as universally superior, faster, or more reliable; no efficiency or cost comparison. The appropriate use of this pilot is as a methods and harness shakedown — demonstrating that the frozen specification, fixed suite, hash-verified copying, and per-condition logging can produce comparable raw observations — before any future study with pre-registered hypotheses, repeated trials, and proper statistical treatment.
