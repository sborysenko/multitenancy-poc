insert into schema_a.company (name) values ('First Customer');
insert into schema_a.`user` (company_id, name, email) select id, 'User A', 'a@user.com' from schema_a.company where name = 'First Customer';

insert into schema_b.company (name) values ('Second Customer');
insert into schema_b.`user` (company_id, name, email) select id, 'User A', 'a@user.com' from schema_b.company where name = 'Second Customer';
insert into schema_b.`user` (company_id, name, email) select id, 'User B', 'b@user.com' from schema_b.company where name = 'Second Customer';
