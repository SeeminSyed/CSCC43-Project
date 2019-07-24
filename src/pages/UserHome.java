package pages;

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


  private static void userOptions(Scanner userInput, User user) {
    System.out.println("Hello," + /* user.name + */ "!");
    int userChoice;

    // loop till exit
    do {
      System.out.print(
          // Will allow search listings, then book listing
          " 1. Book a Listing\n"
              // Will show list of bookings: can cancel/comment
              + " 2. Check Your Bookings\n"
              // Will show hosted properties: can edit/delete. Per property, check bookings: can
              // edit/cancel
              + " 3. Check Your Listed Properties\n"
              // Will add new property
              + " 4. Add a New Property Listing\n"
              // Will show list of credit cards: can add/delete
              + " 5. Check Payment Information\n"
              // Delete account and associated info
              + " 6. Delete Your Account\n"
              // Sign out
              + " 0. Sign Out \n");
      System.out.print("Enter the option number: ");
      // get input
      userInput = new Scanner(System.in);
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (Exception e) {
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
          // signIn(userInput);
          break;
        case 3:
          System.out.println("Showing Your Listed Properties... ");
          // signIn(userInput);
          break;
        case 4:
          System.out.println("Showing New Property Form... ");
          // signIn(userInput);
          break;
        case 5:
          System.out.println("Showing Credit Information... ");
          // signIn(userInput);
          break;
        case 6:
          System.out.println("Are you sure you would like to delete your account? ");
          // if successful, userChoice = 0
          userChoice = DeleteAccount.main(userInput, user);
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

}
