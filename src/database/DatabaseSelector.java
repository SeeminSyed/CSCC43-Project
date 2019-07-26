package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Booking;
import models.Comment;
import models.CreditCard;
import models.Listing;

public class DatabaseSelector {

  public static int getSIN(String email, String password) {

    int sin = -1;
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT sin FROM Users WHERE email = ? AND password = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        sin = results.getInt("sin");
      }
      results.close();

    } catch (SQLException sqlError) {
       sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return sin;
  }

  public static List<CreditCard> getUserCards(int user_id) {
    List<CreditCard> userCards = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT * FROM CreditInfo WHERE user_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, user_id);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        userCards.add(new CreditCard(user_id, results.getInt("card_num"),
            results.getString("card_type"), results.getString("exp_date")));
      }
      results.close();

    } catch (SQLException sqlError) {
       sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return userCards;
  }

  public static List<Listing> getUserListings(int user_id) {
    List<Listing> userListings = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT * FROM Listings WHERE user_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, user_id);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        userListings.add(new Listing(results.getInt("listing_id"), user_id,
            results.getString("listing_type"), results.getInt("num_bedrooms"),
            results.getInt("num_beds"), results.getInt("num_bathrooms"), results.getString("title"),
            results.getString("description")));
      }
      results.close();

    } catch (SQLException sqlError) {
       sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return userListings;
  }

}
