-- C:\Users\seemi\Desktop\School\Uni\Semester 6 S19\CSCC43\Project\CSCC43-Project\database.sql
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
  listing_id INT UNSIGNED NOT NULL,
  amenity ENUM('Essentials',
               'WiFi',
               'Shampoo',
               'Closet/Drawers',
               'TV',
               'Heat',
               'AC',
               'Desk/Workspace',
               'Fireplace',
               'Iron',
               'Hair Dryer',
               'Breakfast, Coffee, Tea',
               'Smoke Detector',
               'Carbon Monoxide Detector',
               'First Aid Kit',
               'Fire Extinguisher',
               'Locks on Bedrooms'),

  INDEX (amenity),

  PRIMARY KEY (listing_id, amenity),
  FOREIGN KEY (listing_id) REFERENCES Listings(listing_id) ON DELETE CASCADE
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
  status ENUM('Booked', 'Canceled by Host', 'Canceled by Renter') NOT NULL DEFAULT 'Booked',

  listing_id INT UNSIGNED NOT NULL,
  user_id INT UNSIGNED NOT NULL,
  card_num INT(20) UNSIGNED NOT NULL,

  INDEX (user_id),
  INDEX (listing_id),

  PRIMARY KEY (booking_id),

  FOREIGN KEY (listing_id) REFERENCES Listings(listing_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES Users(sin) ON DELETE CASCADE,
  FOREIGN KEY (card_num) REFERENCES CreditInfo(card_num) ON DELETE CASCADE
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
  listing_id INT UNSIGNED NOT NULL,
  booking_id INT UNSIGNED NOT NULL,
  rating INT UNSIGNED NOT NULL,

  INDEX (commenter_id),
  INDEX (commentee_id),
  INDEX (listing_id),
  INDEX (rating),

  PRIMARY KEY (booking_id),

  FOREIGN KEY (listing_id) REFERENCES Listings(listing_id) ON DELETE CASCADE,
  FOREIGN KEY (commenter_id) REFERENCES Users(sin) ON DELETE CASCADE,
  FOREIGN KEY (commentee_id) REFERENCES Users(sin) ON DELETE CASCADE,
  FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE,

  CHECK (commentee_id != commenter_id),
  CHECK (rating >= 0  AND rating <= 5)
);


-- The view is actually a virtual table that was made by a select statement.
-- Instead of sending the complex query to the database all the time, you can save the query as a view and then `SELECT * FROM view`.
--
--
-- LOCK TABLES `address` WRITE;
-- /*!40000 ALTER TABLE `address` DISABLE KEYS */;
-- INSERT INTO `address` VALUES ();
-- /*!40000 ALTER TABLE `address` ENABLE KEYS */;
-- UNLOCK TABLES;
