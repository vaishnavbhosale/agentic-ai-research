# Reference Map — Planned Paper Claims to Verified Support

Rule: each claim below is paired only with references from `paper/literature-foundation.md`. Where no verified source covers a claim, the entry is marked `[ADDITIONAL VERIFIED SOURCE NEEDED]` instead of inventing support. Our own pilot observations (Conditions A/B/C) need no external citation and are labeled as such.

## Foundations

Claim:
"Agentic AI denotes systems that pursue goals autonomously through perception, reasoning, action, and tool use, distinct from single-turn language-model inference."

Supporting reference:
Sapkota et al. [3]; Abou Ali et al. [4].

Claim:
"A useful taxonomy separates individual AI agents (single-actor tool users) from agentic AI systems (multi-step, possibly multi-actor autonomous systems)."

Supporting reference:
Sapkota et al. [3].

## Architectures

Claim:
"Contemporary agent architectures span single-agent reasoning/action loops and multi-agent conversational systems with specialized roles."

Supporting reference:
Yao et al. [1]; Wu et al. [2]; Abou Ali et al. [4].

Claim:
"Multi-agent designs assign distinct responsibilities (e.g., planning, implementation, verification) to specialized agents that coordinate through structured handoffs."

Supporting reference:
Wu et al. [2].

Note: our Condition C instantiation (Manager/Developer/Tester) is our own experimental design — observation, not a literature claim.

## ReAct

Claim:
"ReAct interleaves reasoning and actions to allow an LLM to interact with external environments."

Supporting reference:
Yao et al. [1].

Claim:
"Reasoning traces, actions (tool calls), and observations form a repeated loop until task completion."

Supporting reference:
Yao et al. [1].

## Multi-agent systems

Claim:
"Multi-agent conversation enables next-generation LLM applications by letting role-specialized agents collaborate, including with human oversight and tool use."

Supporting reference:
Wu et al. [2].

Claim:
"Coordination artifacts (plans, handoffs, test reports) make multi-agent runs auditable."

Supporting reference:
Wu et al. [2] (conversation as coordination medium).

Note: our logged artifacts (plan document, hash-verified test copy, Surefire reports) are our own observations.

## Agent evaluation

Claim:
"Fixed, task-grounded test suites with clear pass/fail criteria are an established way to evaluate agents."

Supporting reference:
Liu et al. [7]; Jimenez et al. [8]; Yehudai et al. [5].

Claim:
"Benchmark comparisons require attention to replication, variance, and comparison methodology; single runs cannot support significance claims."

Supporting reference:
Mohammadi et al. [6]; Yehudai et al. [5].

Note: our statements that all three conditions passed 12/12 evaluation tests (13/13 overall) with zero human interventions are our own pilot observations.

## Software-engineering agents

Claim:
"Language models can be evaluated as agents on practical software tasks verified by tests, including real-world issue resolution."

Supporting reference:
Jimenez et al. [8]; Liu et al. [7].

Claim:
"Our pilot follows this precedent at small scale: a scoped REST API task with a frozen 12-test HTTP suite executed identically across conditions."

Supporting reference:
Methodology precedent: Jimenez et al. [8]; Liu et al. [7]. (Task and results themselves are our own observations.)

## Challenges and safety

Claim:
"Surveyed challenges for agentic AI include scoping, evaluation rigor, and safe deployment considerations."

Supporting reference:
Sapkota et al. [3]; Abou Ali et al. [4].

Claim:
"Specific safety-incident rates, red-teaming outcomes, or alignment guarantees for agentic deployments."

Supporting reference:
[ADDITIONAL VERIFIED SOURCE NEEDED] — no verified source in the current foundation covers quantitative safety claims; none is asserted.

## Future directions

Claim:
"Surveyed future directions include richer architectures, broader applications, and more rigorous evaluation."

Supporting reference:
Abou Ali et al. [4]; Sapkota et al. [3].

Claim:
"Any concrete forecast (capability timelines, adoption statistics, or performance projections)."

Supporting reference:
[ADDITIONAL VERIFIED SOURCE NEEDED] — no verified source in the current foundation supports dated forecasts or statistics; none is asserted.
