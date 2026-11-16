package com.portfolio.site;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PortfolioController {

    private final JdbcTemplate banco;

    public PortfolioController(JdbcTemplate banco) {
        this.banco = banco;
    }

    @GetMapping("/tecnologias")
    public List<Tecnologia> listarTecnologias() {
        return List.of(
            new Tecnologia("PHP", "Linguagem", "Básico", "Estudei lógica, estruturas de decisão e orientação a objetos."),
            new Tecnologia("PDO", "Biblioteca", "Em estudo", "Usei pra conectar o PHP com o banco de dados."),
            new Tecnologia("Supabase", "Plataforma", "Em estudo", "Vi como usar o banco PostgreSQL da plataforma com PHP."),
            new Tecnologia("Java", "Linguagem", "Básico", "Fiz exercícios de tipos, Scanner, Math e orientação a objetos."),
            new Tecnologia("Swing", "Biblioteca", "Em estudo", "Usei pra fazer janelas e botões em Java."),
            new Tecnologia("NetBeans", "IDE", "Básico", "Usei nas atividades pra escrever e rodar código Java."),
            new Tecnologia("JDK, JRE e JVM", "Ambiente Java", "Em estudo", "Estudei as ferramentas pra desenvolver e o que é usado pra executar um programa Java."),
            new Tecnologia("Spring Boot", "Framework", "Em estudo", "Vi arquitetura MVC nas aulas e usei Spring Boot na API deste portfólio."),
            new Tecnologia("Spring Initializr", "Ferramenta", "Básico", "Usei pra começar um projeto e escolher as dependências."),
            new Tecnologia("GitHub Codespaces", "Ambiente on-line", "Básico", "Usei o editor e o terminal no navegador pra rodar a aplicação."),
            new Tecnologia("Git e GitHub", "Ferramenta", "Básico", "Guardo o código das atividades e entrego pelo link do repositório."),
            new Tecnologia("HTML", "Front-end", "Básico", "Usei pra fazer as partes da página e o formulário."),
            new Tecnologia("CSS", "Front-end", "Básico", "Usei pra arrumar o visual da página no computador e no celular."),
            new Tecnologia("JavaScript", "Linguagem", "Em estudo", "Usei nos filtros, no tema escuro e no formulário."),
            new Tecnologia("H2", "Banco de dados", "Em estudo", "Usei neste projeto pra guardar as mensagens do formulário."),
            new Tecnologia("JDBC", "API Java", "Em estudo", "Usei com o JdbcTemplate pra salvar as mensagens no H2.")
        );
    }

    @PostMapping("/contato")
    @ResponseStatus(HttpStatus.CREATED)
    public void receberMensagem(@Valid @RequestBody Mensagem dados) {
        banco.update(
            "INSERT INTO mensagens (nome, email, mensagem) VALUES (?, ?, ?)",
            dados.nome().strip(),
            dados.email().strip(),
            dados.mensagem().strip()
        );
    }

    public record Tecnologia(String nome, String categoria, String nivel, String descricao) {}

    public record Mensagem(
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Email @Size(max = 160) String email,
        @NotBlank @Size(max = 1000) String mensagem
    ) {}
}
