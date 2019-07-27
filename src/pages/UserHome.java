package pages;

import java.util.NoSuchElementException;
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
    System.out.println("Hello" + /*", " + user.name + */ "!");
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
              // Will show list of credit cards: can add/delete
              + " 4. Check Payment Information\n"
              // Delete account and associated info
              + " 5. Delete Your Account\n"
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
          System.out.println("Showing Property Listings... ");
          // signUp(userInput);
          break;
        case 2:
          System.out.println("Showing Your Bookings... ");
          // addPropertyForm(userInput, user);
          break;
        case 3:
          System.out.println("Showing Your Listed Properties... ");
          HostedListings.main(userInput, user);
          break;
        case 4:
          System.out.println("Showing Credit Information... ");
          CreditInfo.main(userInput, user);
          break;
        case 5:
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
