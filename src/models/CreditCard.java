package models;

import database.*;

public class CreditCard {

  private int user_id;
  private int card_num;
  private String card_type;
  private String exp_date;

  /**
   * Only using variables referenced in the application
   * 
   * @param sin
   */
  public CreditCard(int user_id, int card_num, String card_type, String exp_date) {
    this.user_id = user_id;
    this.card_num = card_num;
    this.card_type = card_type;
    this.exp_date = exp_date;
  }

  @Override
  public String toString() {
    return (getCard_type() + " - " + getCard_num() + ": Expires " + getExp_date());
  }

  public void databaseDeleteCard() {
    DatabaseDeleter.deleteCard(this.user_id, this.card_num);
  }

  // ** GETTERS/SETTERS **//

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public int getCard_num() {
    return card_num;
  }

  public void setCard_num(int card_num) {
    this.card_num = card_num;
  }

  public String getCard_type() {
    return card_type;
  }

  public void setCard_type(String card_type) {
    this.card_type = card_type;
  }

  public String getExp_date() {
    return exp_date;
  }

  public void setExp_date(String exp_date) {
    this.exp_date = exp_date;
  }

}
