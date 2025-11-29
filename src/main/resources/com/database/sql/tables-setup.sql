-- To run this setup file, use: psql -U postgres -d BusTicketManagementSystem -f tables-setup.sql

-- Drop tables in correct order (child tables first)
-- DROP TABLE IF EXISTS public.oneway CASCADE;
-- DROP TABLE IF EXISTS public.longterm CASCADE;
-- DROP TABLE IF EXISTS public.price_entries CASCADE;
-- DROP TABLE IF EXISTS public.locations CASCADE;
-- DROP TABLE IF EXISTS public.user CASCADE;
-- DROP TYPE IF EXISTS direction_type;
-- DROP TYPE IF EXISTS ticket_types;
-- DROP TYPE IF EXISTS roles;

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

-- Create ENUM for ticket types
CREATE TYPE ticket_types AS ENUM ('WEEKLY', 'DAILY', 'ONEWAY');

-- Table: public.price_entries
CREATE TABLE IF NOT EXISTS public.price_entries
(
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    ticket_type ticket_types NOT NULL,
    price bigint NOT NULL,
    morning_pickuptime time without time zone NOT NULL,
    afternoon_pickuptime time without time zone NOT NULL,
    CONSTRAINT ticket_info_pk PRIMARY KEY (location_name, ticket_type),
    CONSTRAINT location_id_fk FOREIGN KEY (location_name)
        REFERENCES public.locations (location_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.price_entries
    OWNER to postgres;

-- Seed price entries data
COPY public.price_entries(location_name, ticket_type, price, morning_pickuptime, afternoon_pickuptime) FROM STDIN WITH (FORMAT CSV);
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

-- Create ENUM for user roles
CREATE TYPE roles AS ENUM ('STUDENT', 'PROFESSOR');

-- Table: public.user
CREATE TABLE IF NOT EXISTS public.user
(
    id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    password character varying(256) COLLATE pg_catalog."default" NOT NULL,
    email character varying(256) COLLATE pg_catalog."default" NOT NULL,
    user_type roles NOT NULL,
    user_price_multiplier NUMERIC(3,2) DEFAULT 1.0,
    CONSTRAINT id_pk PRIMARY KEY (id)
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
    ticket_type ticket_types NOT NULL,
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    actual_price BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT longterm_pk PRIMARY KEY (id, start_date),
    CONSTRAINT longterm_user_fk FOREIGN KEY (id)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT longterm_ticket_fk FOREIGN KEY (location_name, ticket_type)
        REFERENCES public.price_entries (location_name, ticket_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.longterm
    OWNER to postgres;

-- Create ENUM for direction
CREATE TYPE direction_type AS ENUM ('TO', 'FROM');

-- Table: public.oneway
CREATE TABLE IF NOT EXISTS public.oneway
(
    id character varying(8) COLLATE pg_catalog."default" NOT NULL,
    departure_date date NOT NULL,
    ticket_type ticket_types NOT NULL,
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    direction direction_type NOT NULL,
    actual_price BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT oneway_pk PRIMARY KEY (id, departure_date, direction),
    CONSTRAINT oneway_user_fk FOREIGN KEY (id)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT oneway_ticket_fk FOREIGN KEY (location_name, ticket_type)
        REFERENCES public.price_entries (location_name, ticket_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.oneway
    OWNER to postgres;

-- Function to calculate actual price based on location_price and user_price_multiplier
CREATE OR REPLACE FUNCTION calculate_actual_price()
RETURNS TRIGGER AS $$
BEGIN 
    SELECT (PE.price * U.user_price_multiplier)::BIGINT INTO NEW.actual_price
    FROM public.user U,
         public.price_entries PE
    WHERE U.id = NEW.id
      AND PE.location_name = NEW.location_name 
      AND PE.ticket_type = NEW.ticket_type;

    IF NEW.actual_price IS NULL THEN
        NEW.actual_price := 0;  
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger for longterm_actual_price
CREATE TRIGGER trigger_actual_longterm_price
    BEFORE INSERT ON public.longterm
    FOR EACH ROW
    EXECUTE FUNCTION calculate_actual_price();

-- Trigger for oneway_actual_price
CREATE TRIGGER trigger_actual_oneway_price
    BEFORE INSERT ON public.oneway
    FOR EACH ROW
    EXECUTE FUNCTION calculate_actual_price();
