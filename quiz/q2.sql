CREATE TABLE Kurs (
	kurskod VARCHAR(255) PRIMARY KEY,
    laengd INT,
    pris INT
);
INSERT INTO Kurs (kurskod, laengd, pris) VALUES
    ("Java1", 5, 6700),
    ("Java2", 4, 6000),
    ("DBM1", 2, 2800),
    ("LDBD", 4, 6000),
    ("FDBD", 5, 7200),
    ("LOG1", 3, 4500);
