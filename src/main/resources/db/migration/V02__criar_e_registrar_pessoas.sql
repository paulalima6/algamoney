create table pessoa (
	codigo bigint(20) primary key auto_increment,
	nome varchar(100) not null,
	ativo boolean not null,
	logradouro varchar(100),
	numero varchar(100) not null,
	complemento varchar(100),
	bairro varchar(100) not null,
	cep varchar(100) not null,
	cidade varchar(100) not null,
	estado varchar(100) not null
	
	
) engine = InnoDB default charset = utf8;

insert into pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Ana Paula', true, 'Estrada Normandia', '137', 'casa 13', 'Jardim Pioneiro', '06705-360', 'Cotia', 'São Paulo');
insert into pessoa(nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('Natalia Lima', true, 'Rua Alagoinhas', '137', 'Jardim Araruama', '06700-560', 'Cotia', 'São Paulo');
insert into pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Tania Lima', true, 'Avenida Chucri Zaidan', '1231', 'apto 92', 'Morumbi', '03221-742', 'São Paulo', 'São Paulo');
insert into pessoa(nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('Divorlan de Almeida', true, 'Avenida Luigi Galvani', '623', 'Brooklin', '03456-221', 'São Paulo', 'São Paulo');
insert into pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Ana Julia Lima', true, 'Alameda Rio Negro', '122', 'casa 4', 'Residencial Zero', '03221-740', 'Barueri', 'São Paulo');
insert into pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Danilo Macedo', true, 'Rua de Alphaville', '12', 'apto 151', 'Alphaville', '06722-123', 'Barueri', 'São Paulo');
insert into pessoa(nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('Cielo', true, 'Alameda Xingu', '512', 'Alphaville', '05221-922', 'Barueri', 'São Paulo');
insert into pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Felipe', true, 'Avenida Tals', '720', 'apto 156', 'Jardim Não Lembro', '08332-992', 'Taboão da Serra', 'São Paulo');
