package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import database.DatabaseDeleter;
import database.DatabaseInserter;
import database.DatabaseSelector;

public class Listing {

  private int listing_id;
  private int user_id;
  // ('Apartment', 'House', 'Secondary Unit', 'Bed and Breakfast', 'Boutique Hotel')
  private String listing_type;
  private int num_bedrooms;
  private int num_beds;
  private int num_bathrooms;
  private String title;
  private String description;
  private ListingAddress address;

  private final Set<String> amenities = new HashSet<>();
  private final List<Comment> comments = new ArrayList<>();
  private final List<Availability> availabilities = new ArrayList<>();

  /**
   * Only using variables referenced in the application
   * 
   * @param sin
   */
  public Listing(int listing_id, int user_id, String listing_type, int num_bedrooms, int num_beds,
      int num_bathrooms, String title, String description) {
    this.listing_id = listing_id;
    this.user_id = user_id;
    this.listing_type = listing_type;
    this.num_bedrooms = num_bedrooms;
    this.num_beds = num_beds;
    this.num_bathrooms = num_bathrooms;
    this.title = title;
    this.description = description;

    // this.amenities.addAll(DatabaseSelector.getListingAmenities(getListing_id()));
    // this.comments.addAll(DatabaseSelector.getListingComments(getListing_id()));
    // this.availabilities.addAll(DatabaseSelector.getListingAvailabilities(getListing_id()));
  }

  // TODO
  @Override
  public String toString() {
    String pop = (getTitle() + "\n" + getDescription() + "\n" + getListing_type() + "\n"
        + "Number of Bedrooms: " + getNum_bedrooms() + "\n" + "Number of Beds: " + getNum_beds()
        + "\n" + "Number of Bathrooms: " + getNum_bathrooms() + "\n" + getAddress().toString());
    return pop;
  }

  public void databaseDeleteListing() {
    DatabaseDeleter.deleteListing(this.listing_id);
  }

  /**
   * Returns true if added
   */
  public boolean addListingAddress(String unit, String street, String city, String state,
      String country, String zipCode, Double x, Double y) {
    // insert into database
    if (databaseInsertAddress(listing_id, unit, street, city, state, country, zipCode, x,
        y) == true) {
      this.address =
          (new ListingAddress(listing_id, unit, street, city, state, country, zipCode, x, y));
      return true;
    }
    return false;
  }

  /**
   * Returns true if added
   */
  public boolean databaseInsertAddress(int listing_id, String unit, String street, String city,
      String state, String country, String zipCode, Double x, Double y) {
    // insert info to database and if row number returned, then valid
    if (DatabaseInserter.insertAddress(listing_id, unit, street, city, state, country, zipCode, x,
        y) > 0) {
      System.out.println("Address Added!");
      return true;
    } else {
      System.out.println(">>>\nYour address was not added. Please try again.\n>>>");
      return false;
    }
  }


  // ** GETTERS/SETTERS **//

  public int getListing_id() {
    return listing_id;
  }

  public void setListing_id(int listing_id) {
    this.listing_id = listing_id;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public String getListing_type() {
    return listing_type;
  }

  public void setListing_type(String listing_type) {
    this.listing_type = listing_type;
  }

  public int getNum_bedrooms() {
    return num_bedrooms;
  }

  public void setNum_bedrooms(int num_bedrooms) {
    this.num_bedrooms = num_bedrooms;
  }

  public int getNum_beds() {
    return num_beds;
  }

  public void setNum_beds(int num_beds) {
    this.num_beds = num_beds;
  }

  public int getNum_bathrooms() {
    return num_bathrooms;
  }

  public void setNum_bathrooms(int num_bathrooms) {
    this.num_bathrooms = num_bathrooms;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ListingAddress getAddress() {
    return address;
  }

  public void setAddress(ListingAddress address) {
    this.address = address;
  }

  public Set<String> getAmenities() {
    return amenities;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public List<Availability> getAvailabilities() {
    return availabilities;
  }

//  public void addAmenities(Set<String> amenities2) {
//    // TODO Auto-generated method stub
//
//  }

}
