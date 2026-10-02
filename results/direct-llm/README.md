# Condition A — Direct LLM (Baseline)

This directory archives the bookkeeping for experimental Condition A only.

- Condition: Direct LLM / Baseline
- Branch: `experiment/direct-llm`
- Starting point: `EXPERIMENT-BASELINE-FROZEN` (`60365d2ea80a51c0744d3bcbfcadd26a62f285bd`)
- Work was autonomous, without ReAct loops and without multi-agent delegation.
- Implementation: standard production-quality Spring Boot REST API satisfying `experiment-specification.md`.
- Evaluation: unmodified functional criteria; Boot 4.1.1 infrastructure compatibility fixes only (see `experiment-report.md`).
- Result: evaluation suite 12/12 passed; overall 13/13 passed; `BUILD SUCCESS`.

No comparison with Condition B (ReAct Agent) or Condition C (Multi-Agent System) has been made. Those conditions have not been executed at the time of this commit. No claim is made that any architecture is better or worse. This record exists only to freeze Condition A observations before proceeding.
