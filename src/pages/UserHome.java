package pages;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import exceptions.*;
import java.util.Scanner;
import models.*;

public class UserHome {
  /*
   * User Home Page
   */
  public static void main(Scanner userInput, int user_id, String email, String password) {
    // Create User object
    User user = new User(user_id, email, password);
    // Present user options
    userOptions(userInput, user);
  }


  /**
   * 
   * Options
   * 
   * @param userInput
   * @param user
   */
  private static void userOptions(Scanner userInput, User user) {
    System.out.println("Hello" + ", " + user.getName() + "!");
    int userChoice;

    // loop till exit
    do {
      System.out.print(
          // Will allow search listings, then book listing
          " 1. Book a Listing\n"
              // Will show list of bookings: can cancel/comment
              + " 2. Check Your Bookings\n"
              // Will show hosted properties: can add/edit/delete. Per property, check bookings: can
              // edit/cancel
              + " 3. Check Your Listed Properties\n"
              // view comments
              + " 4. Check Your Comments\n"
              // Will show list of credit cards: can add/delete
              + " 5. Check Payment Information\n"
              // Delete account and associated info
              + " 6. Delete Your Account\n"
              // Sign out
              + " 0. Sign Out \n");
      System.out.print("Enter the option number: ");
      // get input
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        userChoice = 0;
      }

      // sign up, sign in or exit
      switch (userChoice) {
        case 1:
          System.out.println("Showing Property Listings Search... ");
          Search.main(userInput, user); // TODO Property Listings Search
          break;
        case 2:
          System.out.println("Showing Your Bookings... ");
          viewUserBookings(userInput, user);
          break;
        case 3:
          System.out.println("Showing Your Listed Properties... ");
          HostedListings.main(userInput, user);
          break;
        case 4:
          System.out.println("Showing Comments... ");
          viewUserComments(user);
          break;
        case 5:
          System.out.println("Showing Credit Information... ");
          CreditInfo.main(userInput, user);
          break;
        case 6:
          System.out.println("Are you sure you would like to delete your account? ");
          // if successful, userChoice = 0
          userChoice = deleteAccountForm(userInput, user);
          break;
        case 0:
          System.out.println("Signing Out... ");
          break;
        default:
          System.out.println(">>>\nCommand not recognized. Please try again.\n>>>");
          break;
      }
    } while (userChoice != 0);
  }

  private static void viewUserComments(User user) {
    List<Comment> userComments;
    userComments = user.getComments();
    System.out.println(">>>");
    for (Comment comment : userComments) {
      System.out.println(comment.toString());
      System.out.println("------------------------------------------------");
    }
    System.out.println(">>>");
  }


  private static void viewUserBookings(Scanner userInput, User user) {
    // get user's bookings
    List<Booking> userBookings;
    int userChoice;
    int bookingListNum;
    int i = 0;
    // loop till exit
    do {
      i = 0;
      userBookings = user.getBookings();
      System.out.println(">>>");
      for (Booking booking : userBookings) {
        i++;
        System.out.println(i + ". " + booking.toString());
        System.out.println("------------------------------------------------");
      }
      System.out.println(">>>");

      System.out.print(
          // cancel booking
          " 1. Cancel a Booking\n"
              // comment listing owner
              + " 2. Make comment on the Listing Owner\n"
              // comment listing
              + " 3. Make comment on the Listing\n"
              // Back
              + " 0. Go Back \n");
      System.out.print("Enter the option number: ");

      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        System.out.println("Invalid Input.");
        userChoice = -1;
        continue;
      }
      // cancel, comment user, comment listing, back
      switch (userChoice) {
        case 1:
          System.out.print("Choose by option number which booking to cancel: ");
          try {
            bookingListNum = Integer.parseInt(userInput.nextLine());
          } catch (NoSuchElementException | NumberFormatException invalid) {
            System.out.println("Invalid Input.");
            bookingListNum = 0;
            userChoice = 0;
          }
          if (userChoice > 0 && userChoice <= user.getNumBookings()) {
            user.cancelUserBooking(user.getBookings().get(bookingListNum - 1).getBookingId());
            System.out.println("Booking Deleted.");
          } else {
            System.out.println(">>> Command not recognized. Please try again. >>>");
          }
          break;
        case 2: // make user comment
          System.out.print(
              "Choose by option number which listing's host you would like to comment on. Note that you have to have rented the place in the last month: ");
          try {
            bookingListNum = Integer.parseInt(userInput.nextLine());
          } catch (NoSuchElementException | NumberFormatException invalid) {
            System.out.println("Invalid Input.");
            bookingListNum = 0;
            userChoice = -1;
          }
          // check if valid number
          if (userChoice > 0 && userChoice <= user.getNumBookings()) {
            // check if end date before current date
            Booking tempBooking = user.getBookings().get(bookingListNum - 1);
            String end = tempBooking.getEndDate();
            LocalDate endDate = LocalDate.of(Integer.parseInt(end.substring(0, 4)),
                Integer.parseInt(end.substring(5, 7)), Integer.parseInt(end.substring(8)));
            long p = ChronoUnit.DAYS.between(endDate, LocalDate.now());

            if (p >= 0 && p <= 31) {
              // if so, make comment
              // get id of user to make comment on
              int hostId = tempBooking.getUserId();
              try {
                user.addCommentForm(userInput, user, tempBooking.getBookingId(), hostId);
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
        case 3: // make listing comment
          System.out.print(
              "Choose by option number which listing you would like to comment on. Note that you have to have rented the place in the last month: ");
          try {
            bookingListNum = Integer.parseInt(userInput.nextLine());
          } catch (NoSuchElementException | NumberFormatException invalid) {
            System.out.println("Invalid Input.");
            bookingListNum = 0;
            userChoice = -1;
          }
          // check if valid number
          if (userChoice > 0 && userChoice <= user.getNumBookings()) {
            // check if end date before current date
            Booking tempBooking = user.getBookings().get(bookingListNum - 1);
            String end = tempBooking.getEndDate();
            LocalDate endDate = LocalDate.of(Integer.parseInt(end.substring(0, 4)),
                Integer.parseInt(end.substring(5, 7)), Integer.parseInt(end.substring(8)));
            long p = ChronoUnit.DAYS.between(endDate, LocalDate.now());

            if (p >= 0 && p <= 31) {
              // if so, make comment
              // get id of listing to make comment on
              int listingId = tempBooking.getListingId();
              try {
                user.addListingCommentForm(userInput, user, tempBooking.getBookingId(), listingId);
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



  /**
   * 
   * Form to delete user account. Returns 6 if account deleted
   * 
   */
  private static int deleteAccountForm(Scanner userInput, User user) {
    System.out.println("Please input your email and password to delete your account.");
    try {
      // Get input
      System.out.print(" Email: ");
      String email = userInput.nextLine();
      System.out.print(" Password: ");
      String password = userInput.nextLine();

      // if correct, delete user and return 0
      if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
        user.databaseDeleteUser();
        System.out.println("Account deleted.");
        return 0;
      }
      System.out.println("Incorrect credentials. Going back to the home page.");
      return 6;
    } catch (NoSuchElementException invalid) {
      System.out.println("Incorrect credentials. Going back to the home page.");
      return 6;
    }
  }

}
