CREATE TABLE tb_user (
    code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(120) NOT NULL,
    active BOOLEAN DEFAULT true,
    birth_date DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_group (
    code BIGINT(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_permission (
    code BIGINT(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_group (
    code_user BIGINT(20) NOT NULL,
    code_group BIGINT(20) NOT NULL,
    PRIMARY KEY (code_user, code_group),
    FOREIGN KEY (code_user) REFERENCES tb_user(code),
    FOREIGN KEY (code_group) REFERENCES tb_group(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE group_permission (
    code_group BIGINT(20) NOT NULL,
    code_permission BIGINT(20) NOT NULL,
    PRIMARY KEY (code_group, code_permission),
    FOREIGN KEY (code_group) REFERENCES tb_group(code),
    FOREIGN KEY (code_permission) REFERENCES tb_permission(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;