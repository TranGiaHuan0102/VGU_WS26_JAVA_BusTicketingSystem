CREATE TABLE IF NOT EXISTS Weekly_Daily(
    Student_ID VARCHAR(8),
    Start_Date DATE,
    End_Date DATE NOT NULL,
    Ticket_Type VARCHAR(6) NOT NULL,
    Location_Name VARCHAR(256) NOT NULL,
    CONSTRAINT ticket_info_fk FOREIGN KEY (Location_Name, Ticket_Type) REFERENCES Ticket_Information(Location_Name, Ticket_Type),
    CONSTRAINT weekly_daily_pk PRIMARY KEY (Student_ID, Start_date)
);