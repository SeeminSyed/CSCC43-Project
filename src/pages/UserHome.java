package pages;

import java.util.Scanner;

public class UserHome {

  /*
   * User Home Page
   */
  public static void main(Scanner userInput, int user_id) {
    // Create User object

    // Present user options
    userOptions();
  }

  
  private static void userOptions() {
    System.out.println("Hello," + /* user.name + */ "!");
//    int userChoice;
//
//    // loop till exit
//    do {
//      System.out.print(" 1. Sign Up \n 2. Sign In \n 3. Exit \n");
//      System.out.print("Enter the option number: ");
//      // get input
//      userInput = new Scanner(System.in);
//      try {
//        userChoice = Integer.parseInt(userInput.nextLine());
//      } catch (Exception e) {
//        userChoice = 0;
//      }
//
//      // sign up, sign in or exit
//      switch (userChoice) {
//        case 1:
//          System.out.println("Signing Up... ");
//          signUp(userInput);
//          break;
//        case 2:
//          System.out.println("Signing In... ");
//          signIn(userInput);
//          break;
//        case 3:
//          System.out.println("Bye");
//          break;
//        default:
//          System.out.println(">>>\nCommand not recognized. Please try again.\n>>>");
//          break;
//      }
//    } while (userChoice != 3);

  }

}
