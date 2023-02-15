insert into `tenant` (`name`, `schema_name`, `company_code`) values ('First Customer', 'schema_A', '0001');
insert into `tenant` (`name`, `schema_name`, `company_code`) values ('Second Customer', 'schema_B', '0002');

insert into `user`(`name`, `login`, `password`, `tenant_id`) select 'User A', 'user_A', '$2a$10$HMs/Z9GEvvRZRrel99ItpOXNQQ97wrPw0lMBsVZo8EIA27zqguEkS', id from `tenant` where `company_code` = '0001';
insert into `user`(`name`, `login`, `password`, `tenant_id`) select 'User B', 'user_B', '$2a$10$H2gU9.Gga2WbCkZH1mYByOyYy84Z7N2UdN3DrB0DAn40IyE977jWq', id from `tenant` where `company_code` = '0002';
