# Literature Foundation — Verified Core References Only

Paper: "Agentic AI: A Comprehensive Study of Autonomous AI Agents, Their Architectures, Applications, Challenges, and Future Directions"

Scope note: only the 8 externally verified references below are treated as established literature. No DOI numbers, venues, or statistics have been invented. Where the verified record does not supply a venue, this is stated explicitly rather than filled in.

## Reference list (verified)

### [1] ReAct

- Full citation: Yao, Shunyu, et al. "ReAct: Synergizing Reasoning and Acting in Language Models." ICLR 2023.
- Authors: Yao, Shunyu, et al.
- Year: 2023
- Venue: International Conference on Learning Representations (ICLR), 2023 (as verified).
- Identifier: arXiv:2210.03629.
- Main contribution: Introduces the ReAct paradigm in which a language model interleaves verbal reasoning traces with actions (e.g., tool calls, environment interactions) and observations, enabling grounded multi-step problem solving.
- Supports paper section: ReAct (direct theoretical basis for Condition B); also Agent architectures and Software-engineering agents (reasoning/action loop applied to code tasks).

### [2] Multi-agent conversation

- Full citation: Wu, Qingyun, et al. "AutoGen: Enabling Next-Gen LLM Applications via Multi-Agent Conversation." arXiv:2308.08155.
- Authors: Wu, Qingyun, et al.
- Year: 2023 (per arXiv identifier; venue not specified in the verified record).
- Venue: Not specified in the verified record (preprint). No venue invented.
- Identifier: arXiv:2308.08155.
- Main contribution: Proposes a multi-agent conversation framework in which specialized LLM agents (e.g., with distinct roles) collaborate through dialogue to solve tasks, with support for human-in-the-loop and tool use.
- Supports paper section: Multi-agent systems (direct theoretical basis for Condition C role separation into Manager/Developer/Tester); also Agent architectures.

### [3] Taxonomy of AI agents vs. agentic AI

- Full citation: Sapkota, Ranjan, Konstantinos I. Roumeliotis, and Manoj Karkee. "AI Agents vs. Agentic AI: A Conceptual Taxonomy, Applications and Challenges." Information Fusion, Vol. 126, Part B, Article 103599, 2026.
- Authors: Sapkota, Ranjan; Roumeliotis, Konstantinos I.; Karkee, Manoj.
- Year: 2026.
- Venue: Information Fusion, Vol. 126, Part B, Article 103599.
- DOI: 10.1016/j.inffus.2025.103599.
- Main contribution: Provides a conceptual taxonomy distinguishing individual AI agents from agentic AI systems, with discussion of applications and challenges.
- Supports paper section: Agentic AI foundations (terminology and scope for the paper title); also Challenges and safety, and Future directions (as framed by the taxonomy).

### [4] Agentic AI survey

- Full citation: Abou Ali, Mohamad, Fadi Dornaika, and Jinan Charafeddine. "Agentic AI: A Comprehensive Survey of Architectures, Applications, and Future Directions." Artificial Intelligence Review, Vol. 59, Article 11, 2026.
- Authors: Abou Ali, Mohamad; Dornaika, Fadi; Charafeddine, Jinan.
- Year: 2026.
- Venue: Artificial Intelligence Review, Vol. 59, Article 11.
- DOI: 10.1007/s10462-025-11422-4.
- Main contribution: Comprehensive survey of agentic AI architectures, their applications, and future research directions.
- Supports paper section: Agentic AI foundations and Agent architectures (survey backbone); also Future directions and Challenges and safety (survey-level framing).

### [5] Evaluation of LLM-based agents (survey)

- Full citation: Yehudai, Asaf, et al. "Survey on Evaluation of LLM-based Agents." arXiv:2503.16416, 2025.
- Authors: Yehudai, Asaf, et al.
- Year: 2025.
- Venue: Not specified in the verified record (preprint). No venue invented.
- Identifier: arXiv:2503.16416.
- Main contribution: Surveys approaches to evaluating LLM-based agents, covering evaluation dimensions and methodological considerations.
- Supports paper section: Agent evaluation (methodology for fixed test suites, pass/fail criteria, and reporting standards used in our pilot).

### [6] Evaluation and benchmarking of LLM agents (survey)

- Full citation: Mohammadi, Mahmoud, Yipeng Li, Jane Lo, and Wendy Yip. "Evaluation and Benchmarking of LLM Agents: A Survey." arXiv:2507.21504, 2025.
- Authors: Mohammadi, Mahmoud; Li, Yipeng; Lo, Jane; Yip, Wendy.
- Year: 2025.
- Venue: Not specified in the verified record (preprint). No venue invented.
- Identifier: arXiv:2507.21504.
- Main contribution: Surveys evaluation and benchmarking practices for LLM agents, including benchmark design and comparison methodology.
- Supports paper section: Agent evaluation (benchmark design, single-run limitations, and why statistical claims require replication — directly supports our threats-to-validity discussion).

### [7] AgentBench

- Full citation: Liu, Xiao, et al. "AgentBench: Evaluating LLMs as Agents." arXiv:2308.03688.
- Authors: Liu, Xiao, et al.
- Year: 2023 (per arXiv identifier; venue not specified in the verified record).
- Venue: Not specified in the verified record (preprint). No venue invented.
- Identifier: arXiv:2308.03688.
- Main contribution: Introduces a benchmark suite for evaluating large language models as autonomous agents across diverse environments and tasks.
- Supports paper section: Agent evaluation and Software-engineering agents (precedent for task-based agent benchmarking that motivates our fixed 12-test REST API suite).

### [8] SWE-bench

- Full citation: Jimenez, Carlos E., et al. "SWE-bench: Can Language Models Resolve Real-World GitHub Issues?" International Conference on Learning Representations (ICLR), 2024.
- Authors: Jimenez, Carlos E., et al.
- Year: 2024.
- Venue: International Conference on Learning Representations (ICLR), 2024 (as verified).
- Identifier: arXiv:2310.06770.
- Main contribution: Introduces a benchmark based on real-world GitHub issues for measuring whether language models can resolve practical software-engineering problems, with test-based verification.
- Supports paper section: Software-engineering agents (closest precedent for our Student Management REST API pilot: real code task, fixed tests, autonomous resolution).

## Organization by paper area

### A. Agentic AI foundations

- [3] Sapkota et al. — taxonomy and scope of AI agents vs. agentic AI.
- [4] Abou Ali et al. — survey backbone for architectures, applications, future directions.

### B. Agent architectures

- [4] Abou Ali et al. — architecture survey.
- [1] Yao et al. — single-agent reasoning/action architecture.
- [2] Wu et al. — multi-agent conversational architecture.

### C. ReAct

- [1] Yao et al. — sole verified primary source for the ReAct paradigm in this foundation.

### D. Multi-agent systems

- [2] Wu et al. — sole verified primary source for multi-agent conversation in this foundation.

### E. Agent evaluation

- [5] Yehudai et al. — evaluation dimensions and methodology.
- [6] Mohammadi et al. — benchmarking practice, replication requirements, comparison methodology.
- [7] Liu et al. — task-based agent benchmarking precedent.
- [8] Jimenez et al. — test-verified software-task benchmarking precedent.

### F. Software-engineering agents

- [8] Jimenez et al. — GitHub-issue resolution benchmark.
- [7] Liu et al. — multi-environment agent evaluation including code settings.
- [1] Yao et al. — reasoning/action loop as applied to tool-using tasks including code.

### G. Challenges and safety

- [3] Sapkota et al. — challenges as taxonomized.
- [4] Abou Ali et al. — challenges and survey-level safety framing.
- [ADDITIONAL VERIFIED SOURCE NEEDED] — for any specific safety-incident, red-teaming, or alignment claim beyond survey-level framing; no such source is asserted here.

### H. Future directions

- [4] Abou Ali et al. — survey-articulated future directions.
- [3] Sapkota et al. — taxonomy-implied directions.
- [ADDITIONAL VERIFIED SOURCE NEEDED] — for any concrete roadmap claim (e.g., timelines, capability forecasts, or deployment statistics); no such claim is made here.
