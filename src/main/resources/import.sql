insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Maraotnis');
insert into cozinha (nome) values ('Bomjesuence');

insert into estado(nome) values ('MG');
insert into estado(nome) values ('RJ');
insert into estado(nome) values ('SP');
insert into estado(nome) values ('PA');
insert into estado(nome) values ('RO');
insert into estado(nome) values ('MS');
insert into estado(nome) values ('AL');


insert into cidade(nome) values ('Ouro Preto');
insert into cidade(nome) values ('Belo Horizonte');
insert into cidade(nome) values ('Sao Paulo');
insert into cidade(nome) values ('Ibirite');
insert into cidade(nome) values ('Itabira');
insert into cidade(nome) values ('Caete');
insert into cidade(nome) values ('Joao Monlevade');


insert into restaurante(nome,taxa_frete,cozinha) values ('Parada do mexidao',32.10,1);
insert into restaurante(nome,taxa_frete,cozinha) values ('Gauchao',20.00,2);
insert into restaurante(nome,taxa_frete,cozinha) values ('Brotojao',0,2);

insert into forma_de_pagamento(descricao) values ('dinheiro');
insert into forma_de_pagamento(descricao) values ('debito');
insert into forma_de_pagamento(descricao) values ('credito');

insert into permissao(nome,descricao) values('Vinicius','Adm');
insert into permissao(nome,descricao) values('Joao','Operador');