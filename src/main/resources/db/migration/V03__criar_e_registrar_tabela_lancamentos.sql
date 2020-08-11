create table lancamento (
	codigo bigint(20) primary key auto_increment,
	descricao varchar(100) not null,
	data_vencimento date not null,
	data_pagamento date,
	valor decimal(10,2) not null,
	observacao varchar(100),
	tipo varchar(20) not null,
	codigo_categoria bigint(20) not null,
	codigo_pessoa bigint(20) not null,
	foreign key (codigo_categoria) references categoria(codigo),
	foreign key (codigo_pessoa) references pessoa(codigo)	
) engine = InnoDB default charset = utf8;

insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Prolabore', '2020-07-01', '2020-07-03', 12000.00, 'Distribuição de Lucros', 'RECEITA', 1, 1);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Bahamas', '2020-07-02', null, 4000.00, null, 'DESPESA', 2, 2);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Top Club', '2020-07-03', null, 350.33, null, 'RECEITA', 3, 3);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Cemig', '2020-07-04','2020-07-08', 1239.47, 'Geração', 'RECEITA', 3, 4);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('DMAE', '2020-07-05', '2020-07-07', 436.00, null, 'DESPESA', 3, 5);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Extra', '2020-07-06', null, 745.87, null, 'RECEITA', 4, 6);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Cielo', '2020-07-07', '2020-07-14', 9876.22, null, 'RECEITA', 1, 7);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Despachante', '2020-07-08', '2020-07-09', 327.00, 'Licenciamento Porshe', 'DESPESA', 4, 8);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Pneus', '2020-07-09', null, 2500.00, 'Pneus Novos', 'DESPESA', 3, 8);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Café', '2020-07-10', '2020-07-12', 260.00, null, 'DESPESA', 5, 5);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Eletronicos', '2020-07-11', '2020-07-17', 4700.00, 'Note Novo', 'RECEITA', 1, 5);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Instrumentos', '2020-07-12', '2020-07-22', 2400.00, 'Piano', 'RECEITA', 4, 3);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Almoço', '2020-07-13', '2020-07-14', 127.00, 'Coco Bambu', 'DESPESA', 4, 2);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Lanche', '2020-07-14', '2020-07-22', 37.00, null, 'DESPESA', 4, 1);