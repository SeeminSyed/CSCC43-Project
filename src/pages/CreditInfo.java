package pages;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import models.CreditCard;
import models.User;

public class CreditInfo {

  /**
   * Credit info page
   * 
   * @param userInput
   * @param user
   */
  public static void main(Scanner userInput, User user) {
    // get user's credit cards
    List<CreditCard> userCards;
    int userChoice;
    int i = 0;
    // loop till exit
    do {
      i = 0;
      userCards = user.getCards();
      System.out.println(">>>");
      for (CreditCard card : userCards) {
        i++;
        System.out.println(i + ". " + card.toString());
        System.out.println("------------------------------------------------");
      }
      System.out.println(">>>");


      System.out.print(
          // add new card
          " 1. Add a card\n"
              // delete card
              + " 2. Remove a Card\n"
              // Back
              + " 0. Go Back \n");
      System.out.print("Enter the option number: ");
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        userChoice = 0;
      }

      // add, remove, back
      switch (userChoice) {
        case 1:
          System.out.println("To Add Card... ");
          try {
            addCardForm(userInput, user);
          } catch (EmptyFormException | InvalidFormException invalid) {
            System.out.println("Invalid input. Going back.");
          }
          break;
        case 2:
          // Get input
          try {
            removeCardForm(userInput, user);
          } catch (EmptyFormException invalid) {
            System.out.print("Invalid Input.");
          } catch (NumberFormatException number) {
            System.out.println(">>>\nThis is a numbers-only field. Please try again.\n>>>");
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
   * form to add a new card
   * 
   */
  private static void addCardForm(Scanner userInput, User user)
      throws EmptyFormException, InvalidFormException {
    System.out.println("Please input your payment details:");

    // Get input
    System.out.print(" Card Number: ");
    int cardNum = 0;
    try {
      cardNum = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }

    System.out.println(
        " Card Type (Input the chosen number): 1.'VISA', 2.'Mastercard', 3.'American Express', 4.'Discover' ");
    String cardType = userInput.nextLine();
    if (cardType.isEmpty()) {
      throw new EmptyFormException();
    } else {
      switch (cardType) {
        case "1":
          System.out.println(" Card Type 1.'VISA' chosen.");
          cardType = "VISA";
          break;
        case "2":
          System.out.println(" Card Type 2.'Mastercard' chosen.");
          cardType = "Mastercard";
          break;
        case "3":
          System.out.println(" Card Type 3.'American Express' chosen.");
          cardType = "American Express";
          break;
        case "4":
          System.out.println(" Card Type 4.'Discover' chosen.");
          cardType = "Discover";
          break;
        default:
          throw new EmptyFormException();
      }
    }
    System.out.print(" Expiry Date as 'YYYY-MM': ");
    String expDate = userInput.nextLine();
    if (expDate.isEmpty()) {
      throw new EmptyFormException();
    }
    expDate += "-01";

    Period period =
        Period.between(LocalDate.now(), LocalDate.of(Integer.parseInt(expDate.substring(0, 4)),
            Integer.parseInt(expDate.substring(5, 7)), 1));
    if (period.getYears() < 0 || (period.getYears() == 0 && period.getMonths() <= 0)) {
      throw new InvalidFormException();
    }

    // if card already in user list, don't add
    if (checkUserHasCard(user, cardNum)) {
      System.out.println("Card already in list.");
    } else {
      user.addCard(cardNum, cardType, expDate);
    }
  }

  /*
   * Returns true if user has card in list
   */
  private static boolean checkUserHasCard(User user, int cardNum) {
    for (CreditCard card : user.getCards()) {
      if (card.getCardNum() == cardNum) {
        return true;
      }
    }
    return false;
  }


  /**
   * Form to delete card info
   * 
   */
  private static void removeCardForm(Scanner userInput, User user) throws EmptyFormException {
    // Get input
    System.out.print("Input the number of the card you would like to remove or 0 to go back: ");
    int cardNum = 0;
    try {
      cardNum = Integer.parseInt(userInput.nextLine());
    } catch (NoSuchElementException | NumberFormatException invalid) {
      throw new EmptyFormException();
    }

    if (cardNum == 0) {
      return;
    } else if (cardNum > 0 && cardNum <= user.getNumCards()) {
      user.deleteCard(cardNum - 1);
      System.out.println("Card Deleted.");
    } else {
      System.out.println("Invalid Input.");
    }

  }


}
