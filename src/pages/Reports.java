package pages;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import database.DatabaseSelector;
import exceptions.InvalidFormException;

public class Reports {

  private final static List<String> blockedWords = Arrays.asList(new String[] {"be", "have", "do",
      "say", "get", "make", "go", "know", "take", "see", "come", "think", "look", "want", "give",
      "use", "find", "tell", "ask", "work", "seem", "feel", "try", "leave", "call", "to", "of",
      "in", "for", "on", "with", "at", "by", "from", "up", "about", "into", "over", "after", "the",
      "be", "to", "of", "and", "a", "in", "that", "have", "I", "it", "for", "not", "on", "with",
      "he", "as", "you", "do", "at", "this", "but", "his", "by", "from", "they", "we", "say", "her",
      "she", "or", "will", "an", "my", "one", "all", "would", "there", "their", "what", "so", "up",
      "out", "if", "about", "who", "get", "which", "go", "when", "me", "can", "like", "time", "no",
      "just", "him", "know", "take", "into", "your", "them", "see", "other", "than", "then", "now",
      "look", "only", "its", "over", "also", "back", "after", "use", "two", "how", "our", "first",
      "well", "way", "even", "new", "want", "because", "any", "these", "most", "us"});


  public static void main(Scanner userInput) {
    System.out.println("Reports:");
    int userChoice;

    // loop till exit
    do {
      System.out.print(" 1. Get total number of bookings in a specific date range by city: \n"
          + " 2. Get total number of bookings in a specific date range by zip code within a city:\n"

          + " 3. Get total number of listings per country\n"
          + " 4. Get total number of listings per country and city:\n"
          + " 5. Get total number of listings per country, city and postal code:\n"

          + " 6. Rank hosts by the total number of listings they have per country:\n"
          + " 7. Rank hosts by the total number of listings they have per city:\n"

          + " 8. Get hosts that have a number of listings greater than 10% of the total number of listings in a country:\n"
          + " 9. Get hosts that have a number of listings greater than 10% of the total number of listings in a city:\n"

          + " 10. Rank renters by number of bookings in a specific time period:\n"
          + " 11. Rank renters by number of bookings in a specific time period in a city:\n"

          + " 12. Get hosts with the largest number of cancellations within a year:\n"
          + " 13. Get renters with the largest number of cancellations within a year:\n"

          + " 14. Get set of most popular noun phrases associated with every listing:\n"

          + " 0. Go Back \n");

      System.out.print("Enter the option number: ");
      // get input
      try {
        userChoice = Integer.parseInt(userInput.nextLine());
      } catch (NoSuchElementException | NumberFormatException invalid) {
        userChoice = -1;
      }

      parseReportChoice(userInput, userChoice);

    } while (userChoice != 0);
  }

  private static int parseReportChoice(Scanner userInput, int userChoice) {
    switch (userChoice) {
      case 1:
        // total number of bookings in a specific date range by city
        try {
          printBookingsCountInPeriod(userInput, 1);
        } catch (InvalidFormException e) {
          System.out.println("Try Again.");
        }
        break;
      case 2:
        // total number of bookings in a specific date range by zip code, city
        try {
          printBookingsCountInPeriod(userInput, 2);
        } catch (InvalidFormException e) {
          System.out.println("Try Again.");
        }
        break;
      case 3:
        // get num listings per country
        printListingsCountByCountryCityPostal(1);
        break;
      case 4:
        // get num listings per country, city
        printListingsCountByCountryCityPostal(2);
        break;
      case 5:
        // get num listings per country, city, zipcode
        printListingsCountByCountryCityPostal(3);
        break;
      case 6:
        // rank the hosts by the total number of listings they have per country
        printHostsByListingCountCountryCity(1);
        break;
      case 7:
        // rank the hosts by the total number of listings they have by city
        printHostsByListingCountCountryCity(2);
        break;
      case 8:
        // hosts that have a #listings > 10% of the total #listings in a country
        printCommercialHostsByCountryCity(1);
        break;
      case 9:
        // hosts that have a #listings > 10% of the total #listings in a city
        printCommercialHostsByCountryCity(2);
        break;
      case 10:
        // rank renters by number of bookings in a specific time period
        try {
          printRentersBookingsInPeriod(userInput, 1);
        } catch (InvalidFormException e) {
          System.out.println("Try Again.");
        }
        break;
      case 11:
        // rank renters by number of bookings in a specific time period in a city
        try {
          printRentersBookingsInPeriod(userInput, 2);
        } catch (InvalidFormException e) {
          System.out.println("Try Again.");
        }
        break;
      case 12:
        // hosts with largest number of cancellations within a year
        printCancellations(1);
        break;
      case 13:
        // renters with largest number of cancellations within a year
        printCancellations(2);
        break;
      case 14:
        // most popular noun phrases associated with every listing
        printWordClouds();
        break;
      case 0:
        System.out.println("Going Back... ");
        break;
      default:
        System.out.println(">>>\nCommand not recognized. Please try again.\n>>>");
        break;
    }
    return userChoice;
  }

  private static void printWordClouds() {
    // get comments and listing_ids
    HashMap<Integer, List<String>> print = new HashMap<>();
    print = DatabaseSelector.getAllListingComments();


    // print id, and most popular words in order of popularity
    System.out.println("---");
    for (int key : print.keySet()) {
      // get most popular words
      System.out.println("Listing:" + key + " with words: " + getWordCloud(print.get(key)));
      System.out.println("---");
    }

  }

  private static String getWordCloud(List<String> comments) {
    String print = "";
    HashMap<String, Integer> aggregate = new HashMap<>();

    // for every comment
    for (String comment : comments) {
      // split the comment into words
      String[] tokens =
          comment.split("\\t|,|;|\\.|\\?|!|-|:|@|\\[|\\]|\\(|\\)|\\{|\\}|_|\\*|/\"|\\s+");
      // for every word
      for (String token : tokens) {
        token = token.toLowerCase();
        // if noun
        if (addWord(token)) {
          // add to hash map
          if (aggregate.containsKey(token)) {
            int count = aggregate.get(token);
            aggregate.put(token, count + 1);
          } else {
            aggregate.put(token, 1);
          }
        }
      }
    }

    // sort
    // get list to sort
    List<Map.Entry<String, Integer>> sortingList = new ArrayList<>(aggregate.entrySet());
    // create comparator
    Comparator<Map.Entry<String, Integer>> cmp = new Comparator<>() {
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue().compareTo(o1.getValue());
      }
    };
    // sort
    Collections.sort(sortingList, cmp);

    // get each listing's words
    for (Map.Entry<String, Integer> i : sortingList) {
      print += i.getKey() + ": " + i.getValue() + ",";
    }
    return print;
  }

  // checks if word should be put in cloud
  private static boolean addWord(String token) {
    boolean add = true;
    if (blockedWords.contains(token)) {
      add = false;
    }
    return add;
  }

  private static void printRentersBookingsInPeriod(Scanner userInput, int option)
      throws InvalidFormException {
    List<String> print = new ArrayList<>();

    print = getDates(userInput);

    print = DatabaseSelector.getRenterBookingsInPeriod(option, print.get(0), print.get(1));

    // print
    System.out.println("---");
    for (String line : print) {
      System.out.println(line);
      System.out.println("---");
    }
  }

  private static void printBookingsCountInPeriod(Scanner userInput, int option)
      throws InvalidFormException {
    List<String> print = new ArrayList<>();

    print = getDates(userInput);

    print = DatabaseSelector.getTotalBookingsInPeriod(option, print.get(0), print.get(1));

    // print
    System.out.println("---");
    for (String line : print) {
      System.out.println(line);
      System.out.println("---");
    }
  }

  private static void printCancellations(int option) {
    List<String> print = new ArrayList<>();

    print = DatabaseSelector.getCancellations(option);

    // print
    System.out.println("---");
    for (String line : print) {
      System.out.println(line);
      System.out.println("---");
    }

  }

  private static void printCommercialHostsByCountryCity(int option) {
    List<String> print = new ArrayList<>();

    print = DatabaseSelector.getCommercialHost(option);

    // print
    System.out.println("---");
    for (String line : print) {
      System.out.println(line);
      System.out.println("---");
    }

  }

  private static void printHostsByListingCountCountryCity(int option) {
    List<String> print = new ArrayList<>();

    print = DatabaseSelector.getHostListingCount(option);

    // print
    System.out.println("---");
    for (String line : print) {
      System.out.println(line);
      System.out.println("---");
    }
  }

  private static void printListingsCountByCountryCityPostal(int option) {
    List<String> print = new ArrayList<>();

    print = DatabaseSelector.getListingCount(option);

    // print
    System.out.println("---");
    for (String line : print) {
      System.out.println(line);
      System.out.println("---");
    }
  }

  private static List<String> getDates(Scanner userInput) throws InvalidFormException {
    List<String> out = new ArrayList<>();
    // get start date and validate
    String start;
    LocalDate startDate;
    System.out.print(" Start Date as 'YYYY-MM-DD' : ");
    try {
      start = userInput.nextLine();
      startDate = LocalDate.of(Integer.parseInt(start.substring(0, 4)),
          Integer.parseInt(start.substring(5, 7)), Integer.parseInt(start.substring(8)));
    } catch (NoSuchElementException | NumberFormatException invalid) {
      System.out.print("Invalid input. ");
      throw new InvalidFormException();
    } catch (IndexOutOfBoundsException invalid) {
      System.out.print("Invalid format: 'YYYY-MM-DD' ");
      throw new InvalidFormException();
    }
    // get end date and validate
    String end;
    LocalDate endDate;
    long p;
    System.out.print(" End Date as 'YYYY-MM-DD' : ");
    try {
      end = userInput.nextLine();
      endDate = LocalDate.of(Integer.parseInt(end.substring(0, 4)),
          Integer.parseInt(end.substring(5, 7)), Integer.parseInt(end.substring(8)));
    } catch (NoSuchElementException | NumberFormatException invalid) {
      System.out.print("Invalid input. ");
      throw new InvalidFormException();
    } catch (IndexOutOfBoundsException invalid) {
      System.out.print("Invalid format: 'YYYY-MM-DD'. ");
      throw new InvalidFormException();
    }
    p = ChronoUnit.DAYS.between(startDate, endDate);
    if (p <= 0) {
      System.out.print("Needs to be a date after the start date.");
      throw new InvalidFormException();
    }

    out.add(start);
    out.add(end);
    return out;
  }

}
