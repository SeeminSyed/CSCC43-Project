package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

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

      preparedStatement.close();
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

      preparedStatement.close();
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

  public static int insertListing(int user_id, String listing_type, int num_bedrooms, int num_beds,
      int num_bathrooms, String title, String description) {
    int row = -1;
    int listing_id = -1;
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
        ResultSet results = preparedStatement.getGeneratedKeys();
        results.next();
        listing_id = results.getInt(1);
      }

      preparedStatement.close();
    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return listing_id;
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

      preparedStatement.close();
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

  public static int insertListingAmenities(int listing_id, Set<String> amenities) {
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
    String sql = "INSERT INTO ListingAmenities(listing_id, amenity_id) VALUES(?,?)";
    try {
      for (String amenityName : amenities) {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, listing_id);
        preparedStatement.setInt(2, DatabaseSelector.getAmenityId(amenityName, connection));

        row = preparedStatement.executeUpdate();

        preparedStatement.close();
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

  public static int insertListingAvailability(String listing_use, String start_date,
      String end_date, Double price, boolean available, int listing_id) {
    int row = -1;
    int availability_id = -1;
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
        "INSERT INTO Availability(listing_use, start_date, end_date, price, available, listing_id) VALUES(?,?,?,?,?,?)";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


      preparedStatement.setString(1, listing_use);
      preparedStatement.setString(2, start_date);
      preparedStatement.setString(3, end_date);
      preparedStatement.setDouble(4, price);
      preparedStatement.setBoolean(5, available);
      preparedStatement.setInt(6, listing_id);

      row = preparedStatement.executeUpdate();

      if (row > 0) {
        ResultSet results = preparedStatement.getGeneratedKeys();
        results.next();
        availability_id = results.getInt(1);
      }
      preparedStatement.close();
    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return availability_id;
  }

  public static boolean cancelBooking(int booking_id, String status, String end_date) {
    // Get connection
    boolean exit = false;
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // delete
    String sql = "UPDATE Bookings SET status = ? AND end_date = ? WHERE bookings_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, status);
      preparedStatement.setString(2, end_date);
      preparedStatement.setInt(3, booking_id);

      if (preparedStatement.executeUpdate() > 0) {
        exit = true;
      }
      preparedStatement.close();
    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return exit;
  }


  public static boolean updateAvalilabilityPrice(int availabilityId, Double newPrice) {
    // Get connection
    boolean exit = false;
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // update
    String sql = "UPDATE Availability SET price = ? WHERE availability_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setDouble(1, newPrice);
      preparedStatement.setInt(2, availabilityId);

      if (preparedStatement.executeUpdate() > 0) {
        exit = true;
      }
      preparedStatement.close();
    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return exit;
  }

  public static boolean updateAvalilabilityDates(int availabilityId, String newStart,
      String newEnd) {
    // Get connection
    boolean exit = false;
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // update
    String sql = "UPDATE Availability SET new_start = ? AND new_end = ? WHERE availability_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, newStart);
      preparedStatement.setString(2, newEnd);
      preparedStatement.setInt(3, availabilityId);

      if (preparedStatement.executeUpdate() > 0) {
        exit = true;
      }
      preparedStatement.close();
    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return exit;
  }

  public static int insertUserComment(int booking_id, String comment, int commenter_id,
      int commentee_id, int rating) {
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
        "INSERT INTO UserComments(booking_id, comment, commenter_id, commentee_id, rating) VALUES(?,?,?,?,?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, booking_id);
      preparedStatement.setString(2, comment);
      preparedStatement.setInt(3, commenter_id);
      preparedStatement.setInt(4, commentee_id);
      preparedStatement.setInt(5, rating);

      row = preparedStatement.executeUpdate();

      preparedStatement.close();
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

  public static int insertListingComment(int booking_id, String comment, int user_id,
      int listing_id, int rating) {
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
        "INSERT INTO ListingComments(booking_id, comment, user_id, listing_id, rating) VALUES(?,?,?,?,?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, booking_id);
      preparedStatement.setString(2, comment);
      preparedStatement.setInt(3, user_id);
      preparedStatement.setInt(4, listing_id);
      preparedStatement.setInt(5, rating);

      row = preparedStatement.executeUpdate();

      preparedStatement.close();
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
