package com.portfolio.site;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class PortfolioController {

    private final JdbcTemplate banco;

    public PortfolioController(JdbcTemplate banco) {
        this.banco = banco;
    }

    @GetMapping("/tecnologias")
    public List<Tecnologia> listarTecnologias() {
        return banco.query(
            "SELECT id, nome, categoria, nivel, descricao FROM tecnologias ORDER BY id",
            (linha, numero) -> new Tecnologia(
                linha.getLong("id"), linha.getString("nome"), linha.getString("categoria"),
                linha.getString("nivel"), linha.getString("descricao")
            )
        );
    }

    @GetMapping("/projetos")
    public List<Projeto> listarProjetos() {
        return banco.query(
            "SELECT id, nome, descricao, link FROM projetos ORDER BY id",
            (linha, numero) -> new Projeto(
                linha.getLong("id"), linha.getString("nome"), linha.getString("descricao"), linha.getString("link")
            )
        );
    }

    @GetMapping("/admin/csrf")
    public CsrfToken token(CsrfToken token) {
        return token;
    }

    @PostMapping("/admin/tecnologias")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarTecnologia(@Valid @RequestBody Tecnologia dados) {
        banco.update(
            "INSERT INTO tecnologias (nome, categoria, nivel, descricao) VALUES (?, ?, ?, ?)",
            dados.nome().strip(), dados.categoria().strip(), dados.nivel().strip(), dados.descricao().strip()
        );
    }

    @PutMapping("/admin/tecnologias/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarTecnologia(@PathVariable long id, @Valid @RequestBody Tecnologia dados) {
        exigirRegistro(banco.update(
            "UPDATE tecnologias SET nome = ?, categoria = ?, nivel = ?, descricao = ? WHERE id = ?",
            dados.nome().strip(), dados.categoria().strip(), dados.nivel().strip(), dados.descricao().strip(), id
        ));
    }

    @PostMapping("/admin/projetos")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarProjeto(@Valid @RequestBody Projeto dados) {
        banco.update(
            "INSERT INTO projetos (nome, descricao, link) VALUES (?, ?, ?)",
            dados.nome().strip(), dados.descricao().strip(), dados.link().strip()
        );
    }

    @PutMapping("/admin/projetos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarProjeto(@PathVariable long id, @Valid @RequestBody Projeto dados) {
        exigirRegistro(banco.update(
            "UPDATE projetos SET nome = ?, descricao = ?, link = ? WHERE id = ?",
            dados.nome().strip(), dados.descricao().strip(), dados.link().strip(), id
        ));
    }

    private void exigirRegistro(int alterados) {
        if (alterados == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
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

    public record Tecnologia(
        Long id,
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Size(max = 60) String categoria,
        @NotBlank @Size(max = 40) String nivel,
        @NotBlank @Size(max = 600) String descricao
    ) {}

    public record Projeto(
        Long id,
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Size(max = 600) String descricao,
        @NotBlank @Size(max = 500) @Pattern(regexp = "https?://[^/\\s]+(?:/[^\\s]*)?") String link
    ) {}

    public record Mensagem(
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Email @Size(max = 160) String email,
        @NotBlank @Size(max = 1000) String mensagem
    ) {}
}
