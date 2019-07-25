package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInserter {

  /*
   * Inserts a new user into the database. Returns -1 if insert fails.
   */
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
    String sql =
        "INSERT INTO CreditInfo(user_id, card_num, card_type, exp_date) VALUES(?,?,?,?)";
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


}
