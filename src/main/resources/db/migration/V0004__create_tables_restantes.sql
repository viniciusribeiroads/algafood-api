create table forma_de_pagamento (descricao varchar(60) not null, primary key (descricao)) engine=InnoDB default charset=utf8;
create table permissao (nome varchar(60) not null, descricao varchar(60), primary key (nome)) engine=InnoDB default charset=utf8;
create table restaurante (id bigint not null auto_increment, nome varchar(60), taxa_frete decimal(19,2) not null, cozinha bigint, primary key (id)) engine=InnoDB;
alter table restaurante add constraint cozinha_fk foreign key (cozinha) references cozinha (id);