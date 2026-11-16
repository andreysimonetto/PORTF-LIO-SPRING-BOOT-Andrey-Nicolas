# Portfólio do Andrey

Sou estudante do 3º ano do ensino médio e do curso técnico em Análise e Desenvolvimento de Sistemas. Fiz este portfólio para reunir o que já estudei e praticar a ligação entre uma página web, uma API e um banco de dados.

## Como abrir no GitHub Codespaces

1. No repositório, abra **Code**, selecione **Codespaces** e clique em **Create codespace on main**.
2. Quando o ambiente abrir, rode no terminal:

   ```bash
   mvn spring-boot:run
   ```

3. Na aba **Ports**, abra no navegador o endereço da porta **8080**.

Se você já tinha aberto o Codespace antes de receber a versão nova, pare o servidor com **Ctrl+C**, rode `git pull --ff-only origin main` e inicie de novo com `mvn spring-boot:run`.

## Como funciona

- A seção **O que eu estudei em 2026** resume as atividades dos três trimestres.
- A página usa HTML, CSS e JavaScript. Dá para filtrar tecnologias por categoria ou nível e alternar entre tema claro e escuro.
- O Spring Boot publica a página e disponibiliza `GET /api/tecnologias` e `POST /api/contato`.
- O formulário salva as mensagens no banco H2 do Codespace que está executando o site. O arquivo fica em `dados/`.

Para consultar as mensagens, pare a aplicação com **Ctrl+C** e, na pasta do repositório, rode os comandos abaixo. O arquivo do banco é criado na primeira execução da aplicação.

```bash
mvn dependency:copy-dependencies -DincludeArtifactIds=h2
java -cp 'target/dependency/*' org.h2.tools.Shell -url jdbc:h2:file:./dados/portfolio -user sa -password '' -sql 'SELECT * FROM mensagens ORDER BY enviada_em DESC'
```
