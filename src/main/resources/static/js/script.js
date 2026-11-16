const categoria = document.querySelector("#categoria");
const nivel = document.querySelector("#nivel");
const lista = document.querySelector("#lista-tecnologias");
const estadoTecnologias = document.querySelector("#estado-tecnologias");
const formulario = document.querySelector("#formulario");
const estadoContato = document.querySelector("#estado-contato");
const botaoEnviar = formulario.querySelector("button");
const botaoTema = document.querySelector("#tema");

let tecnologias = [];

function adicionarOpcoes(campo, valores) {
    for (const valor of new Set(valores)) {
        campo.add(new Option(valor, valor));
    }
}

function mostrarTecnologias() {
    const filtradas = tecnologias.filter(tecnologia =>
        (!categoria.value || tecnologia.categoria === categoria.value) &&
        (!nivel.value || tecnologia.nivel === nivel.value)
    );

    lista.replaceChildren();
    estadoTecnologias.textContent = filtradas.length ? "" : "Nenhuma tecnologia com esses filtros.";

    for (const tecnologia of filtradas) {
        const cartao = document.createElement("article");
        cartao.className = "cartao";

        const titulo = document.createElement("h3");
        titulo.textContent = tecnologia.nome;

        const detalhe = document.createElement("p");
        detalhe.className = "detalhe";
        detalhe.textContent = tecnologia.categoria + " · " + tecnologia.nivel;

        const descricao = document.createElement("p");
        descricao.textContent = tecnologia.descricao;

        cartao.append(titulo, detalhe, descricao);
        lista.append(cartao);
    }
}

async function carregarTecnologias() {
    try {
        const resposta = await fetch("/api/tecnologias");
        if (!resposta.ok) {
            throw new Error("Falha na API");
        }

        tecnologias = await resposta.json();
        adicionarOpcoes(categoria, tecnologias.map(tecnologia => tecnologia.categoria));
        adicionarOpcoes(nivel, tecnologias.map(tecnologia => tecnologia.nivel));
        categoria.addEventListener("change", mostrarTecnologias);
        nivel.addEventListener("change", mostrarTecnologias);
        mostrarTecnologias();
    } catch {
        estadoTecnologias.textContent = "Não consegui carregar as tecnologias. Atualize a página para tentar de novo.";
    }
}

formulario.addEventListener("submit", async (evento) => {
    evento.preventDefault();

    const dados = {};
    for (const [campo, valor] of new FormData(formulario)) {
        dados[campo] = valor.trim();
    }

    if (!dados.nome || !dados.email || !dados.mensagem) {
        estadoContato.textContent = "Preencha todos os campos.";
        return;
    }

    botaoEnviar.disabled = true;
    estadoContato.textContent = "Enviando…";

    try {
        const resposta = await fetch("/api/contato", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados)
        });

        if (resposta.status === 400) {
            estadoContato.textContent = "Confira o nome, o e-mail e a mensagem.";
            return;
        }

        if (!resposta.ok) {
            throw new Error("Falha no envio");
        }

        formulario.reset();
        estadoContato.textContent = "Mensagem recebida!";
    } catch {
        estadoContato.textContent = "Não consegui enviar a mensagem. Tente de novo.";
    } finally {
        botaoEnviar.disabled = false;
    }
});

function aplicarTema(escuro) {
    document.documentElement.dataset.tema = escuro ? "escuro" : "claro";
    botaoTema.setAttribute("aria-pressed", String(escuro));
}

aplicarTema(localStorage.getItem("tema") === "escuro");

botaoTema.addEventListener("click", () => {
    const escuro = document.documentElement.dataset.tema !== "escuro";
    aplicarTema(escuro);
    localStorage.setItem("tema", escuro ? "escuro" : "claro");
});

carregarTecnologias();
