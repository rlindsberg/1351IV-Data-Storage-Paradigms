CREATE TABLE artwork_type (
	name VARCHAR(255) PRIMARY KEY
);

INSERT INTO artwork_type (name) VALUES ('Painting');
INSERT INTO artwork_type (name) VALUES ('Sculpture');
INSERT INTO artwork_type (name) VALUES ('Photography');
INSERT INTO artwork_type (name) VALUES ('Calligraphy');

CREATE TABLE product_type (
	name VARCHAR(255) PRIMARY KEY
);

INSERT INTO product_type (name) VALUES ('Keyring');
INSERT INTO product_type (name) VALUES ('T-shirt');
INSERT INTO product_type (name) VALUES ('Tie');
INSERT INTO product_type (name) VALUES ('Postcard');

CREATE TABLE product_color (
	name VARCHAR(255) PRIMARY KEY
);

INSERT INTO product_color (name) VALUES ('RED');
INSERT INTO product_color (name) VALUES ('GREEN');
INSERT INTO product_color (name) VALUES ('BLUE');

CREATE TABLE product_size (
	name VARCHAR(255) PRIMARY KEY
);

INSERT INTO product_size (name) VALUES ('S');
INSERT INTO product_size (name) VALUES ('M');
INSERT INTO product_size (name) VALUES ('L');

CREATE TABLE ticket_type (
	name VARCHAR(255) PRIMARY KEY
);

INSERT INTO ticket_type (name) VALUES ('Junior');
INSERT INTO ticket_type (name) VALUES ('Adult');
INSERT INTO ticket_type (name) VALUES ('Senior');

CREATE TABLE language (
	name VARCHAR(255) PRIMARY KEY
);

INSERT INTO language (name) VALUES ('English');
INSERT INTO language (name) VALUES ('Swedish');
INSERT INTO language (name) VALUES ('French');
INSERT INTO language (name) VALUES ('German');
INSERT INTO language (name) VALUES ('Russian');

CREATE TABLE guide (
	social_sec_no VARCHAR(255) PRIMARY KEY,
	name VARCHAR(255) NOT NULL
);

INSERT INTO guide (social_sec_no, name) VALUES ('123456-7890', 'John Doe');
INSERT INTO guide (social_sec_no, name) VALUES ('234567-8901', 'Tim Cook');
INSERT INTO guide (social_sec_no, name) VALUES ('345678-9012', 'Fred Foo');

CREATE TABLE guide_language (
	social_sec_no VARCHAR(255),
	language VARCHAR(255),
	PRIMARY KEY(social_sec_no, language),
	FOREIGN KEY fk_gl_guide(social_sec_no) REFERENCES guide(social_sec_no)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	FOREIGN KEY fk_gl_lang(language) REFERENCES language(name)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

INSERT INTO guide_language (social_sec_no, language) VALUES
('123456-7890', 'English'),
('123456-7890', 'German'),
('345678-9012', 'German'),
('234567-8901', 'English'),
('234567-8901', 'French'),
('345678-9012', 'Russian');


CREATE TABLE artist (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	date_of_birth DATE,
	date_of_death DATE,
	image_url VARCHAR(255)
);

INSERT INTO artist (name, description, date_of_birth, date_of_death, image_url)
VALUES 
('Salvador Dalí', 'I am drugs', '1904-05-11', '1989-01-23', 'https://uploads5.wikiart.org/images/salvador-dali.jpg!Portrait.jpg'),
('Pablo Picasso', 'Everything you can imagine is real', '1881-10-25', '1973-4-8', 'https://upload.wikimedia.org/wikipedia/commons/e/e3/Pablo_picasso.jpg'),
('Frida Kahlo de Rivera', 'I hope the leaving is joyful; and I hope never to return.', '1907-7-6', '1954-7-3', 'http://www.fridakahlo.org/images/frida-kahlo-picture.jpg');
# NON STRONG CLASSES AHEAD...

CREATE TABLE artwork (
	id INT PRIMARY KEY AUTO_INCREMENT,
	artist_id INT NOT NULL,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	size INT NOT NULL,
	created_at DATE,
	artwork_type VARCHAR(255),
	FOREIGN KEY artist_artwork_fk(artist_id) REFERENCES artist(id)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,	
	FOREIGN KEY artwork_type_fk(artwork_type) REFERENCES artwork_type(name)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

INSERT INTO artwork (artist_id, name, description, size, created_at, artwork_type)
VALUES
((SELECT id FROM artist WHERE name = 'Salvador Dalí'), 'The Persistence of Memory', 'Four clocks are prominently on display in an otherwise empty desert scene. While this might seem uncanny enough, the clocks are not flat as you might expect them to be, but are bent out of shape, appearing to be in the act of melting away.', 4, '1931-1-1', 'Painting'),
((SELECT id FROM artist WHERE name = 'Salvador Dalí'), 'The Temptation of St. Anthony', 'In this picture temptation appears to Saint Anthony successively in the form of a horse in the foreground representing strength, sometimes also the symbol of voluptuousness.', 4, '1946-1-1', 'Painting'),
((SELECT id FROM artist WHERE name = 'Pablo Picasso'), 'Guernica', 'The painting was created in response to the bombing of Guernica, a Basque Country town in northern Spain, by Nazi Germany and Italian warplanes at the request of the Spanish Nationalists.', 8, '1937-1-1', 'Painting'),
((SELECT id FROM artist WHERE name = 'Frida Kahlo de Rivera'), 'The Two Fridas', 'In Frida\'s dairy, she wrote about this painting and said it is originated from her memory of an imaginary childhood friend. Later she admitted it expressed her desperation and loneliness with the separation from Diego.', 2, '1939-1-1', 'Painting');

CREATE TABLE product (
	barcode VARCHAR(255) PRIMARY KEY,
	price INT NOT NULL,
	type VARCHAR(255) NOT NULL,	
	size VARCHAR(255),	
	color VARCHAR(255),
	FOREIGN KEY product_type_fk(type) REFERENCES product_type(name)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	FOREIGN KEY product_color_fk(color) REFERENCES product_color(name)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	FOREIGN KEY product_size_fk(size) REFERENCES product_size(name)
	ON UPDATE CASCADE
	ON DELETE RESTRICT			
);

CREATE TABLE product_artwork (
	artwork_id INT NOT NULL,
	product_barcode VARCHAR(255) NOT NULL,
	PRIMARY KEY(artwork_id, product_barcode),
	FOREIGN KEY product_artwork_id(artwork_id) REFERENCES artwork(id)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	FOREIGN KEY artwork_product_barcode(product_barcode) REFERENCES product(barcode)
	ON UPDATE CASCADE
	ON DELETE RESTRICT	 	
);

INSERT INTO product (barcode, price, type, size, color)
VALUES ('123456789', 10000, 'Keyring', 'M', 'BLUE');
INSERT INTO product_artwork (artwork_id, product_barcode)
VALUES (1,'123456789');

INSERT INTO product (barcode, price, type, size, color)
VALUES ('992245762', 5000, 'Postcard', NULL, NULL);
INSERT INTO product_artwork (artwork_id, product_barcode)
VALUES (1,'992245762');

INSERT INTO product (barcode, price, type, size, color)
VALUES ('555556722', 5000, 'T-shirt', NULL, NULL);
INSERT INTO product_artwork (artwork_id, product_barcode)
VALUES (2,'555556722');


CREATE TABLE exhibition (
	id 	INT PRIMARY KEY AUTO_INCREMENT,
	name  VARCHAR(255) NOT NULL,
	start DATE NOT NULL,
	end DATE NOT NULL,
	size INT NOT NULL
);

CREATE TABLE artwork_exhibition (
   artwork_id INT,
   exhibition_id INT,
   PRIMARY KEY(artwork_id, exhibition_id),
   FOREIGN KEY ae_artwork(artwork_id) REFERENCES artwork(id)
   ON UPDATE CASCADE
   ON DELETE RESTRICT,
   FOREIGN KEY ae_exhibition(exhibition_id) REFERENCES exhibition(id)
   ON UPDATE CASCADE
   ON DELETE RESTRICT
);

INSERT INTO exhibition (name, start, end, size) VALUES
('The grand opening', '2018-11-30', '2018-11-30', 150),
('New year', '2018-12-31', '2018-12-31', 200);

INSERT INTO artwork_exhibition (artwork_id, exhibition_id) VALUES
(1,1),
(2,1),
(2,2),
(3,2),
(4,2);


CREATE TABLE exhibition_guide (
	social_sec_no VARCHAR(255),
	exhibition_id INT,
	PRIMARY KEY (social_sec_no, exhibition_id),
	FOREIGN KEY eg_guide(social_sec_no) REFERENCES guide(social_sec_no)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	FOREIGN KEY eg_exhibition(exhibition_id) REFERENCES exhibition(id)
	ON UPDATE CASCADE
	ON DELETE RESTRICT	
);

INSERT INTO exhibition_guide (social_sec_no, exhibition_id) VALUES
('123456-7890', 1),
('123456-7890', 2),
('234567-8901', 2);

CREATE TABLE guided_tour (
	exhibition_id INT NOT NULL,
	guide_social_sec_no VARCHAR(255) NOT NULL,
    language VARCHAR(255) NOT NULL,
    start TIMESTAMP NOT NULL,
    end TIMESTAMP NOT NULL,
    PRIMARY KEY (guide_social_sec_no, start),
    FOREIGN KEY gt_exhibition(exhibition_id) REFERENCES exhibition(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY gt_guide(guide_social_sec_no) REFERENCES guide(social_sec_no)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY gt_language(language) REFERENCES language(name)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

INSERT INTO guided_tour (exhibition_id, guide_social_sec_no, language, start, end) VALUES
(1, '123456-7890', 'English', '2018-11-30 10:00:00', '2018-11-30 11:00:00'),
(1, '123456-7890', 'German', '2018-11-30 11:00:00', '2018-11-30 12:00:00'),
(2, '123456-7890', 'English', '2018-12-31 13:00:00', '2018-12-31 14:00:00'),
(2, '234567-8901', 'French', '2018-12-31 11:00:00', '2018-12-31 12:00:00');

