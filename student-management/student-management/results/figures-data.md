# Figures Data — Clean Tabular Source (Recorded Values Only)

Provenance: values copied verbatim from `results/consolidated-results.md` (which reproduces the three condition reports). No averages, no derived percentages, no projections. Times in seconds as Maven-reported. Counts are single-run observations (n=1 per condition).

## Test-pass comparison (for bar chart: passed vs failed)

| Condition | Evaluation passed | Evaluation failed | Overall passed | Overall failed |
|---|---:|---:|---:|---:|
| Direct LLM | 12 | 0 | 13 | 0 |
| ReAct | 12 | 0 | 13 | 0 |
| Multi-Agent | 12 | 0 | 13 | 0 |

## Execution-time comparison (for bar chart; single-run Maven times)

| Condition | Full Maven test time (s) |
|---|---:|
| Direct LLM | 22.045 |
| ReAct | 21.753 |
| Multi-Agent | 26.963 |

Caveat for chart caption: single run per condition on one machine; not a benchmark; do not display error bars or rank labels.

## Iteration/cycle comparison (for grouped chart; distinct units — do not stack)

| Condition | Implementation/fix iterations | Agent cycles | Agent interactions (M/D/T) | Coordination cycles | Human interventions |
|---|---:|---:|---|---:|---:|
| Direct LLM | 3 | n/a | n/a | n/a | 0 |
| ReAct | 1 | 7 | n/a | n/a | 0 |
| Multi-Agent | 1 | n/a | 2 / 1 / 1 (total 4) | 1 | 0 |

Caveat: units differ across columns (iterations vs cycles vs interactions); Direct LLM's 3 iterations include 2 pre-harness Boot 4 infrastructure fixes. Chart must label units and note the sequencing confound; do not normalize or average.

## Measurement-availability flags (for methods footnote)

| Metric | Direct LLM | ReAct | Multi-Agent |
|---|---|---|---|
| Tool/action count | unavailable | not reliably measurable | not reliably measurable |
| Independent wall timer | none | none | none |
| Token/cost counters | none | none | none |
