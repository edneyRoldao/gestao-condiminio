DROP DATABASE gestao_condominio_db;

CREATE DATABASE IF NOT EXISTS gestao_condominio_db;

USE gestao_condominio_db;

DROP TABLE receitas;
DROP TABLE despesas;
DROP TABLE unidades;
DROP TABLE usuarios;

CREATE TABLE usuarios (
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    nome            VARCHAR(255)    NOT NULL                            ,
    email           VARCHAR(255)    NOT NULL UNIQUE                     ,
    senha           VARCHAR(255)    NOT NULL                            ,
    admin           BOOLEAN         NOT NULL DEFAULT false
);

CREATE TABLE unidades (
    id          BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    tipo        VARCHAR(50)     NOT NULL                            ,
    registro    VARCHAR(255)    NOT NULL UNIQUE                     ,
    id_usuario  BIGINT
);

CREATE TABLE despesas (
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY         ,
    categoria       VARCHAR(100)    NOT NULL                                    ,
    descricao       VARCHAR(500)    NOT NULL                                    ,
    valor_original  DECIMAL(10, 2)  NOT NULL                                    ,
    valor_pago      DECIMAL(10, 2)  NULL                                        ,
    data_vencimento DATE            NOT NULL                                    ,
    data_pagamento  DATE            NULL                                        ,
    data_cricao     TIMESTAMP       NOT NULL DEFAULT NOW()                      ,
    id_usuario      BIGINT          NOT NULL                                    ,
    status          ENUM('PAGO', 'NAO_PAGO', 'ATRASADO', 'CANCELADO') NOT NULL  ,
    CONSTRAINT fk_despesas_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE receitas (
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    descricao       VARCHAR(255)    NOT NULL                            ,
    data_pagamento  DATE            NULL                                ,
    valor           DECIMAL(10, 2)  NOT NULL                            ,
    categoria       VARCHAR(255)    NOT NULL                            ,
    id_unidade      BIGINT          NULL                                ,
    data_vencimento DATE            NOT NULL                            ,
    id_usuario      BIGINT          NOT NULL                            ,
    data_cricao     TIMESTAMP       NOT NULL DEFAULT NOW()              ,
    CONSTRAINT fk_receitas_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
