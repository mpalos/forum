INSERT INTO PUBLIC.CURSO (CATEGORIA, NOME) VALUES('programação', 'java');
INSERT INTO PUBLIC.CURSO (CATEGORIA, NOME) VALUES('programação', 'python');
INSERT INTO PUBLIC.CURSO (CATEGORIA, NOME) VALUES('bi', 'powerbi');
INSERT INTO PUBLIC.CURSO (CATEGORIA, NOME) VALUES('programação', 'spring');

INSERT INTO PUBLIC.USUARIO (EMAIL, NOME, SENHA) VALUES('user1@email.com', 'Usuario 1', 'xxx');

INSERT INTO PUBLIC.TOPICO (DATA_CRIACAO, MENSAGEM, STATUS, TITULO, AUTOR_ID, CURSO_ID) VALUES('2019-07-25 18:00:00', 'Queria um script' ,'NAO_RESPONDIDO', 'Dúvida 1', 1, 1);
INSERT INTO PUBLIC.TOPICO (DATA_CRIACAO, MENSAGEM, STATUS, TITULO, AUTOR_ID, CURSO_ID) VALUES('2019-07-25 18:00:00', 'Esse curso não tem os arquivos' ,'NAO_RESPONDIDO', 'Dúvida 2', 1, 2);
INSERT INTO PUBLIC.TOPICO (DATA_CRIACAO, MENSAGEM, STATUS, TITULO, AUTOR_ID, CURSO_ID) VALUES('2019-07-25 18:00:00', 'Esse curso está ficando complicado' ,'NAO_RESPONDIDO', 'Dúvida 2', 1, 4);

INSERT INTO PUBLIC.RESPOSTA (DATA_CRIACAO, MENSAGEM, SOLUCAO, AUTOR_ID, TOPICO_ID) VALUES('2019-07-25 20:00:00', 'Tenta novamente', false, 1, 1);
INSERT INTO PUBLIC.RESPOSTA (DATA_CRIACAO, MENSAGEM, SOLUCAO, AUTOR_ID, TOPICO_ID) VALUES('2019-07-25 19:00:00', 'Faça na mão', false, 1, 1);
INSERT INTO PUBLIC.RESPOSTA (DATA_CRIACAO, MENSAGEM, SOLUCAO, AUTOR_ID, TOPICO_ID) VALUES('2019-07-25 18:00:00', 'Espere a próxima aula', false, 1, 2);
