CREATE TABLE IF NOT EXISTS public.customer
(
    id         serial4      NOT NULL,
    "name"     varchar(128) NOT NULL,
    email      varchar(128) NOT NULL,
    address    varchar(128) NOT NULL,
    created_at timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT customer_email_key UNIQUE (email),
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);