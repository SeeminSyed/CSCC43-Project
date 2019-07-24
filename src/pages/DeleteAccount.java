package pages;

import java.util.Scanner;
import models.*;

public class DeleteAccount {

  public static int main(Scanner userInput, User user) {
    try {
      System.out.println("Please input your email and password to delete your account.");

      // Get input
      System.out.print(" Email: ");
      String email = userInput.nextLine();
      System.out.print(" Password: ");
      String password = userInput.nextLine();

      System.out.println(email + " " +(user.getEmail()));
      System.out.println(password+" "+(user.getPassword()));
      
      // if correct, delete user and return 0
      if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
        user.deleteUser();
        System.out.println("Account deleted.");
        return 0;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      System.out.println("Incorrect credentials. Going back to the home page.");
    }      
    System.out.println("Incorrect credentials. Going back to the home page.");
    return 6;
  }

}

