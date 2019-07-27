package models;

import database.*;

public class CreditCard {

  private int userId;
  private int cardNum;
  private String cardType;
  private String expDate;

  /**
   * Only using variables referenced in the application
   * 
   * @param sin
   */
  public CreditCard(int userId, int cardNum, String cardType, String expDate) {
    this.userId = userId;
    this.cardNum = cardNum;
    this.cardType = cardType;
    this.expDate = expDate;
  }

  @Override
  public String toString() {
    return (getCardType() + " - " + getCardNum() + ": Expires " + getExpDate());
  }

  public void databaseDeleteCard() {
    DatabaseDeleter.deleteCard(this.userId, this.cardNum);
  }

  // ** GETTERS/SETTERS **//

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getCardNum() {
    return cardNum;
  }

  public void setCardNum(int cardNum) {
    this.cardNum = cardNum;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public String getExpDate() {
    return expDate;
  }

  public void setExpDate(String expDate) {
    this.expDate = expDate;
  }

}
