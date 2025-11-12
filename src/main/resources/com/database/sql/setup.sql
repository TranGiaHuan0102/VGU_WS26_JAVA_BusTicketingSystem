-- Database: BusTicketManagementSystem

-- DROP DATABASE IF EXISTS "BusTicketManagementSystem";

CREATE DATABASE "BusTicketManagementSystem"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Table: public.locations

-- DROP TABLE IF EXISTS public.locations;

CREATE TABLE IF NOT EXISTS public.locations
(
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT locations_pkey PRIMARY KEY (location_name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.locations
    OWNER to postgres;

-- Table: public.ticket_information

-- DROP TABLE IF EXISTS public.ticket_information;

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

-- Table: public.weekly_daily

-- DROP TABLE IF EXISTS public.weekly_daily;

CREATE TABLE IF NOT EXISTS public.weekly_daily
(
    student_id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    ticket_type character varying(6) COLLATE pg_catalog."default" NOT NULL,
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT weekly_daily_pk PRIMARY KEY (student_id, start_date),
    CONSTRAINT ticket_info_fk FOREIGN KEY (location_name, ticket_type)
        REFERENCES public.ticket_information (location_name, ticket_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.weekly_daily
    OWNER to postgres;

-- Table: public.onewaytickets

-- DROP TABLE IF EXISTS public.onewaytickets;

CREATE TABLE IF NOT EXISTS public.onewaytickets
(
    student_id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    purchase_date date NOT NULL,
    location_name character varying(256) COLLATE pg_catalog."default",
    ticket_type character varying(6) COLLATE pg_catalog."default" DEFAULT 'DAILY'::character varying,
    direction character(1) COLLATE pg_catalog."default" NOT NULL,
    price bigint DEFAULT 150000,
    CONSTRAINT oneway_pk PRIMARY KEY (student_id, purchase_date),
    CONSTRAINT ticket_info_fk FOREIGN KEY (location_name, ticket_type)
        REFERENCES public.ticket_information (location_name, ticket_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT onewaytickets_direction_check CHECK (direction = ANY (ARRAY['T'::bpchar, 'F'::bpchar])),
    CONSTRAINT onewaytickets_price_check CHECK (price = 150000),
    CONSTRAINT onewaytickets_ticket_type_check CHECK (ticket_type::text = 'DAILY'::text)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.onewaytickets
    OWNER to postgres;