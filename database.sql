-- C:/Users/seemi/Desktop/School/Uni/Semester 6 S19/CSCC43/Project/CSCC43-Project/database.sql
DROP DATABASE IF EXISTS mybnb;
CREATE DATABASE mybnb;
USE mybnb;


-- DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE Users (
  sin INT UNSIGNED NOT NULL,

  name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,

  dob DATE NOT NULL,
  occupation VARCHAR(50) NOT NULL,
  phoneNum INT NOT NULL,

  UNIQUE (email),

  PRIMARY KEY (sin)
);


-- DROP TABLE IF EXISTS Listings CASCADE;
CREATE TABLE Listings (
  listing_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED NOT NULL,

  listing_type ENUM('Apartment',
                    'House',
                    'Secondary Unit',
                    'Bed and Breakfast',
                    'Boutique Hotel') NOT NULL DEFAULT 'Apartment',

  num_bedrooms TINYINT UNSIGNED NOT NULL,
  num_beds TINYINT UNSIGNED NOT NULL,
  num_bathrooms TINYINT UNSIGNED NOT NULL,

  title VARCHAR(64) NOT NULL,
  description VARCHAR(1000) NOT NULL,

  UNIQUE(title),

  INDEX(user_id),

  PRIMARY KEY(listing_id),
  FOREIGN KEY (user_id) REFERENCES Users(sin) ON DELETE CASCADE,

  CHECK (num_bedrooms >= 0),
  CHECK (num_beds > 0),
  CHECK (num_bathrooms > 0)
);


-- DROP TABLE IF EXISTS Address CASCADE;
CREATE TABLE Address (
  latitude DECIMAL(8,5) NOT NULL,
  longitude DECIMAL(8,5) NOT NULL,

  listing_id INT UNSIGNED,

  street VARCHAR(256) NOT NULL,
  unit VARCHAR(24),
  city VARCHAR(24) NOT NULL,
  state VARCHAR(24) NOT NULL,
  country VARCHAR(24) NOT NULL,
  zipCode VARCHAR(7) NOT NULL,

  UNIQUE (street, unit, city, state, country),

  INDEX (country),
  INDEX (city, country),
  INDEX (city, country, zipCode),

  PRIMARY KEY (listing_id),
  FOREIGN KEY (listing_id) REFERENCES Listings(listing_id) ON DELETE CASCADE,

  CHECK (latitude >= -180 AND latitude <= 180),
  CHECK (longitude >= -180 AND longitude <= 180)
);


-- DROP TABLE IF EXISTS Amenities CASCADE;
CREATE TABLE Amenities (
  amenity_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  amenity VARCHAR(50) NOT NULL,

  INDEX (amenity),
  UNIQUE (amenity),

  PRIMARY KEY (amenity_id)
);

INSERT INTO Amenities(amenity) VALUES('Essentials'),('WiFi'),('Shampoo'),('Closet/Drawers'),('TV'),('Heat'),('AC'),('Desk/Workspace'),('Fireplace'),('Iron'),('Hair Dryer'),('Breakfast, Coffee, Tea'),('Smoke Detector'),('Carbon Monoxide Detector'),('First Aid Kit'),('Fire Extinguisher'),('Locks on Doors');


-- DROP TABLE IF EXISTS ListingAmenities CASCADE;
CREATE TABLE ListingAmenities (
  listing_id INT UNSIGNED NOT NULL,
  amenity_id INT UNSIGNED NOT NULL,

  PRIMARY KEY (listing_id, amenity_id),
  FOREIGN KEY (listing_id) REFERENCES Listings(listing_id) ON DELETE CASCADE,
  FOREIGN KEY (amenity_id) REFERENCES Amenities(amenity_id) ON DELETE CASCADE
);


-- DROP TABLE IF EXISTS CreditInfo CASCADE;
CREATE TABLE CreditInfo (
  user_id INT UNSIGNED NOT NULL,

  card_num INT(20) UNSIGNED NOT NULL,
  card_type ENUM('VISA', 'Mastercard', 'American Express', 'Discover') NOT NULL DEFAULT 'VISA',
  exp_date DATE NOT NULL,

  INDEX(user_id),
  INDEX (card_num),

  PRIMARY KEY (user_id, card_num),
  FOREIGN KEY (user_id) REFERENCES Users(sin) ON DELETE CASCADE
);


-- DROP TABLE IF EXISTS Availability CASCADE;
CREATE TABLE Availability (
  availability_id INT UNSIGNED NOT NULL AUTO_INCREMENT,

  listing_use ENUM('Full', 'Private Room', 'Shared'),
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  price DECIMAL(7, 2) NOT NULL,
  available BOOL NOT NULL DEFAULT TRUE,

  listing_id INT UNSIGNED NOT NULL,

  INDEX (price),

  PRIMARY KEY (availability_id),
  FOREIGN KEY (listing_id) REFERENCES Listings(listing_id) ON DELETE CASCADE,

  CHECK (price > 0) -- check price
);


-- DROP TABLE IF EXISTS Bookings CASCADE;
CREATE TABLE Bookings (
  booking_id INT UNSIGNED NOT NULL AUTO_INCREMENT,

  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  status ENUM('Booked', 'Cancelled by Host', 'Cancelled by Renter') NOT NULL DEFAULT 'Booked',

  listing_id INT UNSIGNED NOT NULL,
  user_id INT UNSIGNED NOT NULL,
  card_num INT(20) UNSIGNED NOT NULL,
  cost DECIMAL(7, 2) NOT NULL,

  INDEX (user_id),
  INDEX (listing_id),

  PRIMARY KEY (booking_id),

  FOREIGN KEY (listing_id) REFERENCES Listings(listing_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES Users(sin),
  FOREIGN KEY (card_num) REFERENCES CreditInfo(card_num)
);


-- DROP TABLE IF EXISTS ListingComments CASCADE;
CREATE TABLE ListingComments (
  comment VARCHAR(1000) NOT NULL,
  date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  renter_id INT UNSIGNED NOT NULL,
  listing_id INT UNSIGNED NOT NULL,
  booking_id INT UNSIGNED NOT NULL,
  rating INT UNSIGNED NOT NULL,

  INDEX (renter_id),
  INDEX (listing_id),
  INDEX (rating),

  PRIMARY KEY (booking_id),

  FOREIGN KEY (listing_id) REFERENCES Listings(listing_id) ON DELETE CASCADE,
  FOREIGN KEY (renter_id) REFERENCES Users(sin) ON DELETE CASCADE,
  FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE,

  CHECK (rating >= 0  AND rating <= 5)
);


-- DROP TABLE IF EXISTS UserComments CASCADE;
CREATE TABLE UserComments (
  comment VARCHAR(1000) NOT NULL,
  date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  commenter_id INT UNSIGNED NOT NULL,
  commentee_id INT UNSIGNED NOT NULL,
  booking_id INT UNSIGNED NOT NULL,
  rating INT UNSIGNED NOT NULL,

  INDEX (commenter_id),
  INDEX (commentee_id),
  INDEX (rating),

  PRIMARY KEY (commenter_id, booking_id),

  FOREIGN KEY (commenter_id) REFERENCES Users(sin) ON DELETE CASCADE,
  FOREIGN KEY (commentee_id) REFERENCES Users(sin) ON DELETE CASCADE,
  FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE,

  CHECK (commentee_id != commenter_id),
  CHECK (rating >= 0  AND rating <= 5)
);


-- The view is actually a virtual table that was made by a select statement.
-- Instead of sending the complex query to the database all the time, you can save the query as a view and then `SELECT * FROM view`.

-- to get num listings
CREATE OR REPLACE VIEW listingCountByCountryCityZip AS
  (SELECT A.country, A.city, A.zipCode, COUNT(A.listing_id) AS listing_count
		FROM Address A
		GROUP BY A.country, A.city, A.zipCode
	);
-- to get num listings
CREATE OR REPLACE VIEW listingCountByCountryCity AS
  (SELECT L.country, L.city, SUM(L.listing_count) AS listing_count
		FROM listingCountByCountryCityZip L
		GROUP BY L.country, L.city
	);
-- to get num listings
CREATE OR REPLACE VIEW listingCountByCountry AS
	(SELECT L.country, SUM(L.listing_count) AS listing_count
		FROM listingCountByCountryCity L
		GROUP BY L.country
  );

-- to get num listings per host
CREATE OR REPLACE VIEW listingsCountPerHostByCity AS
	(SELECT L.user_id, A.country, A.city, COUNT(L.listing_id) AS listing_count
		FROM Listings L
		LEFT JOIN Address A ON L.listing_id = A.listing_id
		GROUP BY L.user_id, A.country, A.city
	);
-- to get num listings per host
CREATE OR REPLACE VIEW listingsCountPerHostByCountry AS
	(SELECT L.user_id, L.country, COUNT(L.listing_count) AS listing_count
		FROM listingsCountPerHostByCity L
		GROUP BY L.user_id, L.country
	);

-- to get commercial hosts
CREATE OR REPLACE VIEW commercialHostPerCity AS
	(SELECT L.user_id, L.country, L.city, L.listing_count
		FROM listingsCountPerHostByCity L, listingCountByCountryCity K
		WHERE L.listing_count/K.listing_count > 0.1
		GROUP BY L.user_id, L.country, L.city
	);
-- to get commercial hosts
CREATE OR REPLACE VIEW commercialHostPerCountry AS
	(SELECT L.user_id, L.country, L.listing_count
		FROM listingsCountPerHostByCountry L, listingCountByCountry K
		WHERE L.listing_count/K.listing_count > 0.1
		GROUP BY L.user_id, L.country
	);

----

-- to get total number of bookings
CREATE OR REPLACE VIEW bookingsPerCityZipCodeAndPeriod AS
(SELECT A.country, A.city, A.zipCode, B.start_date, B.end_date, Count(B.booking_id) AS booking_count
	FROM Bookings B
	LEFT JOIN Address A ON B.listing_id = A.listing_id
	GROUP BY A.country, A.city, A.zipCode
);

-- to get total number of bookings
CREATE OR REPLACE VIEW bookingsPerCityAndPeriod AS
(SELECT B.country, B.city, B.start_date, B.end_date, Count(B.booking_count) AS booking_count
	FROM bookingsPerCityZipCodeAndPeriod B
	GROUP BY B.country, B.city
);

-- to rank users by bookings
CREATE OR REPLACE VIEW userBookingsCountPerCityAndPeriod AS
(SELECT A.country, A.city, B.user_id, B.start_date, B.end_date, Count(B.booking_id) AS booking_count
	FROM Bookings B
	LEFT JOIN Address A ON B.listing_id = A.listing_id
	GROUP BY A.country, A.city, B.user_id
);
-- to rank users by bookings
CREATE OR REPLACE VIEW userBookingsCountAndPeriod AS
(SELECT B.user_id, B.start_date, B.end_date, Count(B.booking_count) AS booking_count
	FROM userBookingsCountPerCityAndPeriod B
	GROUP BY B.user_id
);

-- rank users by cancellations
CREATE OR REPLACE VIEW bookingCancellations AS
	(SELECT B.user_id, B.status, COUNT(*) AS cancel_count
		FROM Bookings B
		WHERE B.end_date > (CURDATE() - INTERVAL 1 YEAR)
			AND (B.status <> 'Booked')
		GROUP BY B.user_id
	);

CREATE OR REPLACE VIEW allListingComments AS
	(SELECT L.listing_id, L.comment
		FROM ListingComments L
		GROUP BY L.listing_id
	);
