package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    System.out.println("before insert");

    // Insert
    String sql =
        "SELECT sin FROM Users WHERE email = ? AND password = ?";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql);

      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);

      ResultSet results = preparedStatement.executeQuery();
      results.next();
      sin = results.getInt("sin");
      results.close();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      
  }
    return sin;
  }

}
