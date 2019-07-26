package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Listing;

public class DatabaseInserter {

  public static int insertUser(int sin, String name, String email, String password, String dob,
      String occupation, Integer phoneNum) {

    int row = -1;
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // Insert
    String sql =
        "INSERT INTO Users(sin, name, email, password, dob, occupation, phoneNum) VALUES(?,?,?,?,?,?,?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, sin);
      preparedStatement.setString(2, name);
      preparedStatement.setString(3, email);
      preparedStatement.setString(4, password);
      preparedStatement.setString(5, dob);
      preparedStatement.setString(6, occupation);
      preparedStatement.setInt(7, phoneNum);

      row = preparedStatement.executeUpdate();

    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return row;
  }

  public static int insertCard(int user_id, int card_num, String card_type, String exp_date) {
    int row = -1;
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // Insert
    String sql = "INSERT INTO CreditInfo(user_id, card_num, card_type, exp_date) VALUES(?,?,?,?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, user_id);
      preparedStatement.setInt(2, card_num);
      preparedStatement.setString(3, card_type);
      preparedStatement.setString(4, exp_date);

      row = preparedStatement.executeUpdate();

    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return row;
  }

  public static int insertListing(int user_id, String listing_type,
      int num_bedrooms, int num_beds, int num_bathrooms, String title, String description) {
    int row = -1;
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // Insert
    String sql =
        "INSERT INTO Listings(user_id, listing_type, num_bedrooms, num_beds, num_bathrooms, title, description) VALUES(?,?,?,?,?,?,?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);


      preparedStatement.setInt(1, user_id);
      preparedStatement.setString(2, listing_type);
      preparedStatement.setInt(3, num_bedrooms);
      preparedStatement.setInt(4, num_beds);
      preparedStatement.setInt(5, num_bathrooms);
      preparedStatement.setString(6, title);
      preparedStatement.setString(7, description);

      row = preparedStatement.executeUpdate();

      if (row > 0) {
        // select
        sql =
            "SELECT * FROM Listings WHERE user_id = ?, listing_type = ?, num_bedrooms = ?, num_beds = ?, num_bathrooms = ?, title = ?, description = ?";
        preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, user_id);
        ResultSet results = preparedStatement.getGeneratedKeys();
        results.next();
        row = results.getInt(1); 
      }
    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return row;
  }

  public static int insertAddress(int listing_id, String unit, String street, String city,
      String state, String country, String zipCode, Double latitude, Double longitude) {
    int row = -1;
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // Insert
    String sql =
        "INSERT INTO Listings(listing_id, unit, street, city, state, country, zipCode, latitude, longitude) VALUES(?,?,?,?,?,?,?,?,?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, listing_id);
      preparedStatement.setString(2, unit);
      preparedStatement.setString(3, street);
      preparedStatement.setString(4, city);
      preparedStatement.setString(5, state);
      preparedStatement.setString(6, country);
      preparedStatement.setString(7, zipCode);
      preparedStatement.setDouble(8, latitude);
      preparedStatement.setDouble(9, longitude);

      row = preparedStatement.executeUpdate();

    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return row;
  }

}
