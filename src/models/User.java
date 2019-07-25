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
   * @param sin
   */
  public User(int sin, String email, String password) {
    this.sin = sin;
    this.email = email;
    this.password = password;
    
    this.cards.addAll(DatabaseSelector.getUserCards(getSin()));
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
  
  public int getCardsSize() {
    return this.cards.size();
  }

  public void databaseDeleteUser() {
    DatabaseDeleter.deleteUser(sin);
  }

  public void deleteCard(int cardListNum) {
    // delete from database
    this.cards.get(cardListNum-1).databaseDeleteCard();
    // delete from user object
    this.cards.remove(cardListNum-1);
  }
  
  public void addCard(int card_num, String card_type, String exp_date) {
    // add to user object
    this.cards.add(new CreditCard(this.sin, card_num, card_type, exp_date));
    //insert into database
    this.cards.get(getCardsSize()-1).databaseInsertCard(this.sin, card_num, card_type, exp_date);
  }
}
