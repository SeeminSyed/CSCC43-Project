package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.Availability;
import models.Booking;
import models.CreditCard;
import models.Listing;
import models.ListingComment;

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

  public static List<CreditCard> getUserCards(int userId) {
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

      preparedStatement.setInt(1, userId);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        userCards.add(new CreditCard(userId, results.getInt("card_num"),
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

  public static List<Listing> getUserListings(int userId) {
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

      preparedStatement.setInt(1, userId);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        userListings.add(new Listing(results.getInt("listing_id"), userId,
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

  public static List<String> getAllAmenities() {
    List<String> amenities = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT amenity FROM Amenities";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        amenities.add(results.getString(1));
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
    return amenities;
  }

  public static Set<String> getListingAmenities(int listingId) {
    Set<String> amenities = new HashSet<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT amenity_id FROM ListingAmenities WHERE listing_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, listingId);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        amenities.add(getAmenityName(results.getInt(1), connection));
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
    return amenities;
  }

  private static String getAmenityName(int id, Connection connection) {
    String name = "";

    // select
    String sql = "SELECT amenity FROM Amenities WHERE amenity_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, id);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        name = results.getString(1);
      }
      results.close();

    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    }
    return name;
  }

  public static int getAmenityId(String amenityName, Connection connection) {
    int id = 0;

    // select
    String sql = "SELECT amenity_id FROM Amenities WHERE amenity = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, amenityName);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        id = results.getInt(1);
      }
      results.close();

    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    }
    return id;
  }

  public static List<ListingComment> getListingComments(int listingId) {
    List<ListingComment> listingComments = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT * FROM ListingComments WHERE listing_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, listingId);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        listingComments.add(new ListingComment(results.getInt("booking_id"),
            results.getString("comment"), results.getInt("renter_id"), listingId,
            results.getString("date"), results.getInt("rating")));
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
    return listingComments;
  }


  public static boolean getAddress(String unit, Double x, Double y) {
    boolean present = false;
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT * FROM Address WHERE unit = ? AND latitude = ? AND longitude = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, unit);
      preparedStatement.setDouble(2, x);
      preparedStatement.setDouble(3, y);

      present = preparedStatement.execute();

    } catch (SQLException sqlError) {
      sqlError.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlError) {
        sqlError.printStackTrace();
      }
    }
    return present;
  }

  public static String getUserName(int renterId) {
    String name = "";
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT name FROM Users WHERE user_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, renterId);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        name = results.getString("name");
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
    return name;
  }

  public static List<Booking> getListingBookings(int userId, int listingId) {
    List<Booking> bookings = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT * FROM Bookings WHERE listing_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, listingId);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        bookings.add(new Booking(results.getInt("booking_id"), results.getString("start_date"),
            results.getString("end_date"), results.getString("status"), listingId, userId,
            results.getInt("card_num")));
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
    return bookings;
  }

  public static List<Availability> getListingAvailabilities(int listingId) {
    List<Availability> availability = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT * FROM Availability WHERE listing_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, listingId);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        availability.add(
            new Availability(results.getInt("availability_id"), results.getString("listing_use"),
                results.getString("start_date"), results.getString("end_date"),
                results.getDouble("price"), results.getBoolean("available"), listingId));
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
    return availability;
  }


}
