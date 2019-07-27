package models;

import database.DatabaseSelector;

public class Comment { // TODO
  
  private int bookingId;
  private String commentBody;
  private int commenterId;
  private int commenteeId;
//  private int listingId;
  private String date;
  private int rating;


  public Comment(int bookingId, String commentBody, int commenterId, int commenteeId, /*int listingId,*/
      String date, int rating) {
    super();
    this.bookingId = bookingId;
    this.commentBody = commentBody;
    this.commenterId = commenterId;
    this.commenteeId = commenteeId;
//    this.listingId = listingId;
    this.date = date;
    this.rating = rating;
  }


  @Override
  public String toString() {
    return (getCommentorName() + "   " + getRating() + " -- " + getDate() + "\n " + getCommentBody());
  }
  
  private String getCommentorName() {
    return DatabaseSelector.getUserName(getCommenterId());
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


  public int getCommenterId() {
    return commenterId;
  }


  public void setCommenterId(int commenterId) {
    this.commenterId = commenterId;
  }


  public int getCommenteeId() {
    return commenteeId;
  }


  public void setCommenteeId(int commenteeId) {
    this.commenteeId = commenteeId;
  }


//  public int getListingId() {
//    return listingId;
//  }
//
//
//  public void setListingId(int listingId) {
//    this.listingId = listingId;
//  }


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
