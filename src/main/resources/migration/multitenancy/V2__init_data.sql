
insert into `auth_user`(`login`, `password`) values ('a@user.com', '$2a$10$HMs/Z9GEvvRZRrel99ItpOXNQQ97wrPw0lMBsVZo8EIA27zqguEkS'); -- password = user
insert into `auth_user`(`login`, `password`) values ('b@user.com', '$2a$10$H2gU9.Gga2WbCkZH1mYByOyYy84Z7N2UdN3DrB0DAn40IyE977jWq'); -- password = user

insert into `user_tenant` (`user_id`, `schema_name`, `tenant_name`) select id, 'schema_A', 'First Customer' from `auth_user` where login = 'a@user.com';
insert into `user_tenant` (`user_id`, `schema_name`, `tenant_name`) select id, 'schema_B', 'Second Customer' from `auth_user` where login = 'a@user.com';
insert into `user_tenant` (`user_id`, `schema_name`, `tenant_name`) select id, 'schema_B', 'Second Customer' from `auth_user` where login = 'b@user.com';
