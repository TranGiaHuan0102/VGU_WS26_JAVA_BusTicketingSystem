CREATE TABLE OneWayTickets (
    Student_ID VARCHAR(8),
    Purchase_Date DATE,
    Location_Name VARCHAR(256),
    Ticket_Type VARCHAR(6) DEFAULT 'DAILY' CHECK (Ticket_Type = 'DAILY'),
    Direction CHAR(1) NOT NULL CHECK (Direction IN ('T', 'F')),
    Price BIGINT DEFAULT 150000 CHECK (Price = 150000),
    CONSTRAINT ticket_info_fk FOREIGN KEY (Location_Name, Ticket_Type) REFERENCES Ticket_Information(Location_Name, Ticket_Type),
    CONSTRAINT oneway_pk PRIMARY KEY (Student_ID, Purchase_Date)
);


