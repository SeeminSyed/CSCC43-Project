package pages;

import java.util.Scanner;
import models.User;

public class Search {

  public static void main(Scanner userInput, User user) {// when user books, check availability,
                                                         // modify availability based on timing
    int userChoice;

    min(userInput, user);

  }

  private static void min(Scanner userInput, User user) {
    System.out.println("Search by (give input or nothing to skip that filter option):");

    // get search options and validate
    // get listings from database
    // check if in distance
    // print output
  }

  /**
   * https://rosettacode.org/wiki/Haversine_formula#Java
   * 
   * @param lat1
   * @param lon1
   * @param lat2
   * @param lon2
   * @return
   */
  public static double haversine(double lat1, double lon1, double lat2, double lon2) {
    double R = 6372.8; // km
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);

    double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
    double c = 2 * Math.asin(Math.sqrt(a));
    return R * c;
}

}
