package models;

import database.DatabaseDeleter;

public class Booking {

  private int bookingId;
  private String startDate;
  private String endDate;
  private String status;
  private int listingId;
  private int userId;
  private int cardNum; // only fill if card belongs to user of userId

  public Booking(int bookingId, String startDate, String endDate, String status, int listingId,
      int userId, int cardNum) {
    this.bookingId = bookingId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
    this.listingId = listingId;
    this.userId = userId;
    this.cardNum = cardNum;
  }

  @Override
  public String toString() {
    return ("From: " + startDate + " to " + endDate + "\nStatus: " + status + "\n Paid with:"
        + cardNum);
  }

  public void databaseDeleteBooking() {
    DatabaseDeleter.deleteBooking(this.bookingId);
  }

  public int getBookingId() {
    return bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getListingId() {
    return listingId;
  }

  public void setListingId(int listingId) {
    this.listingId = listingId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getcardNum() {
    return cardNum;
  }

  public void setcardNum(int cardNum) {
    this.cardNum = cardNum;
  }

}
