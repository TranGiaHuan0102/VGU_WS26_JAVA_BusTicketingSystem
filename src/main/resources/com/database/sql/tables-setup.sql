-- To run this setup file, use: psql -U postgres -d BusTicketManagementSystem -f tables-setup.sql

-- Uncomment if need fresh db (this will wipe existing data)
-- DROP TABLE IF EXISTS public.oneway CASCADE;
-- DROP TABLE IF EXISTS public.longterm CASCADE;
-- DROP TABLE IF EXISTS public.ticket_information CASCADE;
-- DROP TABLE IF EXISTS public.ticket_types CASCADE;
-- DROP TABLE IF EXISTS public.locations CASCADE;
-- DROP TABLE IF EXISTS public.user_types CASCADE;
-- DROP TABLE IF EXISTS public.user CASCADE;
-- DROP TYPE IF EXISTS direction_type CASCADE;

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

-- Table: public.ticket_types

CREATE TABLE IF NOT EXISTS public.ticket_types
(
    ticket_type character varying (10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ticket_types_pkey PRIMARY KEY (ticket_type)
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ticket_types
    OWNER to postgres;

-- Seed ticket types data
COPY public.ticket_types (ticket_type) FROM STDIN WITH (FORMAT CSV);
WEEKLY
DAILY
ONEWAY
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
    CONSTRAINT ticket_type_fk FOREIGN KEY (ticket_type)
        REFERENCES public.ticket_types (ticket_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
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

-- Table: public.user_types

CREATE TABLE IF NOT EXISTS public.user_types(
    user_type character varying (10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_types_pkey PRIMARY KEY (user_type)
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_types
    OWNER to postgres;


-- Seed user types data
COPY public.user_types (user_type) FROM STDIN WITH (FORMAT CSV);
STUDENT
PROFESSOR
\.


-- Table: public.user

CREATE TABLE IF NOT EXISTS public.user
(
    id character varying(8) COLLATE pg_catalog."default",
    first_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    password character varying(256) COLLATE pg_catalog."default" NOT NULL,
    email character varying(256) COLLATE pg_catalog."default" NOT NULL,
    user_type character varying(10) COLLATE pg_catalog."default" NOT NULL,
    user_price_multiplier NUMERIC(3,2) DEFAULT 1.0,
    CONSTRAINT id_pk PRIMARY KEY (id),
    CONSTRAINT user_type_fk FOREIGN KEY (user_type)
        REFERENCES public.user_types (user_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user
    OWNER to postgres;

-- Function to automatically set multiplier
CREATE OR REPLACE FUNCTION set_price_multiplier()
RETURNS TRIGGER AS $$
BEGIN
    NEW.user_price_multiplier := CASE
        WHEN NEW.user_type = 'PROFESSOR' THEN 0.5
        ELSE 1.0
    END;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger for price multiplier
CREATE TRIGGER trigger_set_price_multiplier
    BEFORE INSERT OR UPDATE OF user_type ON public.user
    FOR EACH ROW
    EXECUTE FUNCTION set_price_multiplier();



-- Table: public.longterm

CREATE TABLE IF NOT EXISTS public.longterm
(
    id character varying(8) COLLATE pg_catalog."default",
    start_date date,
    end_date date NOT NULL,
    ticket_type character varying(10) COLLATE pg_catalog."default" NOT NULL,
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    actual_price BIGINT NOT NULL DEFAULT 0,
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

CREATE TYPE direction_type AS ENUM ('TO', 'FROM');

CREATE TABLE IF NOT EXISTS public.oneway
(
    id character varying(8) COLLATE pg_catalog."default",
    departure_date date,
    location_name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    ticket_type character varying(10) COLLATE pg_catalog."default" NOT NULL,
    direction direction_type NOT NULL,
    actual_price BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT oneway_pk PRIMARY KEY (id, departure_date, direction),
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

ALTER TABLE IF EXISTS public.oneway
    OWNER to postgres;

-- Function to calculate actual price based on location_price and user_price_multiplier
CREATE OR REPLACE FUNCTION calculate_actual_price()
RETURNS TRIGGER AS $$
BEGIN 
    SELECT (TI.price * U.user_price_multiplier)::BIGINT INTO NEW.actual_price
    FROM public.user U 
    JOIN public.ticket_information TI ON (TI.location_name = NEW.location_name AND TI.ticket_type = NEW.ticket_type)
    WHERE U.id = NEW.id;

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
CREATE TRIGGER trigger_actual_longterm_price
    BEFORE INSERT ON public.oneway
    FOR EACH ROW
    EXECUTE FUNCTION calculate_actual_price();