package pages;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import models.Listing;
import models.ListingAddress;
import models.User;

public class HostedListings {

  public static void main(Scanner userInput, User user) {
    // get user's listings
    List<Listing> userListings;
    int userChoice;
    int i = 0;
    // loop till exit
    do {
      i = 0;
      userListings = user.getListings();
      System.out.println(">>>");
      for (Listing listing : userListings) {
        i++;
        System.out.println(i + ". " + listing.toString());
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
            viewListing(userInput, user);
          } catch (EmptyFormException e) {
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
    String listing_type;
    int num_bedrooms;
    int num_beds;
    int num_bathrooms;
    String title;
    String description;

    // if not, get listing info
    System.out.println(
        " Listing Type (Input the chosen number): \n 1.'Apartment'\n 2.'House', 3.'Secondary Unit'\n 4.'Bed and Breakfast'\n 5. Boutique Hotel\n");
    listing_type = userInput.nextLine();
    if (listing_type.isEmpty()) {
      throw new EmptyFormException();
    } else {
      switch (listing_type) {
        case "1":
          System.out.println(" 1.'Apartment' chosen.");
          listing_type = "Apartment";
          break;
        case "2":
          System.out.println(" 2.'House' chosen.");
          listing_type = "House";
          break;
        case "3":
          System.out.println(" 3.'Secondary Unit' chosen.");
          listing_type = "Secondary Unit";
          break;
        case "4":
          System.out.println(" 4.'Bed and Breakfast' chosen.");
          listing_type = "Bed and Breakfast";
          break;
        case "5":
          System.out.println(" 4.'Boutique Hotel' chosen.");
          listing_type = "Boutique Hotel";
          break;
        default:
          throw new EmptyFormException();
      }
    }
    System.out.print(" Number of Bedrooms: ");
    try {
      num_bedrooms = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }
    System.out.print(" Number of Bed: ");
    try {
      num_beds = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }
    System.out.print(" Number of Bathrooms: ");
    try {
      num_bathrooms = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }

    System.out.print(" Title: ");
    title = userInput.nextLine();
    System.out.print(" Description: ");
    description = userInput.nextLine();

    // get amenities TODO
    // Set<String> amenities = new HashSet<>();

    // add listing to database

    if (user.addListing(listing_type, num_bedrooms, num_beds, num_bathrooms, title,
        description) == false) {
      return;
    } else {
      // add address to database
      Listing newListing = user.getListings().get(user.getNumListings() - 1);
      newListing.addListingAddress(unit, street, city, state, country, zipCode, x, y);
      // add amenities to database TODO
      // newListing.addAmenities(amenities);

    }
    // get availability

    // add to database
  }

  /**
   * Returns true if the address is not in the database
   */
  private static boolean newAddress(String unit, Double x, Double y) { // TODO
    // TODO Auto-generated method stub
    return false;
  }

  private static void viewListing(Scanner userInput, User user) throws EmptyFormException {
    // get listing
    Listing listing;
    int userChoice;

    // loop till exit
    do {
      // Get which listing
      System.out.print("Input the number of the Listing you would like to view or 0 to cancel: ");
      int listingNum = 0;
      try {
        listingNum = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        throw new EmptyFormException();
      }

      // parse option
      if (listingNum == 0) {
        return;
      } else if (listingNum > 0 && listingNum < user.getNumListings()) {
        listing = user.getListings().get(listingNum - 1);
        System.out.println(listing.toString());
      } else {
        System.out.println("Invalid Input.");
        return;
      }

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
        case 2:
          // view availability
          break;
        case 3:
          // view comments
          break;
        case 4:
          // view bookings
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

  private static void editListingAvailability(Scanner userInput, User user)
      throws EmptyFormException { // TODO

  }

  private static void editListingPrice(Scanner userInput, User user) throws EmptyFormException { // TODO

  }

  private static void editListingUse(Scanner userInput, User user) throws EmptyFormException { // TODO

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
