package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import database.DatabaseDeleter;
import database.DatabaseInserter;

public class Listing {

  private int listing_id;
  private int user_id;
  private String listing_type; // ('Apartment', 'House', 'Secondary Unit', 'Bed and Breakfast', 'Boutique Hotel') 
  private int num_bedrooms;
  private int num_beds;
  private int num_bathrooms;
  private String title;
  private String description;
  
  private final Set<String> amenities = new HashSet<>();

  private final List<Comment> comments = new ArrayList<>();
  private final List<Availability> availabilities = new ArrayList<>();
  
  /**
   * Only using variables referenced in the application
   * @param sin
   */
  public Listing(int listing_id, int user_id, String listing_type, int num_bedrooms, int num_beds, int num_bathrooms, String title, String description) {
    this.listing_id = listing_id;
    this.user_id = user_id;
    this.listing_type = listing_type;
    this.num_bedrooms = num_bedrooms;
    this.num_beds = num_beds;
    this.num_bathrooms = num_bathrooms;
    this.title = title;
    this.description = description;
  }

  @Override
  public String toString() {
    return ("");
  }
  
  public void databaseDeleteListing() {
    DatabaseDeleter.deleteListing(this.user_id, this.listing_id);
  }
  
  public void databaseInsertListing(int listing_id, int user_id, String listing_type, int num_bedrooms, int num_beds, int num_bathrooms, String title, String description) {
    DatabaseInserter.insertListing(listing_id, user_id, listing_type, num_bedrooms, num_beds, num_bathrooms, title, description);
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

  public Set<String> getAmenities() {
    return amenities;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public List<Availability> getAvailabilities() {
    return availabilities;
  }
}
