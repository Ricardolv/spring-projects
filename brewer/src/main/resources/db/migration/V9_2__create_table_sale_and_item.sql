CREATE TABLE sale (
    code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    creation_date DATETIME NOT NULL,
    freight_value DECIMAL(10,2),
    discount_value DECIMAL(10,2),
    total_value DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    note VARCHAR(200),
    delivery_hour_date DATETIME,
    code_client BIGINT(20) NOT NULL,
    code_user BIGINT(20) NOT NULL,
    FOREIGN KEY (code_client) REFERENCES client(code),
    FOREIGN KEY (code_user) REFERENCES tb_user(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE sale_item (
    code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    quantity INTEGER NOT NULL,
    unitary_value DECIMAL(10,2) NOT NULL,
    code_beer BIGINT(20) NOT NULL,
    code_sale BIGINT(20) NOT NULL,
    FOREIGN KEY (code_beer) REFERENCES beer(code),
    FOREIGN KEY (code_sale) REFERENCES sale(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;