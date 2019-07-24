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


}
