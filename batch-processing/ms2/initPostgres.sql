CREATE TABLE IF NOT EXISTS "tb_dados_base_2" (
    "id" int NOT NULL GENERATED ALWAYS AS IDENTITY,
    "nome" varchar(100) NOT NULL,
    "descricao" varchar(200) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "tb_dados_base_3" (
     "id" int NOT NULL GENERATED ALWAYS AS IDENTITY,
     "email" varchar(100) NOT NULL,
     "senha" varchar(200) NOT NULL,
     PRIMARY KEY ("id")
);