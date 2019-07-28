package pages;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import database.DatabaseSelector;
import models.Availability;
import models.Booking;
import models.Listing;
import models.ListingComment;
import models.User;
import exceptions.*;

public class HostedListings {

  public static void main(Scanner userInput, User user) {
    // get user's listings
    List<Listing> userListings;
    int userChoice;
    int i = 0;
    Listing listing;
    // loop till exit
    do {
      i = 0;
      userListings = user.getListings();
      System.out.println(">>>");
      for (Listing ll : userListings) {
        i++;
        System.out.println(i + ". " + ll.toStringBasic());
        System.out.println("------------------------------------------------");
      }
      System.out.println(">>>");
      // view -> remove, edit/add availability
      System.out.print(" 1. Add a Listing\n" + " 2. View Listing\n" + " 0. Go Back \n");
      System.out.print("Enter the option number: ");
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        userChoice = -1;
      }

      // add, view, back
      switch (userChoice) {
        case 1:
          System.out.println("To Add Listing... ");
          try {
            addListingForm(userInput, user);
          } catch (EmptyFormException e) {
            System.out.println("Invalid input. Going back.");
          }
          break;
        case 2:
          try {
            // Get which listing
            System.out
                .print("Input the number of the listing you would like to view or 0 to cancel: ");
            int listingNum = 0;
            try {
              listingNum = Integer.parseInt(userInput.nextLine());
            } catch (NoSuchElementException | NumberFormatException invalid) {
              throw new EmptyFormException();
            }
            // parse option
            if (listingNum == 0) {
            } else if (listingNum > 0 && listingNum < user.getNumListings()) {
              listing = user.getListings().get(listingNum - 1);
              System.out.println(listing.toString());
              viewListing(userInput, user, listing);
            } else {
              throw new EmptyFormException();
            }
          } catch (EmptyFormException invaid) {
            System.out.println("Invalid input. Going back.");
          }
          break;
        case 0:
          System.out.println("Going Back... ");
          break;
        default:
          System.out.println(">>> Command not recognized. Please try again. >>>");
          break;
      }
    } while (userChoice != 0);
  }

  private static void addListingForm(Scanner userInput, User user) throws EmptyFormException {
    String userChoice;
    Double x;
    Double y;
    String unit;
    String street;
    String city;
    String state;
    String country;
    String zipCode;

    // get coordinates
    System.out.println("Please input the following or nothing to exit:");
    System.out.print(" Latitude: ");
    try {
      x = Double.parseDouble(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }
    System.out.print(" Longitude: ");
    try {
      y = Double.parseDouble(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }
    System.out.print(" Street: ");
    street = userInput.nextLine();
    System.out.print(" Unit (leave empty if not applicable): ");
    try {
      unit = userInput.nextLine();
    } catch (NoSuchElementException empty) {
      unit = "";
    }
    System.out.print(" City: ");
    city = userInput.nextLine();
    System.out.print(" State/Provincee: ");
    state = userInput.nextLine();
    System.out.print(" Country: ");
    country = userInput.nextLine();
    System.out.print(" Postal/Zip Code: ");
    zipCode = userInput.nextLine();

    // check if address already in database
    if (!newAddress(unit, x, y)) {
      System.out.println("Address already used.");
      return;
    }
    // ('Apartment', 'House', 'Secondary Unit', 'Bed and Breakfast', 'Boutique Hotel')
    String listingType;
    int numBedrooms;
    int numBeds;
    int numBathrooms;
    String title;
    String description;

    // if not, get listing info
    System.out.println(
        " Listing Type (Input the chosen number): \n 1.'Apartment'\n 2.'House', 3.'Secondary Unit'\n 4.'Bed and Breakfast'\n 5. Boutique Hotel\n");
    listingType = userInput.nextLine();
    if (listingType.isEmpty()) {
      throw new EmptyFormException();
    } else {
      switch (listingType) {
        case "1":
          System.out.println(" 1.'Apartment' chosen.");
          listingType = "Apartment";
          break;
        case "2":
          System.out.println(" 2.'House' chosen.");
          listingType = "House";
          break;
        case "3":
          System.out.println(" 3.'Secondary Unit' chosen.");
          listingType = "Secondary Unit";
          break;
        case "4":
          System.out.println(" 4.'Bed and Breakfast' chosen.");
          listingType = "Bed and Breakfast";
          break;
        case "5":
          System.out.println(" 4.'Boutique Hotel' chosen.");
          listingType = "Boutique Hotel";
          break;
        default:
          throw new EmptyFormException();
      }
    }
    System.out.print(" Number of Bedrooms: ");
    try {
      numBedrooms = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }
    System.out.print(" Number of Bed: ");
    try {
      numBeds = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }
    System.out.print(" Number of Bathrooms: ");
    try {
      numBathrooms = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }

    System.out.print(" Title: ");
    title = userInput.nextLine();
    System.out.print(" Description: ");
    description = userInput.nextLine();

    // get amenities
    Set<String> chosenAmenities = new HashSet<>();
    System.out.println(
        "Enter 1 to add some amenities to your listing. (Amenities increase the price you can set for a booking)");
    System.out.print(" Option: ");
    userChoice = userInput.nextLine();
    if (userChoice.contentEquals("1")) {
      chosenAmenities = viewAddAmenities(user, userInput);
    }

    // add listing to database
    if (user.addListing(listingType, numBedrooms, numBeds, numBathrooms, title,
        description) == false) {
      return;
    }
    Listing newListing = user.getListings().get(user.getNumListings() - 1);

    // add address
    newListing.addListingAddress(unit, street, city, state, country, zipCode, x, y);
    // add amenities
    newListing.addListingAmenities(chosenAmenities);

    // get availability for listing
    System.out.println(
        "Add availability for the listing. (The listing cannot be booked until you provide its availability)");
    try {
      addAvailabilityForm(userInput, newListing);
    } catch (InvalidFormException rejected) {
      System.out.println("Invalid Input.");
    }
  }

  /**
   * Return set of amenities user wants to add to listing
   */
  private static Set<String> viewAddAmenities(User user, Scanner userInput) {
    List<String> amenities = user.getAllAmenities();
    Set<String> userAmenities = new HashSet<>();
    System.out
        .println("Amenities. The expected increase in price per amenity is listed on the right");
    int i = 0;
    for (String amenity : amenities) {
      i++;
      System.out.println(" " + i + ". " + amenity + " -- $" + user.getAmenityPrice(amenity));
    }

    System.out.println(
        "Enter the option number, seperated by spaces, of the amenities you would like to add.");
    System.out.print(" Options: ");
    String userChoice = userInput.nextLine();
    String[] options = userChoice.split("\\s+");
    if (userChoice.isEmpty()) {
      return userAmenities;
    }

    // add chosen options to list
    for (String option : options) {
      try {
        // add to list if valid int
        userAmenities.add(amenities.get(Integer.parseInt(option)));
      } catch (IndexOutOfBoundsException invalid) {
        System.out.println("Option " + option + " is invalid and therefore has been ignored.");
      }
    }
    return userAmenities;
  }

  /**
   * Returns true if the address is not in the database
   */
  private static boolean newAddress(String unit, Double x, Double y) {
    return !(DatabaseSelector.getAddress(unit, x, y));
  }

  private static void viewListing(Scanner userInput, User user, Listing listing)
      throws EmptyFormException {
    // get listing
    int userChoice;

    // loop till exit
    do {
      // view -> remove, edit/add availability
      System.out.print(" 1. Remove Listing\n" + " 2. View Availability\n" + "3. View Comments\n"
          + "4. View Bookings\n" + " 0. Go Back \n");
      System.out.print("Enter the option number: ");
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        userChoice = -1;
      }

      // remove listing, view availability, view comments, go back
      switch (userChoice) {
        case 1:
          // remove Listing
          if (removeListingForm(userInput, user)) {
            userChoice = 0;
          }
          break;
        case 2:
          // view availability -> add/delete/edit->price/start/end
          viewListingAvailabilities(userInput, user, listing);
          break;
        case 3:
          // view comments
          viewListingComments(userInput, user, listing);
          break;
        case 4:
          // view bookings -> cancel/(comment on user)
          viewListingBookings(userInput, user, listing);
          break;
        case 0:
          System.out.println("Going Back... ");
          break;
        default:
          System.out.println(">>> Command not recognized. Please try again. >>>");
          break;
      }
    } while (userChoice != 0);
  }

  private static void viewListingAvailabilities(Scanner userInput, User user, Listing listing) {
    List<Availability> listingAvailabilities;
    int userChoice;
    int i = 0;
    // loop till exit
    do {
      i = 0;
      listingAvailabilities = listing.getAvailabilities();
      System.out.println(">>>");
      for (Availability availability : listingAvailabilities) {
        i++;
        System.out.println(i + ". " + availability.toString());
        System.out.println("------------------------------------------------");
      }
      System.out.println(">>>");

      // view availability -> add/delete/edit->price/start/end,
      System.out
          .print(" 1. Add Availability\n" + " 2. Edit/Delete Availability\n" + " 0. Go Back \n");
      System.out.print("Enter the option number: ");
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        userChoice = -1;
      }

      // add, delete/(edit->price/startDate/endDate), go back
      switch (userChoice) {
        case 1:
          System.out.println("To Add Availability... ");
          try {
            addAvailabilityForm(userInput, listing);
          } catch (InvalidFormException e) {
            System.out.println("Invalid input. Going back.");
          }
          break;
        case 2:
          // pick availability to modify or delete
          userChoice = modifyAvailabilityForm(userInput, listing);
          break;
        case 0:
          System.out.println("Going Back... ");
          break;
        default:
          System.out.println(">>> Command not recognized. Please try again. >>>");
          break;
      }
    } while (userChoice != 0);
  }

  private static void addAvailabilityForm(Scanner userInput, Listing listing)
      throws InvalidFormException {

    int listingUseNum = 0;
    String listingUse;
    String start = "";
    String end = "";
    Double price = 0.0;

    long p;
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();

    // get coordinates
    System.out.println("Please input the following or nothing to exit:");
    System.out.print(" Allowed use for renter 1. 'Full', 2. 'Private Room', 3. 'Shared' : ");
    try {
      listingUseNum = Integer.parseInt(userInput.nextLine());
      switch (listingUseNum) {
        case 1:
          listingUse = "Full";
          break;
        case 2:
          listingUse = "Private Room";
          break;
        case 3:
          listingUse = "Shared";
          break;
        default:
          throw new InvalidFormException();
      }
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new InvalidFormException();
    }

    System.out.print(" Start Date as 'YYYY-MM-DD' : ");
    try {
      start = userInput.nextLine();
      startDate = LocalDate.of(Integer.parseInt(start.substring(0, 4)),
          Integer.parseInt(start.substring(5, 7)), Integer.parseInt(start.substring(8)));
      p = ChronoUnit.DAYS.between(LocalDate.now(), startDate);
      if (p <= 0) {
        System.out.print("Needs to be a future date.");
        throw new InvalidFormException();
      }
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new InvalidFormException();
    } catch (IndexOutOfBoundsException invalid) {
      System.out.print("Invalid format: 'YYYY-MM-DD'");
    }

    System.out.print(" End Date as 'YYYY-MM-DD' : ");
    try {
      end = userInput.nextLine();
      endDate = LocalDate.of(Integer.parseInt(end.substring(0, 4)),
          Integer.parseInt(end.substring(5, 7)), Integer.parseInt(end.substring(8)));
      p = ChronoUnit.DAYS.between(startDate, endDate);
      if (p <= 0) {
        System.out.print("Needs to be a date after the start date.");
        throw new InvalidFormException();
      }
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new InvalidFormException();
    } catch (IndexOutOfBoundsException invalid) {
      System.out.print("Invalid format: 'YYYY-MM-DD'");
    }

    // Double price;
    System.out.print(" Price: (Suggested price: " + listing.getSuggestedPrice() + ")");
    try {
      price = Double.parseDouble(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new InvalidFormException();
    }

    listing.addListingAvailability(listingUse, start, end, price, true);
  }

  private static int modifyAvailabilityForm(Scanner userInput, Listing listing) {
    int out = 2;
    int availabilityNum = 0;
    int userChoice = 0;
    System.out
        .print("Input the number of the availability you would like to modify or 0 to go back: ");
    try {
      availabilityNum = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      availabilityNum = -1;
    }

    if (availabilityNum > 0 && availabilityNum <= listing.getNumAvailability()) {
      System.out
          .print(" 1. Delete Availability \n 2. Edit price or avaliable dates \n 0. Go Back ");
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        userChoice = -1;
      }
      if (userChoice == 1) { // delete
        listing.deleteAvailabilityNum(availabilityNum - 1);
        System.out.println("Availability Deleted.");
        out = 0;
      } else if (userChoice == 2) { // edit->price/start/end
        try {
          viewEditAvailability(userInput, listing,
              listing.getAvailabilities().get(availabilityNum - 1));
        } catch (InvalidFormException e) {
        }
      } else if (userChoice == 0) { // go back
        System.out.println("Going back...");
      } else {
        System.out.println("Invalid Input.");
      }
    } else if (availabilityNum != 0) {
      System.out.println("Invalid Input.");
    }
    return out;
  }

  private static void viewEditAvailability(Scanner userInput, Listing listing,
      Availability availability) throws InvalidFormException {
    int userChoice;
    Double newPrice = 0.0;
    LocalDate startDate = null;
    String newStart = "";
    LocalDate endDate;
    String newEnd = "";
    long p;
    do {
      System.out.print(" 1. Edit price \n 2. Edit Dates \n 0. Go Back ");
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        userChoice = -1;
      }
      switch (userChoice) {
        case 1:
          // get new price
          System.out.print(" Enter new price or nothing to cancel.");
          try {
            newPrice = Double.parseDouble(userInput.nextLine());
          } catch (NoSuchElementException | NumberFormatException invalid) {

          }
          // insert price if valid
          if (newPrice > 0.0) {
            availability.updatePrice(newPrice);
          }

          break;
        case 2:
          // get new start date
          System.out
              .print(" Enter new start date in the format 'YYYY-MM-DD' or nothing to cancel.");
          try {
            newStart = userInput.nextLine();
            startDate = LocalDate.of(Integer.parseInt(newStart.substring(0, 4)),
                Integer.parseInt(newStart.substring(5, 7)),
                Integer.parseInt(newStart.substring(8)));
          } catch (NoSuchElementException | NumberFormatException invalid) {
            continue;
          } catch (IndexOutOfBoundsException invalid) {
            System.out.print("Invalid format: 'YYYY-MM-DD'. Try again.");
            continue;
          }

          p = ChronoUnit.DAYS.between(LocalDate.now(), startDate);
          // check
          if (p <= 0) {
            System.out.print("Start date has to be after current date. Try again.");
            continue;
          }

          // get new end date
          System.out.print(" Enter new end date in the format 'YYYY-MM-DD' or nothing to cancel.");
          try {
            newEnd = userInput.nextLine();
            endDate = LocalDate.of(Integer.parseInt(newEnd.substring(0, 4)),
                Integer.parseInt(newEnd.substring(5, 7)), Integer.parseInt(newEnd.substring(8)));
            p = ChronoUnit.DAYS.between(startDate, endDate);
          } catch (NoSuchElementException | NumberFormatException invalid) {
            continue;
          } catch (IndexOutOfBoundsException invalid) {
            System.out.print("Invalid format: 'YYYY-MM-DD'. Try again.");
            continue;
          }
          // check
          if (p > 0) {
            availability.updateDates(newStart, newEnd);
          } else {
            System.out.print("End date has to be after start date. Try again.");

          }
          break;
        case 0:
          System.out.println("Going Back... ");
          break;
        default:
          System.out.println(">>> Command not recognized. Please try again. >>>");
          break;
      }
    } while (userChoice != 0);

  }

  private static void viewListingBookings(Scanner userInput, User user, Listing listing) {
    // get user's bookings
    List<Booking> listingBookings;
    int userChoice;
    int bookingListNum;
    int i;
    // loop till exit
    do {
      i = 0;
      bookingListNum = -1;
      userChoice = -1;
      listingBookings = listing.getBookings();
      System.out.println(">>>");
      for (Booking booking : listingBookings) {
        i++;
        System.out.println(i + ". " + booking.toString());
        System.out.println("------------------------------------------------");
      }
      System.out.println(">>>");

      System.out.print(
          // cancel booking
          " 1. Cancel a Booking\n"
              // comment user
              + " 2. Comment on the renter\n"
              // Back
              + " 0. Go Back \n");
      System.out.print("Enter the option number: ");

      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        System.out.println("Invalid Input.");
        continue;
      }
      // cancel, comment, back
      switch (userChoice) {
        case 1:
          System.out.print("Choose by option number which booking to cancel: ");
          try {
            bookingListNum = Integer.parseInt(userInput.nextLine());
          } catch (NoSuchElementException | NumberFormatException invalid) {
            System.out.println("Invalid Input.");
            bookingListNum = 0;
            userChoice = -1;
          }

          if (userChoice > 0 && userChoice <= listing.getNumBookings()) {
            listing.cancelBooking(listing.getBookings().get(bookingListNum - 1).getBookingId());
            System.out.println("Booking Canceled.");
          } else {
            System.out.println(">>> Command not recognized. Please try again. >>>");
          }
          break;
        case 2: // user comment
          System.out.print(
              "Choose by option number which booking's renter you would like to comment on. Note that the renter has to have rented the place in the last month: ");
          try {
            bookingListNum = Integer.parseInt(userInput.nextLine());
          } catch (NoSuchElementException | NumberFormatException invalid) {
            System.out.println("Invalid Input.");
            bookingListNum = 0;
            userChoice = -1;
          }
          // check if valid number
          if (userChoice > 0 && userChoice <= listing.getNumBookings()) {
            // check if end date before current date
            Booking tempBooking = listing.getBookings().get(bookingListNum - 1);
            String end = tempBooking.getEndDate();
            LocalDate endDate = LocalDate.of(Integer.parseInt(end.substring(0, 4)),
                Integer.parseInt(end.substring(5, 7)), Integer.parseInt(end.substring(8)));
            long p = ChronoUnit.DAYS.between(endDate, LocalDate.now());

            if (p >= 0 && p <= 31) {
              // if so, make comment
              // get id of user to make comment on
              int renterId = tempBooking.getRenterId();
              try {
                user.addCommentForm(userInput, user, tempBooking.getBookingId(), renterId);
              } catch (InvalidFormException e) {
                System.out.println("Invalid Input. Try Again.");
              }
            } else {
              System.out.println(
                  "Cannot comment on renter as the booking is in the future or too far in the past.");
            }
          } else {
            System.out.println(">>> Command not recognized. Please try again. >>>");
          }
          break;
        case 0:
          System.out.println("Going Back... ");
          break;
        default:
          System.out.println(">>> Command not recognized. Please try again. >>>");
          break;
      }
    } while (userChoice != 0);

  }

  private static void viewListingComments(Scanner userInput, User user, Listing listing) {
    List<ListingComment> listingComments = listing.getComments();
    System.out.println(">>>");
    for (ListingComment ll : listingComments) {
      System.out.println(ll.toString());
      System.out.println("------------------------------------------------");
    }
    System.out.println(">>>");
  }

  /**
   * Returns true if deleted
   */
  private static boolean removeListingForm(Scanner userInput, User user) {
    // Get input
    boolean del = false;
    System.out.print("Input the number of the listing you would like to remove or 0 to go back: ");
    int listingNum = 0;
    try {
      listingNum = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      listingNum = -1;
    }

    if (listingNum == 0) {
      del = false;
    } else if (listingNum > 0 && listingNum <= user.getNumListings()) {
      // "The host however can cancel a booking any time and similarly the renter can cancel as
      // well."
      user.deleteListing(listingNum - 1);
      System.out.println("Listing Deleted.");
      del = true;
    } else {
      System.out.println("Invalid Input.");
      del = false;
    }
    return del;
  }

}
