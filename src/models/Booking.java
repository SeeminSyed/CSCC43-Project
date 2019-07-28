package models;

import database.DatabaseInserter;
import database.DatabaseSelector;

public class Booking {

  private int bookingId;
  private String startDate;
  private String endDate;
  private String status;
  private int listingId;
  private int renterId;
  private int cardNum; // only fill if card belongs to renter of renterId
  private Double cost;

  public Booking(int bookingId, String startDate, String endDate, String status, int listingId,
      int renterId, int cardNum, Double cost) {
    this.bookingId = bookingId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
    this.listingId = listingId;
    this.renterId = renterId;
    this.cardNum = cardNum;
    this.cost = cost;
  }

  @Override
  public String toString() {
    return ("From: " + startDate + " to " + endDate + "\nStatus: " + status + "\n Paid $" + cost
        + " with:" + cardNum);
  }

  public void databaseCancelBooking(String status, String end_date) {
    if (DatabaseInserter.cancelBooking(this.bookingId, status, end_date)) {
      this.setStatus(status);
      this.setEndDate(end_date);
    }
  }

  public int getUserId() {
    return DatabaseSelector.getUserId(listingId);
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

  public int getRenterId() {
    return renterId;
  }

  public void setRenterId(int renterId) {
    this.renterId = renterId;
  }

  public int getcardNum() {
    return cardNum;
  }

  public void setcardNum(int cardNum) {
    this.cardNum = cardNum;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

}
