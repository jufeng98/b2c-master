drop table sys_menu;
create table sys_menu(id integer, parent_id integer, text varchar(50));
insert into sys_menu values (1, null, 'San Mateo');
insert into sys_menu values (2, null, 'Daly City');
insert into sys_menu values (3, null, 'San Francisco');