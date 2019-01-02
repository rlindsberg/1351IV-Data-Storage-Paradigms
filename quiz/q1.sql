CREATE TABLE Lokal (
	namn VARCHAR(255) PRIMARY KEY,
    maxantal INT
);
INSERT INTO Lokal (namn, maxantal) VALUES
    ('Jupiter', 12),
    ('Orion', 24),
    ('Sirius', 16),
    ('Tellus', 32);

CREATE TABLE Laerare (
    lid INT PRIMARY KEY,
	lnamn VARCHAR(255),
    rum INT,
    tel INT
);
INSERT INTO Laerare (lid, lnamn, rum, tel) VALUES
    (1, "Anders Ödman", 634, 151576),
    (2, "Bo Åkerman", 604, 151526),
    (3, "Carl Nordin", 603, 151553),
    (4, "Lena Svensson", 605, 151556),
    (5, "Sofia Wilsson", 622, 151585);
