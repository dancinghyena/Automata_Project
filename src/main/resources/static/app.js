const PRESETS = {
    anbn: {
        startSymbol: "S",
        productions: [{ left: "S", alternatives: ["aSb", "ε"] }],
    },
    palindrome: {
        startSymbol: "S",
        productions: [{ left: "S", alternatives: ["aSa", "bSb", "ε"] }],
    },
    ab: {
        startSymbol: "S",
        productions: [
            { left: "S", alternatives: ["AB"] },
            { left: "A", alternatives: ["a"] },
            { left: "B", alternatives: ["b"] },
        ],
    },
    astar: {
        startSymbol: "S",
        productions: [{ left: "S", alternatives: ["aS", "ε"] }],
    },
};

const productionsEl = document.getElementById("productions");
const startSymbolEl = document.getElementById("startSymbol");
const presetEl = document.getElementById("preset");
const errorEl = document.getElementById("error");
const resultsEl = document.getElementById("results");
const statesBodyEl = document.getElementById("statesBody");
const transitionsBodyEl = document.getElementById("transitionsBody");

function createProductionRow(left = "S", alternatives = "ε") {
    const row = document.createElement("div");
    row.className = "production-row";
    row.innerHTML = `
        <input type="text" class="lhs input-sm" maxlength="1" value="${left}" aria-label="Variable">
        <span class="arrow">→</span>
        <input type="text" class="rhs" value="${alternatives}" placeholder="aSb, ε" aria-label="Alternatives">
        <button type="button" class="btn btn-icon remove" title="Remove production">×</button>
    `;
    row.querySelector(".remove").addEventListener("click", () => {
        if (productionsEl.children.length > 1) {
            row.remove();
        }
    });
    return row;
}

function clearProductions() {
    productionsEl.innerHTML = "";
}

function loadProductions(productions) {
    clearProductions();
    productions.forEach((p) => {
        productionsEl.appendChild(
            createProductionRow(p.left, p.alternatives.join(", "))
        );
    });
}

function showError(message) {
    errorEl.textContent = message;
    errorEl.hidden = !message;
}

function collectRequest() {
    const startSymbol = startSymbolEl.value.trim();
    const productions = [];

    for (const row of productionsEl.querySelectorAll(".production-row")) {
        const left = row.querySelector(".lhs").value.trim();
        const rhs = row.querySelector(".rhs").value.trim();
        const alternatives = rhs
            ? rhs.split(",").map((s) => s.trim())
            : ["ε"];
        productions.push({ left, alternatives });
    }

    return { startSymbol, productions };
}

function boolTag(value) {
    return value
        ? '<span class="tag tag-yes">yes</span>'
        : '<span class="tag tag-no">no</span>';
}

function renderResults(data) {
    statesBodyEl.innerHTML = data.states
        .map(
            (s) => `
        <tr>
            <td class="mono">q${s.id}</td>
            <td>${boolTag(s.start)}</td>
            <td>${boolTag(s.accepting)}</td>
        </tr>`
        )
        .join("");

    transitionsBodyEl.innerHTML = data.transitions
        .map((t) => {
            const push =
                t.push.length === 0 ? "ε" : t.push.join("");
            return `
        <tr>
            <td class="mono">q${t.from}</td>
            <td class="mono">q${t.to}</td>
            <td class="mono">${t.input}</td>
            <td class="mono">${t.pop}</td>
            <td class="mono">${push}</td>
        </tr>`;
        })
        .join("");

    resultsEl.hidden = false;
    resultsEl.scrollIntoView({ behavior: "smooth", block: "nearest" });
}

async function convert() {
    showError("");
    const body = collectRequest();

    try {
        const response = await fetch("/api/cfg-to-pda", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body),
        });

        const payload = await response.json();
        if (!response.ok) {
            showError(payload.error || "Conversion failed.");
            return;
        }

        renderResults(payload);
    } catch {
        showError("Could not reach the server. Is the Spring Boot app running?");
    }
}

document.getElementById("addProduction").addEventListener("click", () => {
    productionsEl.appendChild(createProductionRow("A", ""));
});

document.getElementById("convert").addEventListener("click", convert);

presetEl.addEventListener("change", () => {
    const preset = PRESETS[presetEl.value];
    if (!preset) return;
    startSymbolEl.value = preset.startSymbol;
    loadProductions(preset.productions);
    showError("");
});

loadProductions(PRESETS.anbn.productions);
