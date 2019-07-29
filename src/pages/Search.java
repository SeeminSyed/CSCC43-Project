package pages;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import database.DatabaseSelector;
import exceptions.EmptyFormException;
import exceptions.InvalidFormException;
import models.Availability;
import models.Listing;
import models.User;

public class Search {

  public static void main(Scanner userInput, User user) {// when user books, check availability,
                                                         // modify availability based on timing
    int userChoice = 1;
    do {
      // (give input or nothing to skip that filter option)
      try {
        System.out.print(" 1. Search, 0, go back: ");
        try {
          userChoice = Integer.parseInt(userInput.nextLine());
        } catch (NoSuchElementException | NumberFormatException invalid) {
          userChoice = 0;
        }
        if (userChoice > 0) {
          min(userInput, user);
        }
      } catch (EmptyFormException e) {
      }
    } while (userChoice != 0);

  }

  private static void min(Scanner userInput, User user) throws EmptyFormException {
    System.out.println("Search by coordinates:");
    Double x = 0.0;
    Double y = 0.0;
    Double dist = 1000.0;
    int order = 0;
    // get search options and validate
    // latitude
    // Get input
    System.out.print(" Latitude: ");
    try {
      x = Double.parseDouble(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }

    // longitude
    System.out.print(" Latitude: ");

    try {
      y = Double.parseDouble(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }
    // distance
    System.out.print(" Radius (default 1000 km): ");
    try {
      dist = Double.parseDouble(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      dist = 25.0;
    }
    // rank by distance, price asc or price desc
    // distance
    // System.out.print(" Order by: 1. Distance ASC, 2. Price ASC, 3. Price DESC");
    // try {
    // order = Integer.parseInt(userInput.nextLine());
    // if (order > 3 | order < 1)
    // order = 0;
    // } catch (NoSuchElementException | NumberFormatException invalid) {
    // order = 0;
    // }

    // get listings from database
    List<String> print = new ArrayList<>();
    print = DatabaseSelector.getAllListingsByCoordinate(x, y, dist);

    // TODO sort


    // print output
    for (String line : print) {
      System.out.println(line);
    }

    int userChoice = -1;
    do {
      // book, go back
      System.out.print(" To book a listing, type in the listing id or 0 to go back");
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        System.out.println(" Invalid command, try again.");
      }

      if (userChoice > 0) {
        if (user.getCards().size() == 0) {
          System.out
              .println("You need to have at least 1 card added to your account to book a listing.");
        } else {

          addBooking(userInput, user, userChoice);
        }
      }

    } while (userChoice != 0);
  }

  private static void addBooking(Scanner userInput, User user, int userChoice) {
    Listing listing = DatabaseSelector.getListing(userChoice);
    System.out.println(listing.toString());
    int i = 0;
    // show availabilites
    for (Availability a : listing.getAvailabilities()) {
      i++;
      System.out.println(i + ". " + a.toString());
    }

    do {
      // book, go back
      System.out.print(" Type in the option number or 0 to go back");
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        System.out.println(" Invalid command, try again.");
      }

      if (userChoice > 0) {
        Availability b = listing.getAvailabilities().get(userChoice - 1);

        user.addBooking(b.getStartDate(), b.getEndDate(), listing.getListingId(), user.getSin(),
            user.getCards().get(1).getCardNum(), b.getPrice());
      }

    } while (userChoice != 0);
  }


  //
  // this.startDate = startDate;
  // this.endDate = endDate;
  // cardNum;
  //
  // listingId;
  // renterId;
  // cost;
  // getDates(userInput);



}
