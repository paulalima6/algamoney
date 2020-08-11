
create table usuario(
	codigo bigint(20) primary key,
	nome varchar(50) not null,
	email varchar(50) not null,
	senha varchar(150) not null
) engine = InnoDB default charset = utf8;

create table permissao(
	codigo bigint(20) primary key,
	descricao varchar(50) not null
) engine = InnoDB default charset = utf8;

create table usuario_permissao(
	codigo_usuario bigint(20) not null,
	codigo_permissao bigint(20) not null,
	primary key (codigo_usuario, codigo_permissao),
	foreign key (codigo_usuario) references usuario(codigo),
	foreign key (codigo_permissao) references permissao(codigo) 
) engine = InnoDB default charset = utf8;

insert into usuario(codigo, nome, email, senha) values (1, 'Administrador', 'admin@pcon.com', '$2a$10$4HFbZLv6uAX9ZJPXIxUUG.SHzWVhvMXXUyJPzS4Y0KGL6KDtiqeTm');
insert into usuario(codigo, nome, email, senha) values (2, 'Ana Paula', 'anapaula@pcon.com', '$2a$10$3y3KW9VzzUD0VOfahw.IMujzRVl7.5HfRNODHZwc78jJtou8qBL6O');

insert into permissao(codigo, descricao) values (1, 'ROLE_CADASTRAR_CATEGORIA');
insert into permissao(codigo, descricao) values (2, 'ROLE_PESQUISAR_CATEGORIA');

insert into permissao(codigo, descricao) values (3, 'ROLE_CADASTRAR_PESSOA');
insert into permissao(codigo, descricao) values (4, 'ROLE_REMOVER_PESSOA');
insert into permissao(codigo, descricao) values (5, 'ROLE_PESQUISAR_PESSOA');

insert into permissao(codigo, descricao) values (6, 'ROLE_CADASTRAR_LANCAMENTO');
insert into permissao(codigo, descricao) values (7, 'ROLE_REMOVER_LANCAMENTO');
insert into permissao(codigo, descricao) values (8, 'ROLE_PESQUISAR_LANCAMENTO');

/**admin**/
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (1, 1);
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (1, 2);
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (1, 3);
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (1, 4);
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (1, 5);
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (1, 6);
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (1, 7);
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (1, 8);

/**ana**/
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (2, 2);
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (2, 5);
insert into usuario_permissao(codigo_usuario, codigo_permissao) values (2, 8);

