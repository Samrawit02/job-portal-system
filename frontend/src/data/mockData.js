export const MOCK_CATEGORIES = [
  { id: 1, name: "Software Engineering", icon: "Code", count: 48 },
  { id: 2, name: "Cloud & DevOps", icon: "Cloud", count: 24 },
  { id: 3, name: "Data Science & AI", icon: "Cpu", count: 32 },
  { id: 4, name: "Product & Project", icon: "Briefcase", count: 19 },
  { id: 5, name: "UI/UX & Design", icon: "Palette", count: 15 },
  { id: 6, name: "Cybersecurity", icon: "Shield", count: 12 },
];

export const MOCK_COMPANIES = [
  {
    id: 1,
    name: "TechNova Solutions",
    slug: "technova-solutions",
    logo: "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?w=150&auto=format&fit=crop&q=80",
    banner: "https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=1200&auto=format&fit=crop&q=80",
    industry: "Enterprise Software",
    companyType: "PUBLIC",
    companyStatus: "VERIFIED",
    website: "https://technova.io",
    city: "San Francisco",
    country: "USA",
    employeeCount: "500 - 1000",
    foundedYear: 2017,
    about: "TechNova is a premier cloud innovation leader developing high-throughput microservice infrastructure and collaborative developer tooling for millions of global developers."
  },
  {
    id: 2,
    name: "CloudScale Systems",
    slug: "cloudscale-systems",
    logo: "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=150&auto=format&fit=crop&q=80",
    banner: "https://images.unsplash.com/photo-1497366216548-37526070297c?w=1200&auto=format&fit=crop&q=80",
    industry: "Cloud Infrastructure",
    companyType: "PRIVATE",
    companyStatus: "VERIFIED",
    website: "https://cloudscale.net",
    city: "Austin",
    country: "USA",
    employeeCount: "250 - 500",
    foundedYear: 2019,
    about: "Pioneering distributed serverless platforms with automated reliability and zero-overhead observability for Kubernetes deployments."
  },
  {
    id: 3,
    name: "NexaFin Global",
    slug: "nexafin-global",
    logo: "https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=150&auto=format&fit=crop&q=80",
    banner: "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?w=1200&auto=format&fit=crop&q=80",
    industry: "FinTech & Payments",
    companyType: "CORPORATE",
    companyStatus: "VERIFIED",
    website: "https://nexafin.global",
    city: "New York",
    country: "USA",
    employeeCount: "1000+",
    foundedYear: 2015,
    about: "Powering real-time global settlement networks with sub-millisecond latency, bank-grade encryption, and regulatory automation."
  },
  {
    id: 4,
    name: "QuantumAI Labs",
    slug: "quantumai-labs",
    logo: "https://images.unsplash.com/photo-1620712943543-bcc4688e7485?w=150&auto=format&fit=crop&q=80",
    banner: "https://images.unsplash.com/photo-1531482615713-2afd69097998?w=1200&auto=format&fit=crop&q=80",
    industry: "Artificial Intelligence",
    companyType: "STARTUP",
    companyStatus: "VERIFIED",
    website: "https://quantumai.ai",
    city: "Seattle",
    country: "USA",
    employeeCount: "50 - 100",
    foundedYear: 2022,
    about: "Building next-generation generative reasoning pipelines and autonomous enterprise AI agents."
  }
];

export const MOCK_JOBS = [
  {
    id: 1,
    title: "Senior Full Stack Engineer (Java & React)",
    description: "Join our core product team to design and scale enterprise-grade microservices and modern React applications handling tens of millions of monthly requests.",
    responsibilities: "- Architect resilient backend services with Spring Boot and Spring Cloud.\n- Build responsive, accessible UI modules using React, Tailwind CSS, and TypeScript.\n- Collaborate across engineering, product design, and DevOps to ship high-impact features bi-weekly.\n- Mentor junior and mid-level software developers through code reviews.",
    requirements: "- 5+ years of production experience in Java (Spring Boot) and modern React.\n- Deep understanding of RESTful API design, JWT security, and relational databases (PostgreSQL/MySQL).\n- Familiarity with containerization (Docker) and CI/CD workflows.\n- Strong communication skills and proactive problem-solving mindset.",
    benefits: "- Competitive compensation ($140,000 - $185,000) + Equity options\n- 100% remote flexibility with home office equipment stipend\n- Comprehensive health, dental, and vision insurance\n- $2,500 annual learning & conference budget\n- Unlimited PTO policy",
    company: MOCK_COMPANIES[0],
    employerId: 2,
    category: { id: 1, name: "Software Engineering" },
    skills: [
      { id: 1, name: "Java" },
      { id: 2, name: "Spring Boot" },
      { id: 3, name: "React" },
      { id: 4, name: "PostgreSQL" },
      { id: 5, name: "Tailwind CSS" }
    ],
    tags: [{ id: 1, name: "Full-Time" }, { id: 2, name: "High Growth" }],
    city: "San Francisco",
    state: "CA",
    country: "USA",
    address: "Market St, Suite 400",
    minSalary: 140000,
    maxSalary: 185000,
    jobType: "FULL_TIME",
    workMode: "REMOTE",
    experienceLevel: "SENIOR",
    status: "ACTIVE",
    openings: 2,
    applicationDeadline: "2026-10-15",
    active: true,
    createdAt: "2026-08-28T10:00:00"
  },
  {
    id: 2,
    title: "Cloud DevOps & Platform Architect",
    description: "Lead our cloud infrastructure modernization strategy. You will oversee Kubernetes cluster orchestration, multi-cloud connectivity, and GitOps delivery pipelines.",
    responsibilities: "- Build and maintain highly available Kubernetes clusters on AWS and GCP.\n- Create Terraform infrastructure-as-code modules for multi-tenant environments.\n- Optimize observability, monitoring dashboards (Prometheus/Grafana), and incident alerting.\n- Enforce zero-trust security postures and automated vulnerability audits.",
    requirements: "- 6+ years in DevOps, SRE, or Cloud Architecture.\n- Certified Kubernetes Administrator (CKA) or equivalent hands-on experience.\n- Expert-level proficiency with Terraform, Helm, ArgoCD, and Docker.\n- Strong scripting abilities in Python or Go.",
    benefits: "- Top tier base salary ($160,000 - $210,000) + performance bonuses\n- Comprehensive wellness and premium health coverage\n- Hybrid work model with dedicated modern workspace\n- 401(k) matching up to 6%",
    company: MOCK_COMPANIES[1],
    employerId: 2,
    category: { id: 2, name: "Cloud & DevOps" },
    skills: [
      { id: 6, name: "Kubernetes" },
      { id: 7, name: "AWS" },
      { id: 8, name: "Terraform" },
      { id: 9, name: "Docker" }
    ],
    tags: [{ id: 3, name: "Urgent Hiring" }, { id: 4, name: "Leadership" }],
    city: "Austin",
    state: "TX",
    country: "USA",
    address: "Congress Ave, 11th Floor",
    minSalary: 160000,
    maxSalary: 210000,
    jobType: "FULL_TIME",
    workMode: "HYBRID",
    experienceLevel: "LEAD",
    status: "ACTIVE",
    openings: 1,
    applicationDeadline: "2026-10-30",
    active: true,
    createdAt: "2026-08-30T14:30:00"
  },
  {
    id: 3,
    title: "Staff AI/ML Research Engineer",
    description: "Develop cutting-edge foundation model fine-tuning systems and multimodal agentic workflows for automated enterprise intelligence.",
    responsibilities: "- Formulate and benchmark custom fine-tuned transformer models for specialized reasoning tasks.\n- Implement high-speed vector retrieval pipelines (RAG) and semantic caching.\n- Profile model inference performance, quantization, and GPU memory usage.",
    requirements: "- M.S. or Ph.D. in Computer Science, Machine Learning, or related field (or equivalent experience).\n- 4+ years training and deploying large-scale neural network models.\n- Deep mastery of PyTorch, Hugging Face, LangChain, and Triton Inference Server.\n- Publications in NeurIPS/ICML/CVPR or significant open-source contributions are a plus.",
    benefits: "- Exceptional equity grant with rapid vesting\n- $175,000 - $230,000 salary package\n- Premium computing hardware (Dedicated H100 GPU clusters)\n- Flexible work setup and international conference attendance",
    company: MOCK_COMPANIES[3],
    employerId: 4,
    category: { id: 3, name: "Data Science & AI" },
    skills: [
      { id: 10, name: "Python" },
      { id: 11, name: "PyTorch" },
      { id: 12, name: "LLMs" },
      { id: 13, name: "Vector DBs" }
    ],
    tags: [{ id: 5, name: "Cutting Edge" }, { id: 6, name: "High Equity" }],
    city: "Seattle",
    state: "WA",
    country: "USA",
    address: "Pine St, Tower 2",
    minSalary: 175000,
    maxSalary: 230000,
    jobType: "FULL_TIME",
    workMode: "REMOTE",
    experienceLevel: "SENIOR",
    status: "ACTIVE",
    openings: 3,
    applicationDeadline: "2026-11-15",
    active: true,
    createdAt: "2026-09-01T09:15:00"
  },
  {
    id: 4,
    title: "Lead UI/UX Product Designer",
    description: "Define the design language and cohesive visual system across web and mobile surfaces for financial transactions and analytics.",
    responsibilities: "- Create modular design systems in Figma and establish atomic tokens.\n- Conduct qualitative user research, usability testing, and wireframe prototypes.\n- Partner directly with frontend engineers to ensure pixel-perfect realization.",
    requirements: "- 5+ years crafting SaaS web and mobile applications.\n- Strong portfolio demonstrating complex dashboard systems and intuitive UX flows.\n- Mastery of Figma, auto-layout, interactive components, and token variables.",
    benefits: "- $125,000 - $165,000 salary\n- Annual design conference budget\n- Flexible hours and generous parental leave",
    company: MOCK_COMPANIES[2],
    employerId: 3,
    category: { id: 5, name: "UI/UX & Design" },
    skills: [
      { id: 14, name: "Figma" },
      { id: 15, name: "Design Systems" },
      { id: 16, name: "User Research" }
    ],
    tags: [{ id: 7, name: "Fintech" }],
    city: "New York",
    state: "NY",
    country: "USA",
    address: "Wall Street, 22nd Floor",
    minSalary: 125000,
    maxSalary: 165000,
    jobType: "FULL_TIME",
    workMode: "HYBRID",
    experienceLevel: "MID",
    status: "ACTIVE",
    openings: 1,
    applicationDeadline: "2026-10-20",
    active: true,
    createdAt: "2026-09-02T11:00:00"
  },
  {
    id: 5,
    title: "Backend Java Microservices Developer",
    description: "Help scale our transaction ledger and secure payment gateway infrastructure. Focus on distributed transactions, event messaging (Kafka), and microservices.",
    responsibilities: "- Implement idempotent REST and gRPC services in Java 21 & Spring Boot.\n- Stream real-time ledger updates via Apache Kafka and Redis cluster.\n- Write rigorous unit, integration, and load tests.",
    requirements: "- 3+ years writing enterprise Java microservices.\n- Proven experience with Kafka or RabbitMQ event streaming.\n- Relational DB query optimization (indexing, locks, query plans).",
    benefits: "- $130,000 - $160,000 base salary\n- Yearly performance bonus\n- Full medical/dental/vision coverage",
    company: MOCK_COMPANIES[2],
    employerId: 3,
    category: { id: 1, name: "Software Engineering" },
    skills: [
      { id: 1, name: "Java" },
      { id: 2, name: "Spring Boot" },
      { id: 17, name: "Kafka" },
      { id: 18, name: "PostgreSQL" }
    ],
    tags: [{ id: 8, name: "Payments" }],
    city: "New York",
    state: "NY",
    country: "USA",
    address: "Broadway, Suite 1200",
    minSalary: 130000,
    maxSalary: 160000,
    jobType: "FULL_TIME",
    workMode: "ONSITE",
    experienceLevel: "MID",
    status: "ACTIVE",
    openings: 2,
    applicationDeadline: "2026-10-25",
    active: true,
    createdAt: "2026-09-03T16:00:00"
  }
];

export const MOCK_RESUME = {
  id: 101,
  candidateId: 1,
  title: "Full Stack Software Engineer",
  summary: "Results-driven Software Engineer with 4+ years of experience building high-performance web applications, distributed REST microservices, and modern user interfaces with React and Java Spring Boot.",
  personalInfo: {
    fullName: "Alex Rivera",
    email: "alex.rivera@example.com",
    phone: "+1 (555) 349-8812",
    city: "San Francisco",
    state: "CA",
    country: "USA",
    linkedinUrl: "https://linkedin.com/in/alex-rivera-dev",
    githubUrl: "https://github.com/alexrivera-code",
    portfolioUrl: "https://alexrivera.dev"
  },
  workExperiences: [
    {
      id: 1,
      jobTitle: "Software Engineer",
      companyName: "Vortex Technologies",
      location: "San Francisco, CA",
      startDate: "2022-06-01",
      endDate: null,
      current: true,
      description: "Spearheaded development of real-time collaboration dashboards using React and Spring Boot. Reduced API latency by 35% through Redis caching and query indexing."
    },
    {
      id: 2,
      jobTitle: "Junior Frontend Developer",
      companyName: "PixelCraft Digital",
      location: "San Jose, CA",
      startDate: "2020-08-01",
      endDate: "2022-05-31",
      current: false,
      description: "Implemented responsive web portals using modern React and Tailwind CSS. Built reusable component design libraries adopted across 5 product verticals."
    }
  ],
  education: [
    {
      id: 1,
      institution: "University of California, Berkeley",
      degree: "Bachelor of Science",
      fieldOfStudy: "Computer Science",
      startDate: "2016-09-01",
      endDate: "2020-05-31",
      grade: "3.85 GPA"
    }
  ],
  skills: [
    { id: 1, skillName: "React", proficiency: "EXPERT" },
    { id: 2, skillName: "Java & Spring Boot", proficiency: "ADVANCED" },
    { id: 3, skillName: "TypeScript", proficiency: "ADVANCED" },
    { id: 4, skillName: "Tailwind CSS", proficiency: "EXPERT" },
    { id: 5, skillName: "Docker", proficiency: "INTERMEDIATE" },
    { id: 6, skillName: "PostgreSQL", proficiency: "ADVANCED" }
  ],
  projects: [
    {
      id: 1,
      projectName: "Job Portal Cloud System",
      description: "A distributed microservice job portal featuring Spring Cloud Gateway, JWT authentication, Eureka Discovery, and a modern React UI.",
      technologies: "Spring Boot, React, Tailwind CSS, Docker, PostgreSQL",
      liveUrl: "https://github.com/Samrawit02/job-portal-system"
    }
  ],
  languages: [
    { id: 1, language: "English", proficiency: "NATIVE" },
    { id: 2, language: "Spanish", proficiency: "PROFICIENT" }
  ],
  isDefault: true
};

export const DEMO_USERS = {
  seeker: {
    id: 1,
    fullName: "Alex Rivera",
    email: "seeker@demo.com",
    role: "ROLE_JOB_SEEKER",
    phone: "+1 (555) 349-8812",
    avatar: "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=120&auto=format&fit=crop&q=80"
  },
  employer: {
    id: 2,
    fullName: "Sarah Vance (Recruiter)",
    email: "recruiter@technova.io",
    role: "ROLE_EMPLOYER",
    companyId: 1,
    companyName: "TechNova Solutions",
    phone: "+1 (555) 890-1234",
    avatar: "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?w=120&auto=format&fit=crop&q=80"
  },
  admin: {
    id: 99,
    fullName: "System Administrator",
    email: "admin@careerhub.com",
    role: "ROLE_ADMIN",
    phone: "+1 (555) 000-1111",
    avatar: "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=120&auto=format&fit=crop&q=80"
  }
};
