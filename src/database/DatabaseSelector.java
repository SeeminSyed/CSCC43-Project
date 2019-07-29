package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.Availability;
import models.Booking;
import models.Comment;
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
      preparedStatement.close();
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
      preparedStatement.close();
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
      preparedStatement.close();
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
    String sql = "SELECT name FROM Users WHERE sin = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, renterId);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        name = results.getString("name");
      }
      results.close();
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
            results.getInt("card_num"), results.getDouble("cost")));
      }
      results.close();
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
    return availability;
  }


  public static List<Comment> getUserComments(int user_id) {
    List<Comment> comments = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT * FROM UserComments WHERE commentee_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, user_id);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        comments.add(new Comment(results.getInt("booking_id"), results.getString("comment"),
            results.getInt("commenter_id"), user_id, /* results.getInt("listing_id"), */
            results.getString("date"), results.getInt("rating")));
      }
      results.close();
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
    return comments;
  }

  public static List<Booking> getUserBookings(int user_id) {
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
    String sql = "SELECT * FROM Bookings WHERE user_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, user_id);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        bookings.add(new Booking(results.getInt("booking_id"), results.getString("start_date"),
            results.getString("end_date"), results.getString("status"),
            results.getInt("listing_id"), user_id, results.getInt("card_num"),
            results.getDouble("cost")));
      }

      results.close();
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
    return bookings;
  }


  public static int getUserId(int listing_id) {
    int id = 0;
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT user_id FROM Listings WHERE listing_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, listing_id);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        id = results.getInt("user_id");
      }
      results.close();
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
    return id;
  }

  public static List<String> getListingCount(int option) {
    List<String> print = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    String sql = "";
    // select
    switch (option) {
      case 1:
        sql = "SELECT * FROM listingCountByCountry";
        break;
      case 2:
        sql = "SELECT * FROM listingCountByCountryCity";
        break;
      case 3:
        sql = "SELECT * FROM listingCountByCountryCityZip";
        break;
      default:
        break;
    }
    sql += " ORDER BY listing_count DESC";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        // select
        switch (option) {
          case 1:
            print.add(results.getString("country") + " and listing count: "
                + results.getInt("listing_count"));
            break;
          case 2:
            print.add(results.getString("country") + ", " + results.getString("city")
                + " and listing count: " + results.getInt("listing_count"));
            break;
          case 3:
            print.add(results.getString("country") + ", " + results.getString("city") + ", "
                + results.getString("postal_code") + " and listing count: "
                + results.getInt("listing_count"));
            break;
          default:
            break;
        }
      }
      results.close();
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
    return print;
  }

  public static List<String> getHostListingCount(int option) {
    List<String> print = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    String sql = "";
    // select
    switch (option) {
      case 1:
        sql = "SELECT * FROM listingsCountPerHostByCountry";
        break;
      case 2:
        sql = "SELECT * FROM listingsCountPerHostByCity";
        break;
      default:
        break;
    }
    sql += " ORDER BY listing_count DESC";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        // select
        switch (option) {
          case 1:
            print.add("Host: " + results.getInt("user_id") + " in " + results.getString("country")
                + " and listing count: " + results.getInt("listing_count"));
            break;
          case 2:
            print.add("Host: " + results.getInt("user_id") + " in " + results.getString("country")
                + ", " + results.getString("city") + " and listing count: "
                + results.getInt("listing_count"));
            break;
          default:
            break;
        }
      }
      results.close();
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
    return print;
  }

  public static List<String> getCommercialHost(int option) {
    List<String> print = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    String sql = "";
    // select
    switch (option) {
      case 1:
        sql = "SELECT * FROM commercialHostPerCountry";
        break;
      case 2:
        sql = "SELECT * FROM commercialHostPerCity";
        break;
      default:
        break;
    }
    sql += " ORDER BY listing_count DESC";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        // select
        switch (option) {
          case 1:
            print.add("Host: " + results.getInt("user_id") + " in " + results.getString("country")
                + " and listing count: " + results.getInt("listing_count"));
            break;
          case 2:
            print.add("Host: " + results.getInt("user_id") + " in " + results.getString("country")
                + ", " + results.getString("city") + " and listing count: "
                + results.getInt("listing_count"));
            break;
          default:
            break;
        }
      }
      results.close();
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
    return print;
  }

  public static List<String> getCancellations(int option) {
    List<String> print = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    String sql = "";
    // select
    switch (option) {
      case 1:
        sql = "SELECT * FROM bookingCancellations WHERE status = 'Cancelled by Host'";
        break;
      case 2:
        sql = "SELECT * FROM commercialHostPerCity WHERE status = 'Cancelled by Renter'";
        break;
      default:
        break;
    }
    sql += " ORDER BY cancel_count DESC";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        // select
        switch (option) {
          case 1:
            print.add("Host: " + results.getInt("user_id") + ", cancellations: "
                + results.getInt("cancel_count"));
            break;
          case 2:
            print.add("Renter: " + results.getString("user_id") + ", cancellations: "
                + results.getInt("cancel_count"));
            break;
          default:
            break;
        }
      }
      results.close();
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
    return print;
  }

  public static List<String> getTotalBookingsInPeriod(int option, String start, String end) {
    List<String> print = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    String sql = "";
    // select
    switch (option) {
      case 1:
        sql = "SELECT * FROM bookingsPerCityZipCodeAndPeriod";
        break;
      case 2:
        sql = "SELECT * FROM bookingsPerCityAndPeriod";
        break;
      default:
        break;
    }
    sql += " WHERE start_date >= ? AND end_date <= ? ORDER BY cancel_count DESC";

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, start);
      preparedStatement.setString(2, end);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        // select
        switch (option) {
          case 1:
            print.add(results.getString("country") + ", " + results.getString("city")
                + " and booking count: " + results.getInt("booking_count"));
            break;
          case 2:
            print.add(results.getString("country") + ", " + results.getString("city") + ", "
                + results.getString("zipCode") + " and booking count: "
                + results.getInt("booking_count"));
            break;
          default:
            break;
        }
      }
      results.close();
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
    return print;
  }

  public static List<String> getRenterBookingsInPeriod(int option, String start, String end) {
    List<String> print = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    String sql = "";
    // select
    switch (option) {
      case 1:
        sql = "SELECT * FROM userBookingsCountAndPeriod";
        break;
      case 2:
        sql = "SELECT * FROM userBookingsCountPerCityAndPeriod";
        break;
      default:
        break;
    }
    sql +=
        " WHERE start_date >= ? AND end_date <= ? AND booking_count > 2 ORDER BY booking_count DESC";

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, start);
      preparedStatement.setString(2, end);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        // select
        switch (option) {
          case 1:
            print.add("Renter: " + results.getString("user_id") + " and booking count: "
                + results.getInt("booking_count"));
            break;
          case 2:
            print.add("Renter: " + results.getString("user_id") + " in "
                + results.getString("country") + ", " + results.getString("city")
                + " and booking count: " + results.getInt("booking_count"));
            break;
          default:
            break;
        }
      }
      results.close();
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
    return print;
  }

  /**
   * returns hashmap where key is listing_id and values are lists of comments for that listing_id
   */
  public static HashMap<Integer, List<String>> getAllListingComments() {
    HashMap<Integer, List<String>> print = new HashMap<>();

    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    String sql = "SELECT * FROM allListingComments ORDER BY listing_id DESC";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      ResultSet results = preparedStatement.executeQuery();
      if (results.next()) {
        int key = results.getInt("listing_id");
        if (print.containsKey(key)) {
          print.get(key).add(results.getString("comment"));
        } else {
          print.put(key, new ArrayList<>());
        }
      }
      results.close();
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
    return print;
  }

  public static List<String> getAllListingsByCoordinate(Double lat, Double lon, Double dist) {
    List<String> listingStrings = new ArrayList<>();
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }
    // select all listing_ids, lat, long from address
    // calculate which have right distance
    // get listings from all correct listing ids
    // return strings

    // select
    String sql = "SELECT * FROM searchListingInfo";
    // A.latitude, A.longitude, L.title, L.description, L.listing_id

    // A.latitude, A.longitude, A.street, A.unit, A.city, A.state, A.country, A.zipCode,
    // L.listing_id, L.listing_type, L.num_bedrooms, L.num_beds, L.num_bathrooms

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        double x = results.getDouble("latitude");
        double y = results.getDouble("longitude");
        double d = haversine(lat, lon, x, y);
        double price = results.getDouble("price");
        if (d < dist) {
          listingStrings.add(results.getInt("listing_id") + ". " + results.getString("title")
              + " -- " + String.format("%.2f", d) + "km away\n" + results.getString("description")
              + "\n From: " + results.getString("start_date") + " -- "
              + results.getString("end_date") + " At $" + String.format("%.2f", price));
        }

      }
      results.close();
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
    return listingStrings;
  }

  /**
   * https://rosettacode.org/wiki/Haversine_formula#Java
   * 
   * @param lat1
   * @param lon1
   * @param lat2
   * @param lon2
   * @return
   */
  public static double haversine(double lat1, double lon1, double lat2, double lon2) {
    double R = 6372.8; // km
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);

    double a = Math.pow(Math.sin(dLat / 2), 2)
        + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
    double c = 2 * Math.asin(Math.sqrt(a));
    return R * c;
  }

  public static Listing getListing(int listing_id) {
    Listing l = null;
    // Get connection
    Connection connection = null;
    try {
      connection = Driver.connectOrCreateDataBase();
    } catch (ClassNotFoundException e) {
      System.out.println("Something went wrong with your connection! See details below: ");
      e.printStackTrace();
    }

    // select
    String sql = "SELECT * FROM Listings WHERE listing_id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, listing_id);

      ResultSet results = preparedStatement.executeQuery();
      while (results.next()) {
        l = new Listing(listing_id, results.getInt("user_id"), results.getString("listing_type"),
            results.getInt("num_bedrooms"), results.getInt("num_beds"),
            results.getInt("num_bathrooms"), results.getString("title"),
            results.getString("description"));
      }
      results.close();
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
    return l;
  }

}
