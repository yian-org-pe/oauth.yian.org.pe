insert into oauth_client_details (client_id, resource_ids, client_secret, scope,
authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity,
refresh_token_validity, additional_information, autoapprove)
values ('bac8881e-2541-44d0-af9c-2fcd4fb94167', '', 'cascader', 'read,write,delete', 'implicit,password',
'http://localhost:3000,http://localhost:8080,https://ventasclient-dem0nline011.rhcloud.com',
'', null, null, '{}', true);

insert into oauth_client_details (client_id, resource_ids, client_secret, scope,
authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity,
refresh_token_validity, additional_information, autoapprove)
values ('asd', '', 'asd', 'read,write,update,delete', 'implicit,password,client_credentials',
'https://www.getpostman.com/oauth2/callback', '', null, null, '{}', false);

insert into role (id, name, description) values (1, 'ADMIN', 'Admin role');
insert into role (id, name, description) values (2, 'USER', 'Admin role');
-- user: demo@example.com password: demo
insert into user (username, email, name, password) values ('demo', 'demo@example.com', 'demo', 'c42d41d1ae6793c36b0fe91300fe226f54c6cde38333642ece2e5dd7bf0c65660b5050edc3ac51d9');
insert into user_has_roles (username, role_id) values ('demo', 1);

insert into organization (code, name, status) values ('001', 'yian.org.pe', 'E');

insert into user_belongs_organization (username, organization_code) values ('demo', '001');