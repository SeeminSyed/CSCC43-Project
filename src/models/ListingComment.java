package models;

import database.DatabaseSelector;

public class ListingComment {

  private int bookingId;
  private String commentBody;
  private int renterId;
  private int listingId;
  private String date;
  private int rating;
  
  public ListingComment(int bookingId, String commentBody, int renterId, int listingId, String date, int rating) {
    this.bookingId = bookingId;
    this.commentBody = commentBody;
    this.renterId = renterId;
    this.listingId = listingId;
    this.date = date;
    this.rating= rating;
  }

  @Override
  public String toString() {
    return (getRenterName() + "   " + getRating() + " -- " + getDate() + "\n " + getCommentBody());
  }

  private String getRenterName() {
    return DatabaseSelector.getUserName(getRenterId());
  }

  public int getBookingId() {
    return bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public String getCommentBody() {
    return commentBody;
  }

  public void setCommentBody(String commentBody) {
    this.commentBody = commentBody;
  }

  public int getRenterId() {
    return renterId;
  }

  public void setRenterId(int renterId) {
    this.renterId = renterId;
  }

  public int getListingId() {
    return listingId;
  }

  public void setListingId(int listingId) {
    this.listingId = listingId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

}
