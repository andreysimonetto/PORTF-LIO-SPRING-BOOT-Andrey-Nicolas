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
            new Tecnologia("HTML", "Front-end", "Básico", "Uso para montar a estrutura das páginas."),
            new Tecnologia("CSS", "Front-end", "Básico", "Uso para dar estilo às páginas e ajustar o layout para o celular."),
            new Tecnologia("JavaScript", "Linguagem", "Em estudo", "Usei nos filtros, no tema escuro e no envio do formulário."),
            new Tecnologia("Java", "Linguagem", "Básico", "Pratico classes, objetos e métodos nos exercícios do curso."),
            new Tecnologia("PHP", "Linguagem", "Básico", "Já fiz exercícios de lógica, orientação a objetos e cadastro com PHP."),
            new Tecnologia("Spring Boot", "Framework", "Em estudo", "Usei para criar a API e receber as mensagens deste site."),
            new Tecnologia("H2", "Banco de dados", "Em estudo", "Guarda as mensagens do formulário em um arquivo local."),
            new Tecnologia("JDBC", "API Java", "Em estudo", "Usei com o JdbcTemplate para gravar mensagens no banco."),
            new Tecnologia("VS Code", "Editor", "Básico", "Ambiente que uso no navegador pelo GitHub Codespaces."),
            new Tecnologia("Git e GitHub", "Ferramenta", "Básico", "Uso para guardar versões e entregar atividades do curso.")
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
