CREATE DATABASE spring_boot_app;
use spring_boot_app;
create table clientes (edad integer, estado bit, id bigint not null auto_increment, direccion varchar(255), genero varchar(255), nombre varchar(255), num_cedula varchar(255) not null, password varchar(255), telefono varchar(255) not null, primary key (id)) engine=InnoDB;
create table cuentas (estado bit, saldo_inicial decimal(10,2) not null, cliente_id bigint, num_cuenta varchar(10) not null, tipo_cuenta enum ('AHORROS','CORRIENTE'), primary key (num_cuenta)) engine=InnoDB;
create table movimientos (saldo decimal(10,2), valor decimal(10,2), fecha datetime(6), id bigint not null auto_increment, cuenta_id varchar(10), tipo_movimiento varchar(255), primary key (id)) engine=InnoDB;
alter table clientes add constraint UKajpyy6u5wkeln3nhpmo9542jp unique (num_cedula);
alter table clientes add constraint UKdtxgio8utms5uc0q7ywit7bp7 unique (telefono);
alter table cuentas add constraint FK65yk2321jpusl3fk96lqehrli foreign key (cliente_id) references clientes (id);
alter table movimientos add constraint FK4moe88hxuohcysas5h70mdc09 foreign key (cuenta_id) references cuentas (num_cuenta);
