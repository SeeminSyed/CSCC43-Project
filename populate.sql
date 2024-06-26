-- C:/Users/seemi/Desktop/School/Uni/Semester 6 S19/CSCC43/Project/CSCC43-Project/populate.sql

INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (10, '1', '1', '1', '1970-04-11', '1', 1);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (0, 'Mrs. Jakayla Swaniawski', 'caroline91@example.net', '2c7313b9ada', '1970-04-11', 'cupiditate', 922669);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (2, 'Prof. Greyson Glover', 'arlo23@example.org', 'de421df36049eb423', '1980-11-27', 'facere', 1);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (1, 'Prof. Skylar Tromp III', 'matilde11@example.org', '95dd7f03ae8e', '1991-02-05', 'quia', 1);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (3, 'Dr. Lamont Hodkiewicz Jr.', 'hettinger.alejandra@example.com', '2281ba69a608e236f', '1978-12-18', 'dolorem', 810089);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (4, 'Mrs. Lina Tremblay PhD', 'emmitt.vonrueden@example.org', '7389eff76cc3d2a34a49992', '1997-02-14', 'consectetur', 1);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (5, 'Tevin Schamberger', 'wellington.cole@example.org', 'f6a41b88190428430eb8f56a0a560', '1987-07-31', 'ut', 665754);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (6, 'Dr. Geo Nikolaus', 'schmitt.antonietta@example.com', 'dc2fe190b0ad390f955d68a71a9', '1996-07-19', 'beatae', 28468);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (7, 'Prof. Marietta Bailey', 'simonis.kaitlin@example.org', '14b7c06ee2bfedbb1a23bd9ef', '1988-11-11', 'ducimus', 124181);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (8, 'Lucio Batz', 'toy.jeanne@example.org', 'd1d10be1a61', '1975-02-08', 'aliquam', 2147483647);
INSERT INTO `Users` (`sin`, `name`, `email`, `password`, `dob`, `occupation`, `phoneNum`) VALUES (9, 'Niko Haley', 'tamara.goldner@example.net', '2f437ad', '1987-08-14', 'ea', 35964);



INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (10, 2394, 'Mastercard', '2022-11-14');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (0, 294, 'Mastercard', '2090-11-14');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (2, 495, 'Discover', '2090-11-14');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (3, 65, 'Mastercard', '2023-08-26');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (4, 43, 'Mastercard', '2034-06-09');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (5, 425, 'Mastercard', '2045-01-19');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (6, 4235, 'Mastercard', '2078-05-17');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (7, 429, 'VISA', '2021-04-25');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (8, 295, 'Mastercard', '2024-05-15');
INSERT INTO `CreditInfo` (`user_id`, `card_num`, `card_type`, `exp_date`) VALUES (9, 495, 'Discover', '2020-12-13');


INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (16, 10, 'House', 5, 1, 1, 'Qui itaque  corporis.', 'Consequuntur qui quo quia odit. Consequatur error corrupti et occaecati ea voluptas. Et inventore sint voluptatem ut rerum voluptas quia. Deleniti nesciunt doloribus facilis.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (11, 0, 'House', 5, 1, 1, 'Qui itaque voluptate corporis.', 'Consequuntur qui quo quia odit. Consequatur error corrupti et  ea voluptas. Et inventore sint voluptatem ut rerum voluptas quia. Deleniti nesciunt doloribus facilis.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (12, 1, 'House', 5, 5, 3, 'Illo molestiae quia perferendis iusto quasi.', 'Fugiat id aliquid dolorem officia dolorem. Illum eos culpa voluptas ut neque quod. Molestiae sint ut aspernatur ut. Iure at esse quos.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (13, 6, 'Secondary Unit', 3, 4, 4, 'Iste placeat soluta eos deleniti iusto.', 'Beatae maxime dolorem modi explicabo modi qui nam. Repudiandae autem quasi ipsam ipsam. Similique voluptas pariatur excepturi. Laudantium vitae ut expedita iure.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (14, 7, 'Secondary Unit', 4, 1, 4, 'Exercitationem fugit ullam quos autem.', 'Sed et in delectus maxime. Nam quibusdam molestiae rerum tempora. Et commodi reprehenderit ut aut. Itaque impedit harum ducimus minus asperiores dolores id facilis.');
INSERT INTO `Listings` (`listing_id`, `user_id`, `listing_type`, `num_bedrooms`, `num_beds`, `num_bathrooms`, `title`, `description`) VALUES (15, 8, 'House', 1, 5, 2, 'Et tempore quis aut consequatur necessitatibus officiis non.', 'Quod et nulla assumenda rerum unde. Consequatur repellendus aperiam aspernatur corrupti suscipit. Consequatur dolores qui perferendis vel. Non sapiente laudantium nihil quia.');


INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('2.00000', '0.00400', 11, '61353 Eichmann Trail Suite 888', '6', 'Strosinburgh', 'Kansas', 'Jersey', '57164-0');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('4.55500', '0.55000', 16, '61353 Eichmanp Trail Suite 888', '6', 'Strosinburgh', 'Kansas', 'Jersey', '57164-0');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('0.46000', '0.62400', 12, '030 Kerluke Locks', '', 'D\'Amorebury', 'Alaska', 'Namibia', '71625');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('0.70900', '3.04900', 13, '14712 Jamal Drive Apt. 483', '4', 'West Katrine', 'Alabama', 'Costa Rica', '64850-8');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('0.89000', '0.06800', 14, '0952 Greenholt Locks', '7', 'New Sandratown', 'Georgia', 'Swaziland', '32589-3');
INSERT INTO `Address` (`latitude`, `longitude`, `listing_id`, `street`, `unit`, `city`, `state`, `country`, `zipCode`) VALUES ('0.00000', '0.00000', 15, '89518 Smitham Mall', '9', 'South Devinmouth', 'Mississippi', 'Svalbard & Jan Mayen Isl', '18640-0');


INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (11, 1);
INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (11, 6);
INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (12, 2);
INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (12, 7);
INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (13, 3);
INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (13, 8);
INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (14, 4);
INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (14, 9);
INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (16, 5);
INSERT INTO `ListingAmenities` (`listing_id`, `amenity_id`) VALUES (16, 10);


INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (1, 'Shared', '2012-10-15', '1986-07-12', '733.44', 2, 11);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (2, 'Private Room', '1987-04-03', '1989-09-08', '98.87', 1, 12);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (3, 'Private Room', '1979-04-15', '1994-08-24', '364.05', 1, 13);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (4, 'Shared', '1980-01-25', '2005-07-01', '63.71', 2, 14);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (5, 'Private Room', '1991-06-20', '2018-02-19', '929.94', 3, 15);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (6, 'Full', '1981-04-17', '1987-03-11', '319.14', 3, 11);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (7, 'Shared', '1976-02-04', '1987-02-02', '634.33', 2, 12);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (8, 'Full', '2013-11-07', '1986-12-11', '787.86', 3, 13);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (9, 'Private Room', '1976-06-02', '2016-04-04', '831.04', 1, 14);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (10, 'Shared', '1973-02-13', '1992-01-02', '74.65', 1, 15);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (11, 'Shared', '1973-02-13', '1992-01-02', '74.65', 1, 16);
INSERT INTO `Availability` (`availability_id`, `listing_use`, `start_date`, `end_date`, `price`, `available`, `listing_id`) VALUES (12, 'Full', '2013-11-07', '1986-12-11', '787.86', 3, 16);


INSERT INTO `Bookings` (`booking_id`, `start_date`, `end_date`, `status`, `listing_id`, `user_id`, `card_num`, `cost`) VALUES (1, '2017-12-01', '2015-05-17', 'Cancelled by Renter', 11, 0, 2394, '588.22');
INSERT INTO `Bookings` (`booking_id`, `start_date`, `end_date`, `status`, `listing_id`, `user_id`, `card_num`, `cost`) VALUES (2, '2003-04-19', '2009-02-24', 'Cancelled by Host', 12, 1, 294, '597.38');
INSERT INTO `Bookings` (`booking_id`, `start_date`, `end_date`, `status`, `listing_id`, `user_id`, `card_num`, `cost`) VALUES (3, '2018-06-22', '2005-12-14', 'Cancelled by Renter', 13, 2, 495, '676.40');
INSERT INTO `Bookings` (`booking_id`, `start_date`, `end_date`, `status`, `listing_id`, `user_id`, `card_num`, `cost`) VALUES (4, '2014-12-08', '1988-10-26', 'Cancelled by Renter', 14, 3, 65, '268.36');
INSERT INTO `Bookings` (`booking_id`, `start_date`, `end_date`, `status`, `listing_id`, `user_id`, `card_num`, `cost`) VALUES (5, '1981-03-21', '1999-01-28', 'Booked', 15, 4, 43, '246.03');
INSERT INTO `Bookings` (`booking_id`, `start_date`, `end_date`, `status`, `listing_id`, `user_id`, `card_num`, `cost`) VALUES (7, '1981-03-21', '1999-01-28', 'Booked', 15, 4, 43, '246.03');
INSERT INTO `Bookings` (`booking_id`, `start_date`, `end_date`, `status`, `listing_id`, `user_id`, `card_num`, `cost`) VALUES (6, '1981-03-21', '1999-01-28', 'Booked', 16, 10, 2394, '400.03');


INSERT INTO `ListingComments` (`comment`, `date`, `renter_id`, `listing_id`, `booking_id`, `rating`) VALUES ('Actually, one of my best airbnb experiences, neat and stylish place. Great value for money. Kitchen was excellent: gas stove with kettle, microwave oven. Communication with Graziella was easy. Id love to stay more..', '2015-09-29 13:34:08', 0, 11, 1, 1);
INSERT INTO `ListingComments` (`comment`, `date`, `renter_id`, `listing_id`, `booking_id`, `rating`) VALUES ('Graziella was a wonderful host. She responded quickly to messages and was very helpful with advice on how to get around. The place was exactly as pictured and check-in/check-out were seamless and simple. Thanks again!.', '1976-10-04 06:12:28', 1, 12, 2, 3);
INSERT INTO `ListingComments` (`comment`, `date`, `renter_id`, `listing_id`, `booking_id`, `rating`) VALUES ('Maiores eos corrupti rerum et maiores id. Suscipit qui amet sed dolorem quae modi. Et exercitationem natus aliquid et maxime. Blanditiis modi aut ut quibusdam odit.', '2004-01-06 15:49:36', 2, 13, 3, 4);
INSERT INTO `ListingComments` (`comment`, `date`, `renter_id`, `listing_id`, `booking_id`, `rating`) VALUES ('Reprehenderit et ad distinctio natus. Possimus nemo porro iusto. Id et libero quos facilis quaerat cumque. Cupiditate assumenda ipsum ab et nemo. Quia facere dolorum magnam aspernatur.', '2013-03-13 01:59:50', 3, 14, 4, 3);
INSERT INTO `ListingComments` (`comment`, `date`, `renter_id`, `listing_id`, `booking_id`, `rating`) VALUES ('Actually, one of  best airbnb experiences, neat and stylish place. Great value for money. Kitchen was excellent: gas stove with kettle, microwave oven. Communication with Graziella was easy. Id love to stay more..', '2015-09-29 13:34:08', 5, 15, 7, 5);
INSERT INTO `ListingComments` (`comment`, `date`, `renter_id`, `listing_id`, `booking_id`, `rating`) VALUES ('Graziella was a wonderful host. She responded quickly  messages and was very helpful with advice on how to get around. The place was exactly as pictured and check-in/check-out were seamless and simple. Thanks again!.', '1976-10-04 06:12:28', 6, 15, 5, 3);

INSERT INTO `UserComments` (`comment`, `date`, `commenter_id`, `commentee_id`, `booking_id`, `rating`) VALUES ('Actually,  with kettle, microwave oven. Communication with Graziella was easy. Id love to stay more..', '2015-09-29 13:34:08', 5, 10, 7, 5);
INSERT INTO `UserComments` (`comment`, `date`, `commenter_id`, `commentee_id`, `booking_id`, `rating`) VALUES ('Graziella e responded quickly  messages and was very helpful with advice on how to get around. /check-out were seamless and simple. Thanks again!.', '1976-10-04 06:12:28', 6, 10, 5, 3);
