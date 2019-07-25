package models;

import java.util.ArrayList;
import java.util.List;
import database.*;

public class User {

  // basic info
  private int sin;
  private String email;
  private String password;

  // complex info
  private final List<Listing> listings = new ArrayList<>();
  private final List<Booking> bookings = new ArrayList<>();
  private final List<Comment> comments = new ArrayList<>();
  private final List<CreditCard> cards = new ArrayList<>();

  /**
   * Only using variables referenced in the application
   * 
   * @param sin
   */
  public User(int sin, String email, String password) {
    this.sin = sin;
    this.email = email;
    this.password = password;

    this.cards.addAll(DatabaseSelector.getUserCards(getSin()));
    this.bookings.addAll(DatabaseSelector.getUserBookings(getSin()));
    this.listings.addAll(DatabaseSelector.getUserListings(getSin()));
    this.comments.addAll(DatabaseSelector.getUserComments(getSin()));
  }

  // public boolean isEligibleRenter(int renter) {
  // for (Listing l : listings) {
  // if (l.isEligibleRenter(renter)) {
  // return true;
  // }
  // }
  // return false;
  // }
  //
  // public boolean isEligibleCommenter(int renter) {
  // for (Comment c : comments) {
  // if (c.getOrigin() == renter) {
  // return false;
  // }
  // }
  // return false;
  // }
  //
  // public boolean isEligibleRater(int renter) {
  // for (Rating c : ratings) {
  // if (c.getOrigin() == renter) {
  // return false;
  // }
  // }
  // return false;
  // }



  public void databaseDeleteUser() {
    DatabaseDeleter.deleteUser(sin);
  }

  public void deleteCard(int cardListNum) {
    // delete from database
    this.cards.get(cardListNum - 1).databaseDeleteCard();
    // delete from user object
    this.cards.remove(cardListNum - 1);
  }

  public void addCard(int card_num, String card_type, String exp_date) {
    // add to user object
    this.cards.add(new CreditCard(this.sin, card_num, card_type, exp_date));
    // insert into database
    this.cards.get(getNumCards() - 1).databaseInsertCard(this.sin, card_num, card_type, exp_date);
  }

  public void deleteListing(int listingListNum) {
    // delete from database
    this.listings.get(listingListNum - 1).databaseDeleteListing();
    // delete from user object
    this.listings.remove(listingListNum - 1);
  }

  public void addListing(int listing_id, String listing_type, int num_bedrooms, int num_beds, int num_bathrooms, String title, String description) {
    // add to user object
    this.listings.add(new Listing(listing_id, this.sin, listing_type, num_bedrooms, num_beds, num_bathrooms, title, description));
    // insert into database
    this.listings.get(getNumListings() - 1).databaseInsertListing(listing_id, this.sin, listing_type, num_bedrooms, num_beds, num_bathrooms, title, description);
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

}
