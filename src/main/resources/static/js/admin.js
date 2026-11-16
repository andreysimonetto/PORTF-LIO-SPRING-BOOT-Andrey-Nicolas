async function respostaAdmin(url, opcoes) {
    const resposta = await fetch(url, opcoes);
    if (resposta.redirected) {
        window.location.assign("/admin.html");
        throw new Error("Entre de novo pra continuar.");
    }
    return resposta;
}

async function prepararSecao(nome, csrf) {
    const secao = document.querySelector("#" + nome + "-admin");
    const form = secao.querySelector("form");
    const lista = secao.querySelector("ul");
    const estado = secao.querySelector("[role='status']");
    const botao = form.querySelector("[type='submit']");
    const cancelar = form.querySelector("[data-cancelar]");
    const campos = [...form.querySelectorAll("[name]")];
    const permiteCadastro = !form.hidden;
    let idEmEdicao = null;

    function limpar() {
        idEmEdicao = null;
        form.reset();
        botao.textContent = permiteCadastro ? "Cadastrar" : "Salvar edição";
        form.hidden = !permiteCadastro;
        cancelar.hidden = true;
    }

    async function atualizarLista() {
        const resposta = await respostaAdmin("/api/" + nome);
        if (!resposta.ok) {
            throw new Error("Não deu pra carregar os registros.");
        }

        const registros = await resposta.json();
        lista.replaceChildren();

        for (const registro of registros) {
            const item = document.createElement("li");
            const titulo = document.createElement("span");
            titulo.textContent = registro.nome;
            const editar = document.createElement("button");
            editar.textContent = "Editar";
            editar.type = "button";
            editar.addEventListener("click", () => {
                idEmEdicao = registro.id;
                form.hidden = false;
                for (const campo of campos) {
                    campo.value = registro[campo.name];
                }
                botao.textContent = "Salvar edição";
                cancelar.hidden = false;
                estado.textContent = "";
                campos[0].focus();
            });
            item.append(titulo, editar);
            lista.append(item);
        }
    }

    cancelar.addEventListener("click", () => {
        limpar();
        estado.textContent = "";
    });

    form.addEventListener("submit", async evento => {
        evento.preventDefault();

        const editando = idEmEdicao !== null;
        if (!editando && !permiteCadastro) {
            return;
        }

        const dados = Object.fromEntries(campos.map(campo => [campo.name, campo.value.trim()]));
        if (Object.values(dados).some(valor => !valor)) {
            estado.textContent = "Preencha os campos.";
            return;
        }

        const caminho = "/api/admin/" + nome + (editando ? "/" + idEmEdicao : "");
        botao.disabled = true;

        try {
            const resposta = await respostaAdmin(caminho, {
                method: editando ? "PUT" : "POST",
                headers: { "Content-Type": "application/json", [csrf.headerName]: csrf.token },
                body: JSON.stringify(dados)
            });
            if (!resposta.ok) {
                estado.textContent = resposta.status === 400
                    ? "Confira os dados preenchidos."
                    : "Não deu pra salvar.";
                return;
            }

            limpar();
            estado.textContent = "Salvo!";
            await atualizarLista();
        } catch (erro) {
            estado.textContent = erro.message;
        } finally {
            botao.disabled = false;
        }
    });

    await atualizarLista();
    secao.hidden = false;
}

async function iniciarAdmin() {
    const estado = document.querySelector("#estado-admin");

    try {
        const resposta = await respostaAdmin("/api/admin/csrf");
        if (!resposta.ok) {
            throw new Error("Não deu pra abrir o painel.");
        }

        const csrf = await resposta.json();
        const sair = document.querySelector("#sair");
        const token = document.createElement("input");
        token.type = "hidden";
        token.name = csrf.parameterName;
        token.value = csrf.token;
        sair.append(token);
        sair.hidden = false;

        await Promise.all([
            prepararSecao("tecnologias", csrf),
            prepararSecao("projetos", csrf)
        ]);
        estado.textContent = "";
    } catch (erro) {
        estado.textContent = erro.message;
    }
}

iniciarAdmin();
