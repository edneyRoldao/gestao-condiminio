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