INSERT INTO person
VALUES (1, '呵呵呵', 22, 0);
INSERT INTO person
VALUES (2, '哈哈哈', 23, 1);

insert into users
values ('218826483963', 'liang yudong3',
        'aa13a0a86c7bcecd09b05064ee9ba81cc46a0de4515ea08e93c87756f90a5a99212dbd010e7bb0d9', 1);
insert into users
values ('218826483964', 'liang yudong4',
        'aa13a0a86c7bcecd09b05064ee9ba81cc46a0de4515ea08e93c87756f90a5a99212dbd010e7bb0d9', 1);
insert into users
values ('218826483965', 'liang yudong5',
        'aa13a0a86c7bcecd09b05064ee9ba81cc46a0de4515ea08e93c87756f90a5a99212dbd010e7bb0d9', 1);
insert into authorities
values ('ROLE_ROOT', '218826483963');
insert into authorities
values ('ROLE_ADMIN', '218826483964');
insert into authorities
values ('ROLE_OTHER', '218826483965');
insert into authorities
values ('ROLE_ANON', '218826483965');
insert into groups
values ('100000', 'TOP');
insert into groups
values ('100001', 'MIDDLE');
insert into groups
values ('100002', 'LOW');
insert into groups_authorities
values ('100000', 'ROOT');
insert into groups_authorities
values ('100000', 'ADMIN');
insert into groups_authorities
values ('100001', 'OTHER');
insert into groups_authorities
values ('100001', 'ANON');
insert into groups_users
values ('100000', '218826483963');
insert into groups_users
values ('100001', '218826483964');