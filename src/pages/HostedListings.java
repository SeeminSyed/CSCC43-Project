package pages;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import database.DatabaseSelector;
import models.Listing;
import models.ListingComment;
import models.User;

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
        userChoice = 0;
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


  private static void addListingForm(Scanner userInput, User user)
      throws EmptyFormException, NoSuchElementException {
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

    // get availability TODO
    // System.out.println(
    // "Add availability for the listing. (The listing cannot be booked until you provide its
    // availability)");
    // System.out.print(" Option: ");
    // userChoice = userInput.nextLine();
    // if (userChoice.contentEquals("1")) {
    // chosenAmenities = viewAddAmenities(user, userInput);
    // }
    // add availability
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
  private static boolean newAddress(String unit, Double x, Double y) { // TODO
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
        userChoice = 0;
      }

      // remove listing, view availability, view comments, go back
      switch (userChoice) {
        case 1:
          // remove Listing
          if (removeListingForm(userInput, user)) {
            userChoice = 0;
          }
          break;
        case 2: // TODOS
          // view availability -> add/delete/edit->price/start/end,
          // viewListingAvailabilities(userInput, user, listing);
          break;
        case 3:
          // view comments
          viewListingComments(userInput, user, listing);
          break;
        case 4:
          // view bookings -> cancel
          // viewListingBookings(userInput, user, listing);
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
  private static boolean removeListingForm(Scanner userInput, User user) throws EmptyFormException {
    // Get input
    boolean del = false;
    System.out.print("Input the number of the Listing you would like to remove or 0 to go back: ");
    int listingNum = 0;
    try {
      listingNum = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }

    if (listingNum == 0) {
      del = false;
    } else if (listingNum > 0 && listingNum <= user.getNumListings()) {
      // if valid bookings then can't delete
      if (validBookingsOnListing(listingNum - 1)) {
        System.out.println("Listing Cannot be deleted as someone is currently renting the place.");
        del = false;
      } else {
        user.deleteListing(listingNum - 1);
        System.out.println("Listing Deleted.");
        del = true;

      }
    } else {
      System.out.println("Invalid Input.");
      del = false;
    }
    return del;
  }

  /**
   * returns true if there are active bookings on the listing
   * 
   */
  private static boolean validBookingsOnListing(int listingListNum) { // TODO

    return false;
  }

}
