-- database: musicshop

-- excluir tabelas: --
set foreign_key_checks = 0;
drop table if exists cliente cascade;
drop table if exists categoria cascade;
drop table if exists produto cascade;
drop table if exists venda cascade;
set foreign_key_checks = 1;

-- criar tabelas: --
create table cliente (
    cliente_id int not null primary key auto_increment,
    nome varchar(45) not null,
    endereco varchar(60) not null
) ENGINE = InnoDB;

create table categoria (
	categoria_id int not null primary key auto_increment,
	nome varchar(45) not null
) ENGINE = InnoDB;

create table produto (
	produto_id int not null primary key auto_increment,
	categoria_id int not null,
	nome varchar(45) not null,
	valor float not null,
	index (categoria_id),
	foreign key (categoria_id) references categoria (categoria_id)
) ENGINE = InnoDB;

create table venda (
	venda_id int not null primary key auto_increment,
	cliente_id int not null,
	produto_id int not null,
	data datetime not null,
	index (cliente_id),
	foreign key (cliente_id) references cliente (cliente_id),
	index (produto_id),
	foreign key (produto_id) references produto (produto_id)
) ENGINE = InnoDB;

-- inserir dados iniciais: --
insert into categoria (nome) values ('Instrumentos de Cordas');
insert into categoria (nome) values ('Instrumentos de Teclas');
insert into categoria (nome) values ('Acessórios');




















