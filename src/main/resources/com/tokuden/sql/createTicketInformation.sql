CREATE TABLE IF NOT EXISTS Ticket_Information(
    Location_name VARCHAR(256),
    Ticket_Type VARCHAR(6),
    Price BIGINT NOT NULL,
    Morning_PickupTime TIME NOT NULL,
    Afternoon_PickupTime TIME NOT NULL,
    CONSTRAINT location_id_fk FOREIGN KEY (Location_name) REFERENCES Locations(Location_name) ON DELETE CASCADE,
    CONSTRAINT ticket_info_pk PRIMARY KEY (Location_name, Ticket_Type),
    CONSTRAINT ticket_type_chk CHECK (Ticket_Type = 'WEEKLY' OR Ticket_Type = 'DAILY')
);
