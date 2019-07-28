package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import database.DatabaseDeleter;
import database.DatabaseInserter;
import database.DatabaseSelector;

public class Listing {

  private int listingId;
  private int userId;
  // ('Apartment', 'House', 'Secondary Unit', 'Bed and Breakfast', 'Boutique Hotel')
  private String listingType;
  private int numBedrooms;
  private int numBeds;
  private int numBathrooms;
  private String title;
  private String description;
  private ListingAddress address;

  private final Set<String> amenities = new HashSet<>();
  private final List<ListingComment> comments = new ArrayList<>();
  private final List<Availability> availabilities = new ArrayList<>();
  private final List<Booking> bookings = new ArrayList<>();

  /**
   * Only using variables referenced in the application
   * 
   * @param sin
   */
  public Listing(int listingId, int userId, String listingType, int numBedrooms, int numBeds,
      int numBathrooms, String title, String description) {
    this.listingId = listingId;
    this.userId = userId;
    this.listingType = listingType;
    this.numBedrooms = numBedrooms;
    this.numBeds = numBeds;
    this.numBathrooms = numBathrooms;
    this.title = title;
    this.description = description;

    this.amenities.addAll(DatabaseSelector.getListingAmenities(getListingId()));
    this.comments.addAll(DatabaseSelector.getListingComments(getListingId()));
    this.availabilities.addAll(DatabaseSelector.getListingAvailabilities(getListingId()));
    this.bookings.addAll(DatabaseSelector.getListingBookings(getUserId(), getListingId()));
  }

  public String toStringBasic() {
    String pop = (getTitle() + "\n" + getDescription() + "\n" + getAddress().toString());
    return pop;
  }

  @Override
  public String toString() {
    return (getTitle() + "\n" + getDescription() + "\n" + getListingType() + "\n"
        + "Number of Bedrooms: " + getNumBedrooms() + "\n" + "Number of Beds: " + getNumBeds()
        + "\n" + "Number of Bathrooms: " + getNumBathrooms() + "\n" + getAmenities().toString()
        + "\n" + getAddress().toString());
  }

  public void databaseDeleteListing() {
    DatabaseDeleter.deleteListing(this.listingId);
  }


  public void deleteAvailabilityNum(int availabilityId) {
    // delete from database
    this.availabilities.get(availabilityId).databaseDeleteAvailability();
    // delete from object
    this.availabilities.remove(availabilityId);

  }

  public void cancelBooking(int bookingId) {
    // database
    this.bookings.get(bookingId).databaseCancelBooking("Cancelled by Host", LocalDate.now().toString());
  }

  /**
   * Returns true if added
   */
  public boolean addListingAmenities(Set<String> amenities) {
    // insert into database
    if (databaseInsertListingAmenities(this.getListingId(), amenities) == true) {
      this.amenities.addAll(amenities);
      return true;
    }
    return false;
  }

  /**
   * Returns true if added
   */
  public boolean databaseInsertListingAmenities(int listingId, Set<String> amenities) {
    // insert info to database and if row number returned, then valid
    if (DatabaseInserter.insertListingAmenities(this.getListingId(), amenities) > 0) {
      System.out.println("amenities Added!");
      return true;
    } else {
      System.out.println(">>>\nYour amenities were not added. Please try again.\n>>>");
      return false;
    }
  }

  /**
   * Returns true if added
   */
  public boolean addListingAddress(String unit, String street, String city, String state,
      String country, String zipCode, Double x, Double y) {
    // insert into database
    if (databaseInsertAddress(this.getListingId(), unit, street, city, state, country, zipCode, x,
        y) == true) {
      this.address = (new ListingAddress(this.getListingId(), unit, street, city, state, country,
          zipCode, x, y));
      return true;
    }
    return false;
  }

  /**
   * Returns true if added
   */
  public boolean addListingAvailability(String listingUse, String startDate, String endDate,
      Double price, boolean available) {
    // insert into database
    int availability_id =
        databaseInsertListingAvailability(listingUse, startDate, endDate, price, available, getListingId());

    // add to user object
    if (availability_id > 0) {
      this.availabilities
          .add(new Availability(availability_id, listingUse, startDate, endDate, price, available, getListingId()));
      return true;
    }
    return false;
  }

  /**
   * Returns listing_id if added
   */
  private int databaseInsertListingAvailability(String listingUse, String startDate, String endDate,
      Double price, boolean available, int listing_id) {
    int availability_id = 0;
    // insert info to database and if row number returned, then valid
    if ((availability_id = DatabaseInserter.insertListingAvailability(listingUse, startDate, endDate, price, available, listing_id)) > 0) {
      System.out.println("Availability Added!");
    } else {
      System.out.println(">>> \nYour availability was not added. Please try again.\n>>>");
    }
    return availability_id;
  }

  /**
   * Returns true if added
   */
  public boolean databaseInsertAddress(int listingId, String unit, String street, String city,
      String state, String country, String zipCode, Double x, Double y) {
    // insert info to database and if row number returned, then valid
    if (DatabaseInserter.insertAddress(listingId, unit, street, city, state, country, zipCode, x,
        y) > 0) {
      System.out.println("Address Added!");
      return true;
    } else {
      System.out.println(">>>\nYour address was not added. Please try again.\n>>>");
      return false;
    }
  }


  // ** GETTERS/SETTERS **//

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

  public String getListingType() {
    return listingType;
  }

  public void setListingType(String listingType) {
    this.listingType = listingType;
  }

  public int getNumBedrooms() {
    return numBedrooms;
  }

  public void setNumBedrooms(int numBedrooms) {
    this.numBedrooms = numBedrooms;
  }

  public int getNumBeds() {
    return numBeds;
  }

  public void setNumBeds(int numBeds) {
    this.numBeds = numBeds;
  }

  public int getNumBathrooms() {
    return numBathrooms;
  }

  public void setNumBathrooms(int numBathrooms) {
    this.numBathrooms = numBathrooms;
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

  public List<ListingComment> getComments() {
    return comments;
  }

  public List<Availability> getAvailabilities() {
    return availabilities;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public int getNumBookings() {
    return getBookings().size();
  }

  public int getNumAvailability() {
    return getAvailabilities().size();
  }

  public String getSuggestedPrice() {
    // loop through user.getAmenityPricing()
    return null;
  }

}
