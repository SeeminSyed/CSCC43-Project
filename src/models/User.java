package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import database.DatabaseDeleter;

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
   * @param sin
   */
  public User(int sin, String email, String password) {
    this.sin = sin;
    this.email = email;
    this.password = password;
  }

//  public boolean isEligibleRenter(int renter) {
//    for (Listing l : listings) {
//      if (l.isEligibleRenter(renter)) {
//        return true;
//      }
//    }
//    return false;
//  }
//
//  public boolean isEligibleCommenter(int renter) {
//    for (Comment c : comments) {
//      if (c.getOrigin() == renter) {
//        return false;
//      }
//    }
//    return false;
//  }
//
//  public boolean isEligibleRater(int renter) {
//    for (Rating c : ratings) {
//      if (c.getOrigin() == renter) {
//        return false;
//      }
//    }
//    return false;
//  }


  
  //** GETTERS/SETTERS **//
  
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
  public List<Booking> getBookings() {
    return bookings;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public List<CreditCard> getCards() {
    return cards;
  }

  public void deleteUser() {
    DatabaseDeleter.deleteUser(sin);
  }
}
