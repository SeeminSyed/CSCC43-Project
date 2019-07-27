package models;

import database.DatabaseDeleter;

public class Availability {

  private int availabilityId;
  private String listingUse;
  private String startDate;
  private String endDate;
  private Double price;
  private boolean available;
  private int listingId;

  public Availability(int availabilityId, String listingUse, String startDate, String endDate,
      Double price, boolean available, int listingId) {
    this.availabilityId = availabilityId;
    this.listingUse = listingUse;
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
    this.available = available;
    this.listingId = listingId;
  }

  @Override
  public String toString() {
    return ("From: " + startDate + " to " + endDate + "\nPrice: $" + price);
  }

  public void databaseDeleteAvailability() {
    DatabaseDeleter.deleteAvailability(this.availabilityId);
  }

  public int getAvailabilityId() {
    return availabilityId;
  }

  public void setAvailabilityId(int availabilityId) {
    this.availabilityId = availabilityId;
  }

  public String getListingUse() {
    return listingUse;
  }

  public void setListingUse(String listingUse) {
    this.listingUse = listingUse;
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

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public int getListingId() {
    return listingId;
  }

  public void setListingId(int listingId) {
    this.listingId = listingId;
  }


}
