# Automata Project

Java implementations of formal-language concepts from an **Automata Theory** course: deterministic finite automata (DFA) simulators and a **context-free grammar (CFG) → pushdown automaton (PDA)** converter.

## Features

### DFA simulators (`src/DFA/`)

| Program | Language accepted |
|---------|-------------------|
| `EvenOneEvenZero.java` | Binary strings with an **even** number of `1`s and an **even** number of `0`s |
| `ThreeConsecutiveZeros.java` | Binary strings containing **three consecutive zeros** |

Each program reads a binary string from standard input and reports whether the string is accepted or rejected.

### CFG → PDA conversion (`src/Convert/`)

| Class | Role |
|-------|------|
| `CFG.java` | Context-free grammar representation |
| `PDA.java` | Pushdown automaton data structure |
| `CFG_To_PDA_Converter.java` | Standard construction: CFG → equivalent PDA |

The converter builds a two-state PDA that non-deterministically expands productions and matches terminals.

## Requirements

- **Java 11+** (module project)
- Eclipse IDE recommended (`.project`, `.classpath`, and `.settings` included)

## Running

### From Eclipse

1. Import the project: **File → Import → Existing Projects into Workspace**
2. Run any class with a `main` method (e.g. `DFA.EvenOneEvenZero`)

### From the command line

```bash
# Compile (from project root)
javac -d bin src/module-info.java src/DFA/*.java src/Convert/*.java

# Run a DFA example
java -cp bin DFA.EvenOneEvenZero
```

When prompted, enter a binary string (e.g. `1010`).

## Project structure

```
Automata_Project/
├── src/
│   ├── module-info.java
│   ├── DFA/
│   │   ├── EvenOneEvenZero.java
│   │   └── ThreeConsecutiveZeros.java
│   └── Convert/
│       ├── CFG.java
│       ├── PDA.java
│       └── CFG_To_PDA_Converter.java
├── .project / .classpath   # Eclipse configuration
└── README.md
```

## Author

**Ali Kele** — [dancinghyena](https://github.com/dancinghyena)

## License

Academic / coursework project.
