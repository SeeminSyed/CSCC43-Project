package pages;

public class CustomExceptions extends Exception {

  /**
   * Serial Id for custom exceptions
   */
  private static final long serialVersionUID = 5406713859613806402L;

}

class InvalidFormException extends CustomExceptions {

  /**
   * Serial Id for log in exceptions
   */
  private static final long serialVersionUID = -6267506190276041358L;
}

class EmptyFormException extends CustomExceptions {

  /**
   * Serial Id for empty forms.
   */
  private static final long serialVersionUID = -7965299483890839567L;
  
}