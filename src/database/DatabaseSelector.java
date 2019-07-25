package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.CreditCard;

public class DatabaseSelector {

  /*
   * Gets SIN from email and password. Returns -1 if not present.
   */
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

    // Insert
    String sql = "SELECT sin FROM Users WHERE email = ? AND password = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);

      ResultSet results = preparedStatement.executeQuery();
      if( results.next()) {
        sin = results.getInt("sin");
      }
      results.close();

    } catch (SQLException sqlError) {
      //sqlError.printStackTrace();
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

    // Insert
    String sql = "SELECT * FROM CreditInfo WHERE user_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, user_id);

      ResultSet results = preparedStatement.executeQuery();
      while(results.next()) {
         userCards.add(new CreditCard(user_id, results.getInt("card_num"), results.getString("card_type"), results.getString("exp_date")));
      }
      results.close();

    } catch (SQLException sqlError) {
      //sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return userCards;
  }
  
}
