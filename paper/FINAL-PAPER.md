# Agentic AI: A Comprehensive Study of Autonomous AI Agents, Their Architectures, Applications, Challenges, and Future Directions

> DOCUMENT STATUS: Chapters 1–2 fully written; Chapters 3–10, References (entries listed), and Appendices remain as skeleton notes. Experimental notes reproduce only values already recorded in `results/consolidated-results.md`, `results/consolidated-methodology.md`, `results/analysis.md`, and `results/figures-data.md`. No result, reference, DOI, or statistic is invented. Claims lacking a verified source are marked `[SOURCE NEEDED]`.

---

## FRONT MATTER

### Title Page

Note: Will carry the full title above, SPPU-affiliated engineering college name, department, academic year, and student/guide identification as per institute format. No technical claims here.

### Certificate

Note: Standard institute certificate of original work and guide approval. No technical claims here.

### Declaration

Note: Student declaration of originality, including that the pilot experiment (n=1 per condition) and its logs are our own observations. No technical claims here.

### Acknowledgement

Note: Thanks to guides, department, and open-source communities (Spring Boot, H2, JUnit). No technical claims here.

### Abstract

Note: Will summarize in ~200 words: (a) survey scope grounded in `paper/literature-foundation.md` (8 verified refs); (b) pilot method — same Student Management REST API task under 3 conditions with a fixed 12-test suite; (c) observed outcome — all conditions 12/12 evaluation (13/13 overall), iterations 3/1/1, single-run Maven times 22.045/21.753/26.963 s, 0 interventions; (d) explicit pilot caveat — n=1, no significance, no superiority claim.

### Keywords

Note: Agentic AI, AI Agents, ReAct, Multi-Agent Systems, LLM Agents, Agent Evaluation, Software Engineering Agents. Terms align with foundation sections A–F.

### Table of Contents

Note: Generated from the chapter/section headings below.

### List of Figures

Note: Planned figures (data in `results/figures-data.md`, single-run values only, captions must state n=1 and no error bars): test-pass comparison; execution-time comparison; iteration/cycle comparison.

### List of Tables

Note: Planned tables: fixed evaluation suite (12 tests); consolidated raw results; environment/versions (Java 21, Boot 4.1.1); harness compatibility changes.

### List of Abbreviations

Note: LLM, ReAct (Reasoning + Acting), API, REST, JPA, MVC, H2, CRUD, HTTP. Expand on first use in chapters.

---

## CHAPTER 1 — INTRODUCTION

### 1.1 Background

Recent progress in large language models has shifted attention from single-turn text generation toward systems that act autonomously to achieve goals. Such autonomous agents perceive their environment, reason over observations, invoke tools, and adjust their behavior on the basis of feedback [3, 4]. Unlike a conventional classifier or a conversational assistant that responds to one prompt at a time, an agent is expected to carry a multi-step task through to completion, for example by reading files, editing code, running commands, and interpreting the results. This paper studies that class of systems — referred to here as agentic AI, following the taxonomy of Sapkota et al. [3] and the survey of Abou Ali et al. [4] — through two complementary contributions: a synthesis of the established literature, and a small controlled pilot experiment in which three different agentic approaches solve the same software-engineering task. The pilot is presented strictly as an illustration of a reproducible comparison method, not as evidence for general properties of agentic systems.

### 1.2 Evolution from Traditional AI to Agentic AI

Traditional artificial intelligence systems were typically built for a fixed task with a fixed input-output contract: rule-based programs encoded expert knowledge explicitly, while classical machine-learning models learned a mapping from training data to predictions. Both operated within narrowly defined boundaries and could not easily decompose novel problems, use external tools, or recover from their own mistakes. The arrival of large language models changed the interface — a single model could follow open-ended instructions — but single-turn inference on its own still lacked persistence, environment interaction, and error recovery. Tool-using agents closed part of this gap by giving models access to file systems, compilers, test runners, and web interfaces, and agentic AI, as surveyed by Abou Ali et al. [4] and taxonomized by Sapkota et al. [3], denotes the resulting systems that combine goal-directed planning, reasoning, memory, tool use, and observation into an autonomous loop. Throughout this paper, statements about this evolution drawn from the literature are cited as such, while statements about our own pilot are labeled as our observations.

### 1.3 Problem Statement

Architectural proposals for agents — single-pass generation, reasoning-and-acting loops, and multi-agent collaboration — are frequently compared informally or on different tasks with different success criteria, which makes it difficult to attribute any observed difference to the architecture itself. What is needed, and what this paper pilots at small scale, is a head-to-head comparison in which the task, the starting code, the environment, and the pass/fail criteria are held constant while only the agentic approach varies. Concretely, this paper compares three approaches — Direct LLM generation, a ReAct-style reasoning/action loop, and a multi-agent Manager/Developer/Tester system — on one identical task: a Student Management REST API defined by a frozen specification and judged by a fixed 12-test HTTP evaluation suite (plus one default context test).

### 1.4 Motivation

The motivation is both pedagogical and practical. In an engineering-college setting, students increasingly complete software tasks with LLM assistance, yet project evaluation still needs a reproducible basis for judging what was built and how. A frozen task specification paired with an automated, hash-verified test suite provides exactly such a basis, and the same apparatus doubles as a research instrument for comparing agentic strategies fairly. No claim is made here about the scale of industrial adoption of these techniques.

### 1.5 Research Questions

The experiment is designed to answer only the following questions, and no additional questions beyond its reach are posed:

RQ1: How do Direct LLM, ReAct, and Multi-Agent approaches perform on the same multi-step software-engineering task under a common evaluation suite?

RQ2: What process-level differences can be observed between the three approaches?

RQ3: What limitations arise when evaluating agent architectures using a small controlled software-engineering task?

### 1.6 Objectives

The objectives of this paper, each directly achievable within its scope, are: (i) review Agentic AI concepts and architectures from the verified literature; (ii) examine the ReAct and multi-agent approaches in particular; (iii) review existing agent-evaluation approaches; (iv) design a controlled pilot experiment with a frozen task and fixed suite; (v) compare the three approaches on the same Student Management REST API task; (vi) evaluate functional correctness together with observable process metrics (iterations, cycles, coordination records, and recorded run times); and (vii) identify limitations and future research directions warranted by the pilot.

### 1.7 Scope

The study focuses on software-engineering agents and a single representative task: a Java 21 / Spring Boot 4.1.1 Student Management REST API backed by an in-memory H2 database, implemented under a frozen specification and verified by a fixed HTTP-level test suite. The survey component covers the eight verified references in `paper/literature-foundation.md`. Studies involving other task domains, other technology stacks, other models, or cost and token metering are outside the scope of this paper.

### 1.8 Limitations

The pilot has the following explicit limitations, documented here so that no later chapter can overstate the findings: it covers one task; it uses one model configuration as provided by the environment; it comprises one experimental run per condition (n=1); the sample size therefore supports no statistical significance testing; cost and token measurement is absent because no reliable counters were exposed; and generalizability beyond this task, stack, and machine is not established. The task itself exhibited a ceiling effect (all conditions passed), and the run order introduced a sequencing confound (Condition A absorbed the Boot 4 test-harness fixes that Conditions B and C inherited). These points are examined further in Chapter 8.

### 1.9 Organization of the Paper

Chapter 2 reviews the literature; Chapter 3 systematizes agentic AI fundamentals; Chapter 4 details the architectures; Chapter 5 surveys applications, challenges, and security; Chapter 6 documents the experimental methodology; Chapter 7 reports the observed results; Chapter 8 discusses their interpretation; Chapter 9 outlines future directions; and Chapter 10 concludes. The References list the eight verified sources, and Appendices A–D carry the experimental specification, the fixed evaluation suite, the experimental logs, and the raw results respectively.

---

## CHAPTER 2 — LITERATURE REVIEW

### 2.1 Traditional AI and LLM-Based Systems

Traditional AI systems — expert systems with hand-encoded rules and machine-learning models trained for a single predictive mapping — performed reliably within their specified envelope but could not readily decompose unfamiliar problems, operate software tools, or revise their own plans after failure. Large language models broadened the interface by conditioning open-ended generation on natural-language instructions, yet a single forward pass still lacks task persistence and environmental grounding. The surveyed literature characterizes the subsequent step as the coupling of such models with perception, memory, and tool interfaces so that behavior extends across multiple steps [3, 4]. This section reports that characterization as established survey framing; no benchmark figures are asserted here.

### 2.2 AI Agents

Following the taxonomy of Sapkota et al. [3], an AI agent is understood here as a single-actor system that perceives its context, reasons toward a goal, and acts through available tools — for example, reading and writing files, executing shell commands, or querying an application under test. The agent receives observations (command output, test results, error messages) and conditions its next action on them. This definition is the working meaning of "agent" throughout the paper and the unit of analysis for Conditions A and B.

### 2.3 Agentic AI

Agentic AI, in the same taxonomy, denotes systems whose autonomy spans extended multi-step pursuits rather than isolated tool calls: they maintain goals, plan and replan, coordinate sub-tasks, and integrate feedback over a full lifecycle [3, 4]. Abou Ali et al. [4] survey how such systems are architected, where they are applied, and which research directions remain open. Our usage follows this survey-level meaning, and our Condition C (a Manager/Developer/Tester collaboration) is presented as one concrete instance of it — an experimental observation, not a redefinition.

### 2.4 Agent Architectures

The surveyed architectures span two broad families [4]. In the single-agent family, one model carries the whole loop of reasoning, acting, and observation. In the multi-agent family, specialized agents with distinct roles collaborate through structured conversation, sharing plans, intermediate artifacts, and verification reports [2]. The ReAct paradigm of Yao et al. [1] is the canonical example of the first family in this paper, and the conversational multi-agent framework of Wu et al. [2] is the canonical example of the second; Abou Ali et al. [4] provide the survey backbone relating them.

### 2.5 ReAct

Yao et al. [1] introduce ReAct as the synergistic interleaving of verbal reasoning traces with actions directed at an external environment, whose observations are then fed back into subsequent reasoning. The loop — reason, act, observe, repeat — grounds the model's problem solving in concrete tool outcomes rather than unaided generation, and it is directly applicable to software tasks where compilation and test output serve as observations. This is the sole verified primary source for the ReAct paradigm used in this paper, and it is the theoretical basis of our Condition B, which logged seven explicit reason→action→observation cycles.

### 2.6 Multi-Agent Systems

Wu et al. [2] propose enabling LLM applications through multi-agent conversation: agents with distinct roles and capabilities collaborate via dialogue, divide labor, critique intermediate results, and incorporate human input and tool use. Role separation makes each transition — delegation, handoff, and verification — an inspectable artifact. This is the sole verified primary source for multi-agent organization used in this paper, and it is the theoretical basis of our Condition C, instantiated as Manager (planning and review), Developer (implementation), and Tester (test execution and reporting) with one logged coordination cycle.

### 2.7 Agent Evaluation

How agents should be judged is itself surveyed in the verified literature. Yehudai et al. [5] survey evaluation dimensions and methodological considerations for LLM-based agents, while Mohammadi et al. [6] survey benchmarking practice with emphasis on comparison methodology and the replication needed before significance can be claimed. Task-grounded benchmarks provide the precedents: AgentBench evaluates language models as agents across diverse environments [7], establishing the pattern of fixed tasks with uniform scoring that our frozen 12-test suite follows at small scale. These sources directly motivate three of our controls: identical pass/fail criteria across conditions, hash-verified copying of the evaluation suite, and the refusal to perform significance testing on single runs.

### 2.8 Software Engineering Agents

The closest precedent for our pilot is test-verified software-task benchmarking. SWE-bench measures whether language models can resolve real-world GitHub issues, with resolution judged by tests [8]; AgentBench likewise includes settings in which agents operate on code [7]. The ReAct loop supplies the mechanism by which such tasks are typically approached — compiler and test output as observations guiding the next edit [1]. Our Student Management REST API pilot is positioned explicitly as a small instance of this paradigm: one scoped construction task, judged by an automated suite, executed identically across conditions. It is not presented as a replacement for large-scale benchmarks such as [7] and [8].

### 2.9 Research Gap

The existing literature, as verified, provides surveys and taxonomies of agentic AI [3, 4], the ReAct architectural approach [1], the AutoGen multi-agent framework [2], agent-evaluation methodology [5, 6], and software-task benchmarks [7, 8]. What it does not provide — and what this paper pilots rather than claims to close — is a fully logged, same-task, same-suite, same-environment comparison of a direct-generation baseline against a ReAct agent and a role-separated multi-agent system, with frozen starting points, hash-verified evaluation copying, and per-condition process records. This pilot fills exactly one cell of that larger matrix: one task, one stack, one run per condition. It leaves open replication across tasks, models, and trials, larger and more discriminative suites, and cost and safety instrumentation, all of which are recorded as future work rather than asserted results.

---

## CHAPTER 3 — AGENTIC AI FUNDAMENTALS

### 3.1 Definition

Note: Will adopt the [3][4] definition (autonomous goal pursuit via perception–reasoning–action–tool use). Marks any alternative definition as `[SOURCE NEEDED]`.

### 3.2 Goal-Oriented Behavior

Note: Will explain goals/subgoals with our task as illustration (implement spec to green suite). Labels the illustration as our observation.

### 3.3 Planning

Note: Will explain plan generation/delegation, illustrated by our Manager plan artifact (Condition C). General planning claims cite [4]; `[SOURCE NEEDED]` for formal planning-theory claims.

### 3.4 Reasoning

Note: Will explain ReAct-style traces per [1]; distinguishes literature finding from our logged cycles.

### 3.5 Memory

Note: Will cover working vs persistent memory at survey level per [4]. Our pilot used no explicit memory mechanism — will state this as observation; detailed memory architectures `[SOURCE NEEDED]`.

### 3.6 Tool Use

Note: Will cover file/Maven/test tools as the agent–environment interface in our pilot (reads, writes, `mvn -B test`, hash checks). General tool-use claims cite [1][2].

### 3.7 Observation and Feedback

Note: Will cover Surefire/test-log feedback driving fix decisions per [1]; notes our runs needed zero corrections (first-run PASS in B and C).

### 3.8 Action

Note: Will enumerate action types used (create/edit/run/inspect) as our observation; general action-space taxonomies `[SOURCE NEEDED]`.

### 3.9 Human-in-the-Loop

Note: Will note our pilot recorded 0 interventions in all conditions; broader HITL patterns cite [2] (AutoGen human-in-the-loop); quantitative supervision claims `[SOURCE NEEDED]`.

### 3.10 Agent Lifecycle

Note: Will outline specify→plan→implement→test→verify→report lifecycle as instantiated by our branches and logs. Presents it as our method, not a literature universal.

---

## CHAPTER 4 — AGENTIC AI ARCHITECTURES

### 4.1 Single-Agent Architecture

Note: Will describe the Direct LLM baseline (single pass, no loop/subagents) as our Condition A; general single-agent framing per [4].

### 4.2 Tool-Using Agents

Note: Will describe compiler/test/hash tools as environment grounding, citing [1]; lists the exact commands from condition reports.

### 4.3 ReAct Architecture

Note: Will detail the 7-cycle loop from `results/react/react-log.md`, grounded in Yao et al. [1]. No efficacy claim beyond the logged trace.

### 4.4 Multi-Agent Architecture

Note: Will detail Manager→Developer→Tester→Manager (4 interactions, 1 cycle) from `results/multi-agent/agent-log.md`, grounded in Wu et al. [2]. Notes recovery loop was designed but unexercised (0 corrections).

### 4.5 Hybrid Architectures

Note: Will note hybrids were NOT tested; any hybrid claim is `[SOURCE NEEDED]`. Keeps this section as future-facing only.

### 4.6 Architecture Comparison

Note: Will present only the recorded structural contrast (direct vs 7-cycle vs 4-interaction/1-cycle) with the explicit warning that functional outcomes were identical (13/13 each), so no ranking follows. Points to Chapters 7–8.

---

## CHAPTER 5 — APPLICATIONS, CHALLENGES AND SECURITY

### 5.1 Software Engineering

Note: Will ground in [8][7] plus our pilot (CRUD API, fixed suite, hash-verified runs). This is the only applications subsection with verified + observed support.

### 5.2 Healthcare

Note: Survey-level mention only per [4]’s applications framing; specifics `[SOURCE NEEDED]`. No statistics will be stated.

### 5.3 Finance

Note: Same as 5.2 — survey-level per [4]; specifics `[SOURCE NEEDED]`.

### 5.4 Education

Note: Same as 5.2 — survey-level per [4], plus our SPPU project-report context as our own framing; effectiveness claims `[SOURCE NEEDED]`.

### 5.5 Cybersecurity

Note: Survey-level per [4]; technical claims (attack rates, tooling) `[SOURCE NEEDED]`.

### 5.6 Robotics

Note: Survey-level per [4]; specifics `[SOURCE NEEDED]`.

### 5.7 Hallucination

Note: Will discuss as a surveyed challenge per [3][4] at framing level; incident rates or mitigation benchmarks `[SOURCE NEEDED]`.

### 5.8 Error Propagation

Note: Will discuss multi-step error accumulation conceptually; our pilot observed zero propagated failures (first-run passes in B/C) — labeled as observation; general rates `[SOURCE NEEDED]`.

### 5.9 Reliability

Note: Will discuss repeatability/variance concerns per [5][6]; notes our n=1 design cannot measure reliability — stated as limitation.

### 5.10 Prompt Injection

Note: Survey-level framing per [3][4]; concrete attack data `[SOURCE NEEDED]`. Our task involved no adversarial inputs — will state this.

### 5.11 Excessive Permissions

Note: Conceptual risk of over-privileged tool use; our agents used scoped file/Maven/test tools only (observation); prevalence data `[SOURCE NEEDED]`.

### 5.12 Cost and Resource Usage

Note: Will state no token/cost data was collected in any condition (per reports); any cost comparison is disallowed. Benchmarks for cost `[SOURCE NEEDED]`.

### 5.13 Governance

Note: Survey-level per [3][4]; specific regulatory claims `[SOURCE NEEDED]`.

### 5.14 Security Considerations

Note: Summary tying 5.7–5.13 together; will reaffirm that quantitative safety claims require `[ADDITIONAL VERIFIED SOURCE NEEDED]` (as in `paper/reference-map.md`) and none are asserted.

---

## CHAPTER 6 — EXPERIMENTAL METHODOLOGY

### 6.1 Research Question

Note: Will quote the欧冠 research question: how do Direct LLM, ReAct, and Multi-Agent perform on the same multi-step software task (pilot, n=1).

### 6.2 Experimental Objective

Note: Will state the objective as producing comparable raw observations (not rankings): same task, same suite, full logs.

### 6.3 Experimental Design

Note: Will describe baseline freeze (`60365d2`), common harness (`7c88e75`), and branches `experiment/direct-llm`, `experiment/react`, `experiment/multi-agent` (with commit hashes from consolidated results).

### 6.4 Controlled Variables

Note: Will list frozen spec, fixed 12+1 tests, hash-verified copying, no criteria changes, 0 interventions.

### 6.5 Common Development Environment

Note: Will record Java 21 (21.0.9), Boot 4.1.1, Maven 3.9.12, H2 in-memory `studentdb`/`create-drop`, Windows 11 single machine.

### 6.6 Student Management REST API Task

Note: Will summarize Appendix A: Student fields/validation and the 5 endpoints with expected codes (201/200/404/204, 4xx validation).

### 6.7 Fixed Evaluation Suite

Note: Will summarize the 12 tests + context test, `TestRestTemplate`+`Map` design, random e-mails, `4xx`-range assertions; cites [5][6][7][8] as methodology precedent.

### 6.8 Condition A — Direct LLM

Note: Will record branch/commit, single-pass baseline, 3 iterations (1 build + 2 pre-harness Boot 4 fixes), no loops/subagents.

### 6.9 Condition B — ReAct Agent

Note: Will record branch/commit, 7 explicit cycles from `react-log.md`, 1 implementation iteration, first-run pass.

### 6.10 Condition C — Multi-Agent System

Note: Will record branch/commit, Manager/Developer/Tester roles, 4 interactions over 1 cycle, 1 implementation iteration, first-run pass.

### 6.11 Evaluation Metrics

Note: Will list exactly the consolidated metrics (pass/fail, iterations, cycles/interactions, Maven times, interventions) plus documented unavailability of tool counts and wall timers.

### 6.12 Experimental Procedure

Note: Will give the step order: freeze → harness → branch per condition → implement → hash-copy suite → `mvn -B test` → fix loop if needed → verify diff → report → commit.

### 6.13 Reproducibility

Note: Will list artifacts enabling rerun: frozen commits, harness diff (2 deps + import + annotation), hash checks, Surefire logs, per-condition reports. Notes the self-hash documentation caveat from condition reports.

### 6.14 Threats to Validity

Note: Will summarize analysis §10: n=1, task ceiling, sequencing confound, single environment/task, measurement gaps.

---

## CHAPTER 7 — EXPERIMENTAL RESULTS

### 7.1 Functional Test Results

Note: Will state the headline observation: 12/12 evaluation and 13/13 overall in every condition, `BUILD SUCCESS`, 0 failures/errors/skipped (data: `results/figures-data.md`).

### 7.2 Direct LLM Results

Note: Will reproduce Condition A record: 12/0 and 13/0, 3 iterations, Maven 22.045 s, wall interval 17:53:28–18:06:06, 0 interventions, 2 Boot 4 errors encountered and fixed (package move + missing `RestTemplateBuilder`).

### 7.3 ReAct Results

Note: Will reproduce Condition B record: 12/0 and 13/0, 1 iteration, 7 cycles, Maven 21.753 s (+6.456 s compile check), 0 interventions, no corrections needed.

### 7.4 Multi-Agent Results

Note: Will reproduce Condition C record: 12/0 and 13/0, 1 iteration, 4 interactions/1 cycle (M2/D1/T1), Maven 26.963 s (surefire 15.19 s + 1.317 s), 0 interventions, 0 corrections.

### 7.5 Consolidated Results

Note: Will embed the consolidated table verbatim (no averages, no derived percentages) with commit references.

### 7.6 Process-Level Observations

Note: Will contrast structures without ranking: direct (no cycles) vs 7 ReAct cycles vs 4-interaction/1-cycle coordination; notes Direct LLM’s iteration count reflects pre-harness history.

### 7.7 Execution-Time Observations

Note: Will list single-run times 22.045/21.753/26.963 s with the mandatory caption: one run, one machine, not a benchmark, no error bars or rank labels.

### 7.8 Human Intervention

Note: Will state 0 in all conditions; distinguishes this scoped-task feasibility signal from general supervision needs.

### 7.9 Experimental Limitations

Note: Will restate measurement gaps (tool counts unavailable; no wall timer/token/cost data) and pointer to Chapter 8.

---

## CHAPTER 8 — DISCUSSION

### 8.1 Interpretation of Results

Note: Will separate observations (identical passes; differing logged processes; single-run times) from interpretation, per `results/analysis.md` §1.

### 8.2 Functional Correctness

Note: Will explain the ceiling effect: a suite all approaches pass cannot discriminate correctness; calls for harder future tasks, citing [7][8] as precedent for discriminative benchmarks.

### 8.3 Process-Level Differences

Note: Will discuss iteration/cycle contrasts with the sequencing confound stated plainly; no efficiency ranking.

### 8.4 ReAct Behavior

Note: Will value the 7-cycle audit trail for reproducibility while noting its benefit is unmeasured here since the outcome matched the baseline.

### 8.5 Multi-Agent Coordination

Note: Will value checkable handoffs (plan, file list, hash match, Surefire numbers) while noting recovery machinery idled (0 corrections), so failure-handling quality is untested.

### 8.6 Relationship to Previous Research

Note: Will tie our loop to Yao et al. [1], roles to Wu et al. [2], and benchmarking practice to [5][6][7][8]; marks any beyond-survey claim `[SOURCE NEEDED]`.

### 8.7 Implications

Note: Will limit implications to: (a) the harness/logging method is shown workable; (b) routine scoped CRUD is autonomously completable here; (c) no deployment or pedagogy generalization is licensed.

### 8.8 Threats to Validity

Note: Will expand analysis §10: n=1, ceiling, sequencing, single env/task, gaps, researcher degrees of freedom.

---

## CHAPTER 9 — FUTURE DIRECTIONS

### 9.1 Larger Benchmarks

Note: Will propose harder suites that discriminate (more endpoints, auth, pagination, concurrency) — our proposal, citing [7][8] as precedent; no invented difficulty statistics.

### 9.2 Multiple Software Tasks

Note: Will propose multi-domain tasks; coverage claims beyond our one task `[SOURCE NEEDED]` if asserted as literature.

### 9.3 Multiple Models

Note: Will propose cross-model replication; model-comparison statistics `[SOURCE NEEDED]` (none asserted).

### 9.4 Repeated Trials

Note: Will propose pre-registered repeated runs with variance/CIs per [5][6]; notes none were computed here.

### 9.5 Token and Cost Evaluation

Note: Will propose metering tokens/time/cost; states none was collected — labeled gap, not data.

### 9.6 Safety Evaluation

Note: Will propose adversarial/permission-scope tests; quantitative safety targets `[SOURCE NEEDED]`.

### 9.7 Human-Agent Collaboration

Note: Will propose intervention-rate studies building on [2]’s human-in-the-loop; effectiveness numbers `[SOURCE NEEDED]`.

### 9.8 Standardized Agent Evaluation

Note: Will propose fixed-harness reuse (frozen spec, hash-verified suites, full logs) as our methodological contribution, citing [5][6] for standards.

---

## CHAPTER 10 — CONCLUSION

### 10.1 Summary

Note: Will recap survey + pilot in one paragraph with the four headline numbers (12/12 ×3; 13/13 ×3; iterations 3/1/1; times 22.045/21.753/26.963 s) and the n=1 caveat.

### 10.2 Contributions

Note: Will list: (a) survey synthesis from 8 verified refs; (b) frozen task + fixed suite + Boot 4 harness method; (c) three fully logged pilot runs; (d) raw-data + analysis artifacts. Labels each as our work vs literature.

### 10.3 Final Conclusions

Note: Will close with the only licensed conclusion: the pilot demonstrates feasibility and method, not superiority — all approaches passed; differences are process-logged observations from single runs requiring future replication before any general claim.

---

## REFERENCES

Note: Will list exactly the 8 verified references from `paper/literature-foundation.md` with full citations, years, venues/identifiers as recorded (no invented DOIs/venues). Any extra source added later must first be externally verified and recorded in the foundation file.

- [1] Yao, Shunyu, et al. "ReAct: Synergizing Reasoning and Acting in Language Models." ICLR 2023. arXiv:2210.03629.
- [2] Wu, Qingyun, et al. "AutoGen: Enabling Next-Gen LLM Applications via Multi-Agent Conversation." arXiv:2308.08155.
- [3] Sapkota, Ranjan, Konstantinos I. Roumeliotis, and Manoj Karkee. "AI Agents vs. Agentic AI: A Conceptual Taxonomy, Applications and Challenges." Information Fusion, Vol. 126, Part B, Article 103599, 2026. DOI: 10.1016/j.inffus.2025.103599.
- [4] Abou Ali, Mohamad, Fadi Dornaika, and Jinan Charafeddine. "Agentic AI: A Comprehensive Survey of Architectures, Applications, and Future Directions." Artificial Intelligence Review, Vol. 59, Article 11, 2026. DOI: 10.1007/s10462-025-11422-4.
- [5] Yehudai, Asaf, et al. "Survey on Evaluation of LLM-based Agents." arXiv:2503.16416, 2025.
- [6] Mohammadi, Mahmoud, Yipeng Li, Jane Lo, and Wendy Yip. "Evaluation and Benchmarking of LLM Agents: A Survey." arXiv:2507.21504, 2025.
- [7] Liu, Xiao, et al. "AgentBench: Evaluating LLMs as Agents." arXiv:2308.03688.
- [8] Jimenez, Carlos E., et al. "SWE-bench: Can Language Models Resolve Real-World GitHub Issues?" ICLR 2024. arXiv:2310.06770.

---

## APPENDICES

### Appendix A — Experimental Specification

Note: Will reproduce `experiment-specification.md` scope (Student fields/validation, 5 endpoints, codes) or reference it verbatim; no paraphrase drift.

### Appendix B — Fixed Evaluation Test Suite

Note: Will reference the 12-test suite design (hash `35E7D2E3…`), Boot 4 compat notes (new `TestRestTemplate` package, `@AutoConfigureTestRestTemplate`, `resttestclient`/`restclient`), and the hash-verified copy procedure.

### Appendix C — Experimental Logs

Note: Will index `results/react/react-log.md` (7 cycles) and `results/multi-agent/agent-log.md` (4 interactions) plus the per-condition command histories.

### Appendix D — Raw Experimental Results

Note: Will point to `results/consolidated-results.md`, `results/figures-data.md`, per-condition reports, and the five commit hashes (baseline `60365d2`, harness `7c88e75`, A `de03148`, B `7298ade`, C `a030a19`).
