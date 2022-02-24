CREATE TABLE client (
    code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    person_type VARCHAR(15) NOT NULL,
    cpf_cnpj VARCHAR(30),
    phone VARCHAR(20),
    email VARCHAR(50) NOT NULL,
    address_street VARCHAR(50),
    address_number VARCHAR(15),
    address_complement VARCHAR(20),
    address_zipcode VARCHAR(15),
    code_city BIGINT(20),
    FOREIGN KEY (code_city) REFERENCES city(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;