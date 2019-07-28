
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('66.99000', '56.04000', 1, '6385 Mylene Dale', 'Suite 394', 'Alexzanderstad', 'Florida', 'Lithuania', '35130-7');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('155.86000', '86.38000', 2, '0480 Boyd River', 'Apt. 126', 'Berniceton', 'Tennessee', 'Greece', '56643');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('-105.90000', '14.94000', 3, '4896 Rosa Dale Suite 954', 'Apt. 312', 'East Marjolainefurt', 'Oregon', 'Bouvet Island (Bouvetoya', '53161-9');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('84.41000', '30.21000', 4, '864 Charlene Lodge Apt. 433', 'Apt. 975', 'Margarettemouth', 'Louisiana', 'Guinea', '81989-9');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('-62.39000', '167.04000', 5, '817 Parisian Glens', 'Apt. 657', 'Millermouth', 'Texas', 'Kuwait', '94231');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('111.39000', '41.39000', 6, '988 Skyla Green', 'Apt. 428', 'Collinsland', 'RhodeIsland', 'Saint Barthelemy', '15444');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('77.30000', '-143.01000', 7, '52504 Nola Island', 'Suite 379', 'Sheldonville', 'RhodeIsland', 'Nicaragua', '18969-0');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('-59.71000', '71.70000', 8, '947 Barton Radial Suite 894', 'Suite 047', 'Gretaborough', 'NorthDakota', 'Australia', '93695');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('-176.57000', '-94.19000', 9, '288 Ed Villages Apt. 937', 'Suite 721', 'West Elnora', 'Arkansas', 'Uzbekistan', '29244');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('-108.64000', '-106.15000', 10, '0376 Anika Radial Apt. 746', 'Apt. 674', 'South Claudiemouth', 'District of Columbia', 'Yemen', '77405-0');

INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (1, 'Full', '1979-05-14', '1995-03-06', '99999.99', 1, 1);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (2, 'Full', '2007-03-19', '1996-12-25', '1657.21', 0, 2);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (3, 'Shared', '1985-06-26', '1991-04-13', '5.84', 0, 3);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (4, 'Full', '2007-03-25', '1972-01-02', '7.10', 1, 4);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (5, 'Shared', '2005-08-03', '2018-10-30', '27.11', 0, 5);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (6, 'Full', '1975-11-23', '2004-04-10', '3.70', 0, 6);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (7, 'Shared', '2018-09-04', '2001-12-10', '99999.99', 1, 7);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (8, 'Full', '2017-02-20', '1982-06-19', '0.48', 0, 8);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (9, 'Shared', '2005-11-21', '1988-08-07', '129.47', 1, 9);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (10, 'Shared', '2006-06-23', '1988-09-01', '99999.99', 1, 10);

INSERT INTO `Bookings` (`booking_id`, `start_date`, `end_date`, `status`, `listing_id`, `user_id`, `card_num`, `cost`) VALUES (1, '1977-09-05', '2000-09-28', 'Booked', 1, 0, 4294967295, '291.01');

INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (0, 4294967295, 'American Express', '1978-09-06');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (7, 4294967295, 'Discover', '1995-09-11');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (8, 4294967295, 'VISA', '1993-08-08');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (24, 4294967295, 'Discover', '1978-05-10');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (53, 4294967295, 'American Express', '2013-05-04');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (55, 4294967295, 'Discover', '1981-08-30');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (574, 4294967295, 'VISA', '1987-06-02');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (3484089, 4294967295, 'American Express', '1983-12-29');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (9713907, 4294967295, 'Discover', '2010-03-19');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (223719705, 4294967295, 'American Express', '1992-10-10');

-- INSERT INTO `ListingAmenities` (

INSERT INTO `ListingComments` (`comment`, `date`, `renter_id`, `listing_id`, `booking_id`, `rating`) VALUES ('Accusantium ut possimus eligendi sint qui officia. Dolores qui consequatur beatae voluptatem. Asperiores explicabo molestias non et impedit doloribus quasi. Quisquam quos similique et et ut unde quasi.', '1993-05-26 22:40:32', 0, 1, 1, 5);

INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (1, 0, 'Apartment', 5, 7, 4, 'Placeat et et qui sed tempore.', 'Consequatur delectus perspiciatis nobis hic aperiam blanditiis voluptatem. Hic corporis qui nihil accusamus dolorem perferendis repellendus. Nihil maxime libero at ut hic occaecati explicabo. Eaque est saepe consequatur nam modi nulla ea.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (2, 7, 'Secondary Unit', 3, 9, 5, 'Et quas in voluptatem aut rerum.', 'Natus deleniti dolor saepe dolores consectetur. Magnam quia quaerat voluptatem et eos quibusdam. Qui consequatur aspernatur natus et.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (3, 8, 'House', 3, 3, 4, 'Quos porro eum alias sint.', 'Sit suscipit ab facilis non voluptatem. Molestiae dolorem et odio voluptate recusandae laborum sed. Laborum excepturi consequatur nisi unde aut fuga molestias. Voluptatem libero culpa eum veniam.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (4, 24, 'Secondary Unit', 2, 2, 9, 'Sequi distinctio recusandae qui quisquam sequi enim quibusdam.', 'Voluptatibus itaque laborum id enim saepe accusantium labore temporibus. Deleniti consectetur minus libero voluptatem qui odio quo. Repellendus illo qui animi laboriosam fugit omnis inventore. Ab suscipit veritatis perspiciatis facilis aspernatur quaerat mollitia ut. Modi illum omnis est modi nemo nihil in.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (5, 53, 'Apartment', 8, 4, 8, 'Sint quae quasi ad eveniet alias provident animi a.', 'Similique dolor expedita voluptas. Dignissimos ut suscipit molestiae fuga consequatur corrupti nostrum. Occaecati quas occaecati sint iste. Pariatur nihil aut qui eum accusantium asperiores est.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (6, 55, 'Apartment', 7, 4, 4, 'Voluptas est nobis ipsam eius reiciendis ut recusandae.', 'Ratione et nam iusto beatae repellendus rerum. Corporis quis quidem sed. Soluta non magni ad nihil unde placeat officiis.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (7, 574, 'Bed and Breakfast', 8, 3, 9, 'Qui corrupti sint voluptatem maiores et autem et.', 'Ratione et sed hic doloribus iusto repellendus. A aut dolorum qui possimus laborum corporis quam sint. Enim consequuntur tempora saepe dolor.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (8, 3484089, 'Apartment', 9, 2, 2, 'Sed nihil est autem dolores earum.', 'Dicta nobis ipsam ipsum sed sit esse enim. Error sed et repellendus nostrum quidem magni vel quaerat. Harum excepturi repudiandae quaerat consequuntur architecto sit vel.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (9, 9713907, 'Bed and Breakfast', 1, 4, 5, 'Sit molestias voluptatem cupiditate necessitatibus et quia.', 'Dolorem vero sed saepe deserunt voluptatum repellat dolor. Qui fugit harum quod ea consequatur ut et.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (10, 223719705, 'Apartment', 5, 5, 5, 'Esse deserunt ullam distinctio repudiandae.', 'Sint aut qui explicabo id illo dignissimos quam. Quam rem unde voluptates hic ullam et dolorem. Ut minus eos autem accusamus numquam. Beatae quis facilis blanditiis ut.');

-- INSERT INTO `UserComments`

INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (0, 'Karl Moore', 'crau@example.net', '5d168de049eea10abd899dc56b6c08f5b64ec954', '0000-00-00', 'incidunt', 733836);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (7, 'Dr. Felicita Zulauf DDS', 'ashlee48@example.com', '016d252c95573710deca46913166479244ed1b23', '0000-00-00', 'ut', 206680);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (8, 'Elna Roberts', 'pluettgen@example.net', '4b705ab77395e2e1fc7337d358e751ad3bfeb38a', '0000-00-00', 'et', 578288);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (24, 'Dr. Salma Mante', 'alysa50@example.org', 'b93624832674c65169e7f7754ad8aded628a1d25', '0000-00-00', 'voluptatem', 1);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (53, 'Prof. Michel Hane', 'julianne05@example.org', '628343437226ab7195d2f9c59c652b312eea9c4c', '0000-00-00', 'consequatur', 1);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (55, 'Miss Noemie McKenzie DDS', 'abbey68@example.org', '7a52233179133dab236a2a46d848c37b31a149b4', '0000-00-00', 'dolorum', 1);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (574, 'Watson Heaney', 'sim.welch@example.com', 'e3f0dc6b783cfc91ecc2b8faf2c7ec39a4400c8e', '0000-00-00', 'quaerat', 884);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (3484089, 'Karelle Schinner', 'hand.roscoe@example.org', '081d8791e18fac2e7ad5875f8b83f0b693d38cf3', '0000-00-00', 'sint', 0);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (9713907, 'Miss Edwina Emmerich IV', 'blaise65@example.com', '94c8b2f21d53dc94f125af06f4bbea03f5c5b200', '0000-00-00', 'et', 557377);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (223719705, 'Prof. Christophe Hamill', 'tillman16@example.org', '7289e0bafac587c73d97eedcc3e4414a368e132d', '0000-00-00', 'pariatur', 1);
