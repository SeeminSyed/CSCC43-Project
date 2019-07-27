package models;

import java.util.ArrayList;
import java.util.List;
import database.*;

public class User {

  // basic info
  private int sin;
  private String email;
  private String password;
  private String name;

  // complex info
  private final List<Listing> listings = new ArrayList<>();
  private final List<Booking> bookings = new ArrayList<>();
  private final List<Comment> comments = new ArrayList<>();
  private final List<CreditCard> cards = new ArrayList<>();

  public User(int sin, String email, String password) {
    this.sin = sin;
    this.email = email;
    this.password = password;
    this.name = DatabaseSelector.getUserName(getSin());

    this.cards.addAll(DatabaseSelector.getUserCards(getSin()));
    // this.bookings.addAll(DatabaseSelector.getUserBookings(getSin()));
    this.listings.addAll(DatabaseSelector.getUserListings(getSin()));
    // this.comments.addAll(DatabaseSelector.getUserComments(getSin()));
  }

  public void databaseDeleteUser() {
    DatabaseDeleter.deleteUser(sin);
  }

  public void deleteCard(int cardListNum) {
    // delete from database
    this.cards.get(cardListNum).databaseDeleteCard();
    // delete from user object
    this.cards.remove(cardListNum);
  }

  /**
   * Returns true if added
   */
  public boolean addCard(int card_num, String card_type, String exp_date) {
    // insert into database
    if (databaseInsertCard(this.sin, card_num, card_type, exp_date) == true) {
      // add to user object
      this.cards.add(new CreditCard(this.sin, card_num, card_type, exp_date));
      return true;
    }
    return false;
  }

  /**
   * Returns true if added
   */
  public boolean databaseInsertCard(int user_id, int card_num, String card_type, String exp_date) {
    // insert info to database and if row number returned, then valid
    if (DatabaseInserter.insertCard(user_id, card_num, card_type, exp_date) > 0) {
      System.out.println("Card Added!");
      return true;
    } else {
      System.out.println(">>> \nYour card was not added. Please try again.\n>>>");
      return false;
    }

  }

  public void deleteListing(int listingListNum) {
    // delete from database
    this.listings.get(listingListNum).databaseDeleteListing();
    // delete from user object
    this.listings.remove(listingListNum);
  }

  /**
   * Returns true if added
   */
  public boolean addListing(String listing_type, int num_bedrooms, int num_beds, int num_bathrooms,
      String title, String description) {
    // insert into database
    int listing_id = databaseInsertListing(this.sin, listing_type, num_bedrooms, num_beds,
        num_bathrooms, title, description);

    // add to user object
    if (listing_id > 0) {
      this.listings.add(new Listing(listing_id, this.sin, listing_type, num_bedrooms, num_beds,
          num_bathrooms, title, description));
      return true;
    }
    return false;
  }

  /**
   * Returns listing_id if added
   */
  private int databaseInsertListing(int user_id, String listing_type, int num_bedrooms,
      int num_beds, int num_bathrooms, String title, String description) {
    int listing_id = 0;
    // insert info to database and if row number returned, then valid
    if ((listing_id = DatabaseInserter.insertListing(user_id, listing_type, num_bedrooms, num_beds,
        num_bathrooms, title, description)) > 0) {
      System.out.println("Listing Added!");
    } else {
      System.out.println(">>> \nYour Listing was not added. Please try again.\n>>>");
    }
    return listing_id;
  }

  // ** GETTERS/SETTERS **//

  public int getSin() {
    return sin;
  }

  public void setSin(int sin) {
    this.sin = sin;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Listing> getListings() {
    return listings;
  }

  public int getNumListings() {
    return this.listings.size();
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public int getNumBookings() {
    return this.bookings.size();
  }

  public List<Comment> getComments() {
    return comments;
  }

  public int getNumComments() {
    return this.comments.size();
  }

  public List<CreditCard> getCards() {
    return cards;
  }

  public int getNumCards() {
    return this.cards.size();
  }

  /**
   * Returns suggested price increase of adding an amenity to a listing based on comparisons with
   * other listings TODO
   */
  public String getAmenityPrice(String amenity) {
    return "TODO";
  }

  /**
   * Returns all available amenities
   * 
   */
  public List<String> getAllAmenities() {
    return DatabaseSelector.getAllAmenities();
  }

}
