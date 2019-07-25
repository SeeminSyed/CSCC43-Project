package pages;

import java.time.LocalDate;
import java.time.Period;
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
          // addPropertyForm(userInput, user);
          break;
        case 4:
          System.out.println("Showing New Property Form... ");
           addPropertyForm(userInput, user);
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


  /**
   * 
   * 
   * @param userInput
   * @param user
   */
  private static void addPropertyForm(Scanner userInput, User user) {
//    try {
//      System.out.println("Please input your payment details:");
//
//      // Get input
//      System.out.print(" Card Number: ");
//      int card_num = 0;
//      try {
//        card_num = Integer.parseInt(userInput.nextLine());
//      } catch (NoSuchElementException | NumberFormatException invalid) {
//        throw new EmptyFormException();
//      }
//
//      System.out.println(card_num);
//
//      System.out.println(
//          " Card Type (Input the chosen number): 1.'VISA', 2.'Mastercard', 3.'American Express', 4.'Discover' ");
//      String card_type = userInput.nextLine();
//      if (card_type.isEmpty()) {
//        throw new EmptyFormException();
//      } else {
//        switch (card_type) {
//          case "1":
//            System.out.println(" Card Type 1.'VISA' chosen.");
//            card_type = "VISA";
//            break;
//          case "2":
//            System.out.println(" Card Type 2.'Mastercard' chosen.");
//            card_type = "Mastercard";
//            break;
//          case "3":
//            System.out.println(" Card Type 3.'American Express' chosen.");
//            card_type = "American Express";
//            break;
//          case "4":
//            System.out.println(" Card Type 4.'Discover' chosen.");
//            card_type = "Discover";
//            break;
//          default:
//            throw new EmptyFormException();
//        }
//      }
//      System.out.println(card_type);
//      System.out.print(" Expiry Date as 'YYYY-MM': ");
//      String exp_date = userInput.nextLine();
//      if (exp_date.isEmpty()) {
//        throw new EmptyFormException();
//      }
//      exp_date += "-01";
//      System.out.println(exp_date);
//
//      Period period =
//          Period.between(LocalDate.now(), LocalDate.of(Integer.parseInt(exp_date.substring(0, 4)),
//              Integer.parseInt(exp_date.substring(5, 7)), 1));
//      if (period.getYears() < 0 || (period.getYears() == 0 && period.getMonths() <= 0)) {
//        throw new InvalidFormException();
//      }
//
//      // if card already in user list, don't add
//      if (checkUserHasCard(user, card_num)) {
//        System.out.println("Card already in list.");
//      } else {
//        user.addCard(card_num, card_type, exp_date);
//        System.out.println("Card added.");
//      }
//    } catch (EmptyFormException | InvalidFormException invalid) {
//      System.out.println("Invalid input. Going back.");
//    }
  }



  /**
   * 
   * Form to delete user account
   * 
   * @param userInput
   * @param user
   * @return
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
      // TODO Auto-generated catch block
      System.out.println("Incorrect credentials. Going back to the home page.");
      return 6;
    }
  }

}
