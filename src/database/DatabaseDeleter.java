package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDeleter {

  /**
   * Removes user from database, as well as any associated data: listings, creditInfo, bookings,
   * comments.
   * 
   * @param user_id
   */
  public static void deleteUser(int user_id) {

    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // delete
    String sql = "DELETE FROM Users WHERE sin = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, user_id);

      preparedStatement.executeUpdate();

    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
  }

  /**
   * Removes credit card from database.
   * @param user_id
   * @param card_id
   */
  public static void deleteCard(int user_id, int card_id) {
 // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // delete
    String sql = "DELETE FROM CreditInfo WHERE user_id = ? AND card_num = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, user_id);
      preparedStatement.setInt(2, card_id);

      preparedStatement.executeUpdate();

    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
  }



}
