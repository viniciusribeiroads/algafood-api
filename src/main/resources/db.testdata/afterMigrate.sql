set foreign_key_checks = 0;

delete from estado;
delete from cozinha;
delete from restaurante;
delete from permissao;
delete from cidade;

set foreign_key_checks = 1;

alter table cidade auto_increment=1;
alter table cozinha auto_increment=1;
alter table restaurante auto_increment=1;
alter table estado auto_increment=1;
alter table permissao auto_increment=1;


insert ignore into cozinha (nome) values ('Tailandesa');
insert ignore into cozinha (nome) values ('Indiana');
insert ignore into cozinha (nome) values ('Maraotnis');
insert ignore into cozinha (nome) values ('Bomjesuence');

insert into estado(nome) values ('MG');
insert into estado(nome) values ('RJ');
insert into estado(nome) values ('SP');
insert into estado(nome) values ('PA');
insert into estado(nome) values ('RO');
insert into estado(nome) values ('MS');
insert into estado(nome) values ('AL');

insert into cidade(nome,estado_id) values ('Ouro Preto',1);
insert into cidade(nome,estado_id) values ('Belo Horizonte',2);
insert into cidade(nome,estado_id) values ('Sao Paulo',3);
insert into cidade(nome,estado_id) values ('Ibirite',4);
insert into cidade(nome,estado_id) values ('Itabira',2);
insert into cidade(nome,estado_id) values ('Caete',1);
insert into cidade(nome,estado_id) values ('Joao Monlevade',1);