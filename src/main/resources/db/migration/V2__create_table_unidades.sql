CREATE TABLE unidades (
    id          BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    tipo        VARCHAR(50)     NOT NULL                            ,
    registro    VARCHAR(255)    NOT NULL UNIQUE                     ,
    id_usuario  BIGINT          NULL                                ,
    data_cricao TIMESTAMP       NOT NULL DEFAULT NOW()
);
