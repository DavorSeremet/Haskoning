CREATE DATABASE CarDealership;

USE CarDealership;

CREATE TABLE Car(
	CarId INT IDENTITY(1,1) PRIMARY KEY,
	Make VARCHAR(25) NOT NULL,
	Model VARCHAR(30) NOT NULL,
	Version VARCHAR(20) NOT NULL,
	CONSTRAINT UC_Car UNIQUE(Make, Model, Version)
);

CREATE TABLE CarInStore(
	CarInStoreId INT IDENTITY(1,1) PRIMARY KEY,
	CarId INT NOT NULL,
	YearOfRelease INT NOT NULL,
	Price FLOAT NOT NULL,
	FuelConsumption INT NOT NULL,
	AnnualMaintenanceCost FLOAT NOT NULL,
	FOREIGN KEY (CarId) REFERENCES Car(CarId)
);

--Insert new cars.
INSERT INTO Car VALUES('Audi', 'TT', 'S');
INSERT INTO Car VALUES('Audi', 'TT', 'RS');
INSERT INTO Car VALUES('Volkswagen', 'Golf', 'green');
INSERT INTO Car VALUES('Volkswagen', 'Golf', 'GTI');
INSERT INTO Car VALUES('Volkswagen', 'Polo', 'GTI');
INSERT INTO Car VALUES('Citroen', 'C3', 'Picasso');
INSERT INTO Car VALUES('Honda', 'Fit', 'diesel');

--Insert new cars in the dealership
INSERT INTO CarInStore VALUES(1, 2018, 45000, 19, 340.45);
INSERT INTO CarInStore VALUES(2, 2019, 75, 8, 444);
INSERT INTO CarInStore VALUES(3, 2018, 21000, 20, 120.75);
INSERT INTO CarInStore VALUES(4, 2015, 3500, 17, 100);
INSERT INTO CarInStore VALUES(5, 2017, 9000, 12, 175.99);
INSERT INTO CarInStore VALUES(6, 2012, 2500, 19, 750);
INSERT INTO CarInStore VALUES(7, 2018, 17499.99, 25, 100);