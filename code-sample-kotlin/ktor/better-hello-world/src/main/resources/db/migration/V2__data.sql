insert into user_access.USERS(UUID) values
    ('09910fae-3748-4a4b-b7e5-53456ab47c4f'),
    ('8823029c-079a-4861-8a07-5dc9af2c920e'),
    ('f40445be-6e26-43dd-b3ce-0f876cc1f554');

insert into user_access.PROMOCODES(uuid, value, external_user_uuid) values
    ('a45ae2a9-c304-4632-80c4-59a78eb379e8', 'abc', '09910fae-3748-4a4b-b7e5-53456ab47c4f'),
    ('804355b2-73e6-4f9a-acad-a6b4c76e8a65', 'def', '8823029c-079a-4861-8a07-5dc9af2c920e'),
    ('bf9daed8-f924-4951-b81a-4eec4948dce0', 'vds', 'f40445be-6e26-43dd-b3ce-0f876cc1f554');
