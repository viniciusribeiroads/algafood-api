create table endereco(
 id bigint not null auto_increment,
 bairro varchar(80) not null,
 rua varchar (100) not null,
 numero bigint not null,
 primary key(id)
)engine=InnoDB;