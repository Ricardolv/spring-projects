
INSERT INTO tb_permission VALUES (1, 'ROLE_REGISTER_CITY');
INSERT INTO tb_permission VALUES (2, 'ROLE_REGISTER_USER');
INSERT INTO tb_permission VALUES (3, 'ROLE_REGISTER_BEER');
INSERT INTO tb_permission VALUES (4, 'ROLE_REGISTER_CLIENT');
INSERT INTO tb_permission VALUES (5, 'ROLE_REGISTER_STYLE');

INSERT INTO group_permission (code_group, code_permission) VALUES (1, 1);
INSERT INTO group_permission (code_group, code_permission) VALUES (1, 2);
INSERT INTO group_permission (code_group, code_permission) VALUES (1, 3);
INSERT INTO group_permission (code_group, code_permission) VALUES (1, 4);
INSERT INTO group_permission (code_group, code_permission) VALUES (1, 5);

INSERT INTO user_group (code_user, code_group) VALUES (
	(SELECT code FROM tb_user WHERE email = 'admin@brewer.com'), 1);