INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -16, 'PHP', 'Linguagem', 'Básico', 'Estudamos lógica, estruturas de decisão e orientação a objetos.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -16);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -15, 'PDO', 'Biblioteca', 'Em estudo', 'Usamos pra conectar o PHP com o banco de dados.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -15);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -14, 'Supabase', 'Plataforma', 'Em estudo', 'Vimos como usar o banco PostgreSQL da plataforma com PHP.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -14);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -13, 'Java', 'Linguagem', 'Básico', 'Fizemos exercícios de tipos, Scanner, Math e orientação a objetos.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -13);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -12, 'Swing', 'Biblioteca', 'Em estudo', 'Usamos pra fazer janelas e botões em Java.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -12);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -11, 'NetBeans', 'IDE', 'Básico', 'Usamos nas atividades pra escrever e rodar código Java.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -11);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -10, 'JDK, JRE e JVM', 'Ambiente Java', 'Em estudo', 'Estudamos as ferramentas pra desenvolver e o que é usado pra executar um programa Java.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -10);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -9, 'Spring Boot', 'Framework', 'Em estudo', 'Vimos arquitetura MVC nas aulas e usei Spring Boot na API deste portfólio.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -9);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -8, 'Spring Initializr', 'Ferramenta', 'Básico', 'Usamos pra começar um projeto e escolher as dependências.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -8);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -7, 'GitHub Codespaces', 'Ambiente on-line', 'Básico', 'Usamos o editor e o terminal no navegador pra rodar a aplicação.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -7);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -6, 'Git e GitHub', 'Ferramenta', 'Básico', 'Guardamos o código das atividades e entregamos pelo link do repositório.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -6);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -5, 'HTML', 'Front-end', 'Básico', 'Usamos pra fazer as partes da página e o formulário.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -5);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -4, 'CSS', 'Front-end', 'Básico', 'Usamos pra arrumar o visual da página no computador e no celular.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -4);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -3, 'JavaScript', 'Linguagem', 'Em estudo', 'Usamos nos filtros, no tema escuro e no formulário.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -3);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -2, 'H2', 'Banco de dados', 'Em estudo', 'Usamos neste projeto pra guardar as mensagens do formulário.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -2);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -1, 'JDBC', 'API Java', 'Em estudo', 'Usamos com o JdbcTemplate pra salvar as mensagens no H2.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -1);

INSERT INTO projetos (id, nome, descricao, link)
SELECT -2, 'Exercícios de orientação a objetos', 'Colocamos em pratica o que vimos de encapsulamento, herança, polimorfismo e abstração.', 'https://github.com/andreysimonetto/EncapsulamentoHerancaPolimorfismoAbstracao'
WHERE NOT EXISTS (SELECT 1 FROM projetos WHERE id = -2);

INSERT INTO projetos (id, nome, descricao, link)
SELECT -1, 'Cadastro em PHP', 'Um exercício pra cadastrar, ver, editar e apagar registros usando PHP e Supabase.', 'https://github.com/andreysimonetto/CRUDPHPSubase'
WHERE NOT EXISTS (SELECT 1 FROM projetos WHERE id = -1);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -21, 'SQL', 'Linguagem', 'Básico', 'Usamos SELECT, JOIN, ORDER BY e funções de agregação.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -21);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -20, 'Firebase', 'Plataforma', 'Em estudo', 'Vimos como enviar, ler, editar e excluir dados.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -20);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -19, 'C++', 'Linguagem', 'Básico', 'Praticamos vetores, matrizes, funções, pilha e fila.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -19);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -18, 'Python', 'Linguagem', 'Básico', 'Praticamos classes, objetos e herança.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -18);

INSERT INTO tecnologias (id, nome, categoria, nivel, descricao)
SELECT -17, 'Tkinter', 'Biblioteca', 'Em estudo', 'Usamos pra montar uma tela em Python.'
WHERE NOT EXISTS (SELECT 1 FROM tecnologias WHERE id = -17);
