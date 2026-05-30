# Automata Project

Java implementations of formal-language concepts from an **Automata Theory** course: deterministic finite automata (DFA) simulators and a **context-free grammar (CFG) → pushdown automaton (PDA)** converter with a **Spring Boot** backend and web UI.

## Features

### CFG → PDA web app

- Enter a start symbol and productions in the browser
- Built-in examples (aⁿbⁿ, palindromes, a*, and more)
- View the resulting PDA states and transitions

**Run the app:**

```bash
mvn spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080) in your browser.

**REST API:** `POST /api/cfg-to-pda` with JSON body:

```json
{
  "startSymbol": "S",
  "productions": [
    { "left": "S", "alternatives": ["aSb", "ε"] }
  ]
}
```

Use `ε`, `epsilon`, or an empty string for epsilon productions.

### DFA simulators (`src/main/java/DFA/`)

| Program | Language accepted |
|---------|-------------------|
| `EvenOneEvenZero.java` | Binary strings with an **even** number of `1`s and an **even** number of `0`s |
| `ThreeConsecutiveZeros.java` | Binary strings containing **three consecutive zeros** |

Run from the IDE or with Maven:

```bash
mvn -q exec:java -Dexec.mainClass=DFA.EvenOneEvenZero
```

### CFG → PDA conversion (`src/main/java/Convert/`)

| Class | Role |
|-------|------|
| `CFG.java` | Context-free grammar representation |
| `PDA.java` | Pushdown automaton data structure |
| `CFG_To_PDA_Converter.java` | Standard construction: CFG → equivalent PDA |

The converter builds a PDA that non-deterministically expands productions and matches terminals.

## Requirements

- **Java 21+**
- **Maven 3.9+** (for Spring Boot and the web UI)
- Eclipse or IntelliJ (optional; import as a Maven project)

## Project structure

```
Automata_Project/
├── pom.xml
├── src/main/java/
│   ├── com/automata/          # Spring Boot application
│   │   ├── AutomataApplication.java
│   │   └── web/               # REST API
│   ├── Convert/               # CFG → PDA core logic
│   └── DFA/                   # Console DFA examples
└── src/main/resources/
    ├── application.properties
    └── static/                # Web UI (index.html, app.js, styles.css)
```

## License

Academic / coursework project.
