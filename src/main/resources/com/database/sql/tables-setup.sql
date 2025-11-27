-- To run this setup file, use: psql -U postgres -d BusTicketManagementSystem -f tables-setup.sql

-- Uncomment if need fresh db (this will wipe existing data)
-- DROP TABLE IF EXISTS public.oneway;
-- DROP TABLE IF EXISTS public.longterm;
-- DROP TABLE IF EXISTS public.ticket_information;
-- DROP TABLE IF EXISTS public.locations;
-- DROP TABLE IF EXISTS public.user;

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
    ticket_type character varying(10) COLLATE pg_catalog."default" NOT NULL,
    price bigint NOT NULL,
    morning_pickuptime time without time zone NOT NULL,
    afternoon_pickuptime time without time zone NOT NULL,
    CONSTRAINT ticket_info_pk PRIMARY KEY (location_name, ticket_type),
    CONSTRAINT location_id_fk FOREIGN KEY (location_name)
        REFERENCES public.locations (location_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT ticket_type_chk CHECK (ticket_type::text = 'WEEKLY'::text OR ticket_type::text = 'DAILY' OR ticket_type::text = 'ONEWAY'::text)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ticket_information
    OWNER to postgres;

-- Seed ticket information data
COPY public.ticket_information (location_name, ticket_type, price, morning_pickuptime, afternoon_pickuptime) FROM STDIN WITH (FORMAT CSV);
Turtle Lake,WEEKLY,4500000,06:00:00,16:30:00
Turtle Lake,DAILY,8500000,06:45:00,16:30:00
Turtle Lake,ONEWAY,150000,06:45:00,16:30:00
Hang Xanh,WEEKLY,6700000,06:10:00,16:30:00
Hang Xanh,DAILY,8000000,06:55:00,16:30:00
Hang Xanh,ONEWAY,150000,06:55:00,16:30:00
Binh Trieu,WEEKLY,3800000,06:25:00,16:30:00
Binh Trieu,DAILY,7100000,07:05:00,16:30:00
Binh Trieu,ONEWAY,150000,07:05:00,16:30:00
Binh Phuoc Crossroads,WEEKLY,3600000,06:50:00,16:30:00
Binh Phuoc Crossroads,DAILY,6700000,07:15:00,16:30:00
Binh Phuoc Crossroads,ONEWAY,150000,07:15:00,16:30:00
Binh Duong Aeon Mall,WEEKLY,2500000,07:05:00,16:30:00
Binh Duong Aeon Mall,DAILY,4700000,07:35:00,16:30:00
Binh Duong Aeon Mall,ONEWAY,150000,07:35:00,16:30:00
Becamex Tower,WEEKLY,1900000,07:20:00,16:30:00
Becamex Tower,DAILY,3600000,07:50:00,16:30:00
Becamex Tower,ONEWAY,150000,07:50:00,16:30:00
\.

-- Table: public.user

CREATE TABLE IF NOT EXISTS public.user
(
    id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    password character varying(256) COLLATE pg_catalog."default" NOT NULL,
    email character varying(256) COLLATE pg_catalog."default" NOT NULL,
    user_type character varying(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT id_pk PRIMARY KEY (id),
    CONSTRAINT user_type_check CHECK (user_type::text = ANY (ARRAY['STUDENT'::character varying::text, 'INSTRUCTOR'::character varying::text, 'GUEST'::character varying::text]))
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user
    OWNER to postgres;

-- Table: public.longterm
CREATE TABLE IF NOT EXISTS public.longterm
(
    id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    ticket_type character varying(10) COLLATE pg_catalog."default" NOT NULL,
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT longterm_pk PRIMARY KEY (id, start_date),
    CONSTRAINT id_fk FOREIGN KEY (id)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT ticket_info_fk FOREIGN KEY (location_name, ticket_type)
        REFERENCES public.ticket_information (location_name, ticket_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.longterm
    OWNER to postgres;

-- Table: public.oneway

CREATE TABLE IF NOT EXISTS public.oneway
(
    id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    departure_date date NOT NULL,
    location_name character varying(256) COLLATE pg_catalog."default",
    ticket_type character varying(10) COLLATE pg_catalog."default" DEFAULT 'ONEWAY'::character varying,
    direction character(1) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT oneway_pk PRIMARY KEY (id, departure_date, direction),
    CONSTRAINT id_fk FOREIGN KEY (id)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT ticket_info_fk FOREIGN KEY (location_name, ticket_type)
        REFERENCES public.ticket_information (location_name, ticket_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT oneway_direction_check CHECK (direction = ANY (ARRAY['T'::bpchar, 'F'::bpchar])),
    CONSTRAINT oneway_ticket_type_check CHECK (ticket_type::text = 'ONEWAY'::text)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.oneway
    OWNER to postgres;