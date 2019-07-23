package database;

import java.sql.*;

public class Driver {

  private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
  private static final String CONNECTION = "jdbc:mysql://localhost:3306/mybnb";
  // Database credentials
  private static final String USER = "root";
  private static final String PASS = "";

  /**
   * This will connect to existing database, or create it if it's not there.
   * @return the database connection.
   */
  protected static Connection connectOrCreateDataBase() throws ClassNotFoundException {
    Connection connection = null;
    System.out.println("Connecting to database...");

    try {    
      Class.forName(dbClassName);
      connection = DriverManager.getConnection(CONNECTION, USER, PASS);
      System.out.println("Successfully connected to MySQL!");
    
    } catch (Exception e) {
      System.out.println("Something went wrong with your connection! see below details: ");
      e.printStackTrace();
    }
    
    return connection;
  }
  
}
