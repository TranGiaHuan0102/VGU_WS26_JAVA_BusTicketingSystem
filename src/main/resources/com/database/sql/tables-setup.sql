-- Drop tables if needed
DROP TABLE IF EXISTS public.oneway;
DROP TABLE IF EXISTS public.weekly_daily;
DROP TABLE IF EXISTS public.ticket_information;
DROP TABLE IF EXISTS public.locations;

-- Table: public.locations

CREATE TABLE IF NOT EXISTS public.locations
(
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT locations_pkey PRIMARY KEY (location_name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.locations
    OWNER to postgres;

-- Seed locations data
COPY public.locations (location_name) FROM STDIN WITH (FORMAT CSV);
Turtle Lake
Hang Xanh
Binh Trieu
Binh Phuoc Crossroads
Binh Duong Aeon Mall
Becamex Tower
\.

-- Table: public.ticket_information


CREATE TABLE IF NOT EXISTS public.ticket_information
(
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    ticket_type character varying(20) COLLATE pg_catalog."default" NOT NULL,
    price bigint NOT NULL,
    morning_pickuptime time without time zone NOT NULL,
    afternoon_pickuptime time without time zone NOT NULL,
    CONSTRAINT ticket_info_pk PRIMARY KEY (location_name, ticket_type),
    CONSTRAINT location_id_fk FOREIGN KEY (location_name)
        REFERENCES public.locations (location_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT ticket_type_chk CHECK (ticket_type::text = 'WEEKLY'::text OR ticket_type::text = 'DAILY'::text)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ticket_information
    OWNER to postgres;

-- Seed ticket information data
COPY public.ticket_information (location_name, ticket_type, price, morning_pickuptime, afternoon_pickuptime) FROM STDIN WITH (FORMAT CSV);
Turtle Lake,WEEKLY,450000,06:00:00,16:30:00
Turtle Lake,DAILY,8500000,06:45:00,16:30:00
Hang Xanh,WEEKLY,6700000,06:10:00,16:30:00
Hang Xanh,DAILY,8000000,06:55:00,16:30:00
Binh Trieu,WEEKLY,3800000,06:25:00,16:30:00
Binh Trieu,DAILY,7100000,07:05:00,16:30:00
Binh Phuoc Crossroads,WEEKLY,3600000,06:50:00,16:30:00
Binh Phuoc Crossroads,DAILY,6700000,07:15:00,16:30:00
Binh Duong Aeon Mall,WEEKLY,2500000,07:05:00,16:30:00
Binh Duong Aeon Mall,DAILY,4700000,07:35:00,16:30:00
Becamex Tower,WEEKLY,1900000,07:20:00,16:30:00
Becamex Tower,DAILY,3600000,07:50:00,16:30:00
\.


-- Table: public.weekly_daily



CREATE TABLE IF NOT EXISTS public.weekly_daily
(
    id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    ticket_type character varying(6) COLLATE pg_catalog."default" NOT NULL,
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT weekly_daily_pk PRIMARY KEY (id, start_date),
    CONSTRAINT ticket_info_fk FOREIGN KEY (location_name, ticket_type)
        REFERENCES public.ticket_information (location_name, ticket_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.weekly_daily
    OWNER to postgres;

-- Table: public.oneway



CREATE TABLE IF NOT EXISTS public.oneway
(
    id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    purchase_date date NOT NULL,
    location_name character varying(256) COLLATE pg_catalog."default",
    ticket_type character varying(6) COLLATE pg_catalog."default" DEFAULT 'DAILY'::character varying,
    direction character(1) COLLATE pg_catalog."default" NOT NULL,
    price bigint DEFAULT 150000,
    CONSTRAINT oneway_pk PRIMARY KEY (id, purchase_date),
    CONSTRAINT ticket_info_fk FOREIGN KEY (location_name, ticket_type)
        REFERENCES public.ticket_information (location_name, ticket_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT oneway_direction_check CHECK (direction = ANY (ARRAY['T'::bpchar, 'F'::bpchar])),
    CONSTRAINT oneway_price_check CHECK (price = 150000),
    CONSTRAINT oneway_ticket_type_check CHECK (ticket_type::text = 'DAILY'::text)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.oneway
    OWNER to postgres;