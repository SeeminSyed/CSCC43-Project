package pages;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.NoSuchElementException;
import java.util.Scanner;
import database.*;

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
      System.out.print(" 1. Sign In \n 2. Sign Up \n 0. Exit \n");
      System.out.print("Enter the option number: ");
      // get input
      userInput = new Scanner(System.in);
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NumberFormatException number) {
        userChoice = -1;
      }

      // sign up, sign in or exit
      switch (userChoice) {
        case 1:
          System.out.println("Signing In... ");
          signIn(userInput);
          break;
        case 2:
          System.out.println("Signing Up... ");
          signUp(userInput);
          break;
        case 0:
          System.out.println("Bye");
          break;
        default:
          System.out.println(">>>\nCommand not recognized. Please try again.\n>>>");
          break;
      }
    } while (userChoice != 0);
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
      if (sin == 0) {
        throw new EmptyFormException();
      }

      System.out.print(" Name: ");
      name = userInput.nextLine();
      if (name.isEmpty()) {
        throw new EmptyFormException();
      }

      System.out.print(" Email: ");
      email = userInput.nextLine();
      if (email.isEmpty()) {
        throw new EmptyFormException();
      }
      System.out.print(" Password: ");
      password = userInput.nextLine();
      if (password.isEmpty()) {
        throw new EmptyFormException();
      }

      System.out.print(
          " Date of birth as 'YYYY-MM-DD' (You must be at least 18 years of age to access this service): ");
      dob = userInput.nextLine();
      // Check >18yo
      Period period =
          Period.between(
              LocalDate.of(Integer.parseInt(dob.substring(0, 4)),
                  Integer.parseInt(dob.substring(5, 7)), Integer.parseInt(dob.substring(8))),
              LocalDate.now());
      if (period.getYears() < 18) {
        throw new InvalidFormException();
      }

      System.out.print(" Occupation: ");
      occupation = userInput.nextLine();
      if (occupation.isEmpty()) {
        throw new EmptyFormException();
      }
      System.out.print(" Phone number as numbers only: ");
      phoneNum = Integer.parseInt(userInput.nextLine());
      if (phoneNum == 0) {
        throw new EmptyFormException();
      }

      // insert info to database and if row number returned, then valid
      if (DatabaseInserter.insertUser(sin, name, email, password, dob, occupation, phoneNum) > 0) {
        System.out.println("You've signed up!");
      } else {
        System.out.println(
            ">>> \nYour account was not created due to invalid input. Please try again.\n>>>");
      }
    } catch (StringIndexOutOfBoundsException | DateTimeException dob_format_error) {
      System.out.println(
          ">>>\nYour account was not created as your date of birth was entered incorrectly.\n>>>");
    } catch (InvalidFormException eighteen) {
      System.out.println(
          ">>>\nYour account was not created as you must be at least 18 years of age.\n>>>");
    } catch (NumberFormatException number) {
      System.out.println(">>>\nThis is a numbers-only field. Please try again.\n>>>");
    } catch (EmptyFormException empty) {
      System.out.println(">>>\nYour account was not created as you left a field empty.\n>>>");
    }
  }

  /*
   * Tries to sign in user and creates user object if valid user.
   */
  private static void signIn(Scanner userInput) {
    System.out.println("Please sign in using your email and password.");
    // Validate
    int user_id = 0;

    try {
      // Get input
      System.out.print(" Email: ");
      String email = userInput.nextLine();
      if (email.isEmpty()) {
        throw new InvalidFormException();
      }
      System.out.print(" Password: ");
      String password = userInput.nextLine();
      if (password.isEmpty()) {
        throw new InvalidFormException();
      }

      // Get user_id from database and if user_id returned, then logged in
      if ((user_id = DatabaseSelector.getSIN(email, password)) > 0) {
        System.out.println(">>>\nYou've signed in successfully!\n>>>");
        UserHome.main(userInput, user_id, email, password);
        System.out.println(">>>\nYou've signed out successfully!\n>>>");
      } else {
        throw new InvalidFormException();
      }
    } catch (InvalidFormException | NoSuchElementException invalid) {
      System.out.println(">>>\nEmail or Password is incorrect. Please try again.\n>>>");
    }
  }

}
