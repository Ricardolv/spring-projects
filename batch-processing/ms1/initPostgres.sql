CREATE TABLE IF NOT EXISTS "tb_dados_base_1" (
    "id" int NOT NULL GENERATED ALWAYS AS IDENTITY,
    "nome" varchar(100) NOT NULL,
    "descricao" varchar(200) NOT NULL,
    "email" varchar(100) NOT NULL,
    "senha" varchar(200) NOT NULL,
    PRIMARY KEY ("id")
);

INSERT INTO tb_dados_base_1 (nome, descricao, email, senha) VALUES ('NOME 1', 'Descrição 1', 'nome1.teste@teste.com', '123');
INSERT INTO tb_dados_base_1 (nome, descricao, email, senha) VALUES ('NOME 2', 'Descrição 2', 'nome2.teste@teste.com', '123');
INSERT INTO tb_dados_base_1 (nome, descricao, email, senha) VALUES ('NOME 3', 'Descrição 3', 'nome3.teste@teste.com', '123');