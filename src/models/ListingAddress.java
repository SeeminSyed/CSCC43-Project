package models;

public class ListingAddress {

  private int listing_id;
  private String unit;
  private String street;
  private String city;
  private String state;
  private String country;
  private String zipCode;
  private Double x;
  private Double y;

  public ListingAddress(int listing_id, String unit, String street, String city, String state,
      String country, String zipCode, Double x, Double y) {
    this.listing_id = listing_id;
    this.unit = unit;
    this.street = street;
    this.city = city;
    this.country = country;
    this.zipCode = zipCode;
    this.x = x;
    this.y = y;
  }

  // TODO
  @Override
  public String toString() {
    return (getStreet() + ", " + getUnit() + ", " + getCity() + ", " + getCountry() + ", "
        + getZipCode() + "\nCoordinates: (" + getX() + ", " + getY() + ")");
  }

  public int getListing_id() {
    return listing_id;
  }

  public void setListing_id(int listing_id) {
    this.listing_id = listing_id;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public Double getX() {
    return x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getY() {
    return y;
  }

  public void setY(Double y) {
    this.y = y;
  }


}