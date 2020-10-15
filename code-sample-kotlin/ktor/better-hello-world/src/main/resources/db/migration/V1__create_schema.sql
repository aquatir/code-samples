-- For DEFAULT UUID
CREATE SCHEMA IF NOT EXISTS public;
CREATE SCHEMA IF NOT EXISTS user_access;
CREATE EXTENSION IF NOT EXISTS "pgcrypto" SCHEMA public;

-- For auto-update 'updated' field
CREATE OR REPLACE FUNCTION public.trigger_set_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--
create table user_access.USERS(
    UUID UUID primary key not null,
    created_at TIMESTAMP WITHOUT TIME ZONE not null default now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE not null default now(),
    deleted_at TIMESTAMP WITHOUT TIME ZONE
);


CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON user_access.USERS
    FOR EACH ROW
EXECUTE PROCEDURE public.trigger_set_timestamp();

--
create table user_access.PROMOCODES(
                           uuid UUID primary key not null,
                           value varchar not null unique,
                           external_user_uuid UUID not null REFERENCES user_access.USERS(UUID) unique,

                           created_at TIMESTAMP WITHOUT TIME ZONE not null default now(),
                           updated_at TIMESTAMP WITHOUT TIME ZONE not null default now(),
                           deleted_at TIMESTAMP WITHOUT TIME ZONE
);

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON user_access.PROMOCODES
    FOR EACH ROW
EXECUTE PROCEDURE public.trigger_set_timestamp();
