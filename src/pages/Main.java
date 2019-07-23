package pages;

import java.util.Scanner;
import database.DatabaseInserter;
import database.DatabaseSelector;

public class Main {

  /*
   * Home Page
   */
  public static void main(String[] args) {
    int userChoice;
    Scanner userInput;
    // loop till exit
    do {
      System.out.println("Welcome to MyBnB!");
      System.out.print(" 1. Sign Up \n 2. Sign In \n 3. Exit \n");
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
          System.out.println("Signing Up... ");
          signUp(userInput);
          break;
        case 2:
          System.out.println("Signing In... ");
          signIn(userInput);
          break;
        case 3:
          System.out.println("Bye");
          break;
        default:
          System.out.println(">>>\nCommand not recognized. Please try again.\n>>>");
          break;
      }
    } while (userChoice != 3);
    userInput.close();
  }

  /*
   * Create a new user account.
   */
  private static void signUp(Scanner userInput) {
    // get and validate info
    System.out.println("Welcome. Please add your information. All fields are required.");
    int sin;
    String name;
    String email;
    String password;

    String dob;
    String occupation;
    int phoneNum;

    try {
      // get info
      System.out.print(" Your Security Insurance Number: ");
      sin = Integer.parseInt(userInput.nextLine());

      System.out.print(" Name: ");
      name = userInput.nextLine();

      System.out.print(" Email: ");
      email = userInput.nextLine();
      System.out.print(" Password: ");
      password = userInput.nextLine();

      System.out.print(" Date of birth as 'YYYY-MM-DD': ");
      dob = userInput.nextLine();
      System.out.print(" Occupation: ");
      occupation = userInput.nextLine();
      System.out.print(" Phone number as numbers only: ");
      phoneNum = Integer.parseInt(userInput.nextLine());

      // insert info to database and if row number returned, then valid
      if (DatabaseInserter.insertUser(sin, name, email, password, dob, occupation, phoneNum) > 0) {
        System.out.println("You've signed up!");
      } else {
        System.out.println(">>> \nnYour account was not created due to invalid input. Please try again.\n>>>");
      }

    } catch (Exception e) {
      System.out.println(">>> \nYour account was not created due to invalid input. Please try again.\n>>>");
    }
  }

  /*
   * Tries to sign in user and creates user object if valid user.
   */
  private static void signIn(Scanner userInput) {
    System.out.println("Please sign in using your email and password.");

    // Get input
    System.out.print(" Email: ");
    String email = userInput.nextLine();
    System.out.print(" Password: ");
    String password = userInput.nextLine();

    // Validate
    int user_id = 0;

    try {
      // Get user_id from database and if user_id returned, then logged in
      if ((user_id = DatabaseSelector.getSIN(email, password)) > 0) {
        System.out.println(">>>\nYou've signed in successfully!\n>>>");
        UserHome.main(userInput, user_id);
        System.out.println(">>>\nYou've signed out successfully!\n>>>");
      } else {
        System.out.println(">>>\nSign in failed. Please try again.\n>>>");
      }
    } catch (Exception e) {
      System.out.println(">>>\nSign in failed. Please try again.\n>>>");
    }
  }


}


//
// private static void address() {
// System.out.print("Address: ");
// String address = newUserInput.nextLine();
// System.out.print("Your credit card number: ");
// String creditCard = newUserInput.nextLine();
// System.out.print("The city you live in currently: ");
// String city = newUserInput.nextLine();
// System.out.print("The province you live in currently: ");
// String province = newUserInput.nextLine();
// System.out.print("The country you live in currently: ");
// String country = newUserInput.nextLine();
// System.out.print("The longitude you are currently located on: ");
// float longitude = newUserInput.nextFloat();
// System.out.print("The lattitude you are currently located on: ");
// float lattitude = newUserInput.nextFloat();
//
// }
