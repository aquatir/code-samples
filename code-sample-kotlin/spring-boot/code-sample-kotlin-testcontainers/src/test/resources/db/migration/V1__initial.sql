create schema other;

CREATE TABLE other.entity
(
    uuid          UUID        not null,
    constraint pk_other_entity PRIMARY KEY (uuid)
);