CREATE TABLE usuarios (
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    nome            VARCHAR(255)    NOT NULL                            ,
    email           VARCHAR(255)    NOT NULL UNIQUE                     ,
    senha           VARCHAR(255)    NOT NULL                            ,
    admin           BOOLEAN         NOT NULL DEFAULT false              ,
    data_cricao TIMESTAMP       NOT NULL DEFAULT NOW()
);
