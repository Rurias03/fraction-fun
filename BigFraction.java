import java.math.BigInteger;

/**
 * A simple implementation of Fractions.
 * 
 * @author Rene Urias Jr
 * @version 1.4 of 17 September 2023
 */
public class BigFraction {
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  BigInteger num; // Numerator of the fraction
  BigInteger denom; // Denominator of the fraction
  public static final BigFraction ZERO = new BigFraction(BigInteger.ZERO, BigInteger.ONE); // a
                                                                                           // constant
                                                                                           // representing
                                                                                           // zero
                                                                                           // as a
                                                                                           // BigFraction
  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /*
   * Constructs a BigFraction object with a specified numerator and denominator
   */
  public BigFraction(BigInteger num, BigInteger denom) {
    // Constructor for negative numbers
    if (denom.equals(BigInteger.ZERO)) {
      throw new IllegalArgumentException("Denominator cannot be zero.");
    }

    this.num = isNegative ? num.negate() : num;
    this.denom = denom;
    simplify();
  } // BigFraction(BigInteger, BigInteger)

  /*
   * Constructs a BigFraction with integer numerator and denominator
   */
  public BigFraction(int num, int denom) {
    this(BigInteger.valueOf(num), BigInteger.valueOf(denom));
  } // BigFraction(int, int)

  /*
   * Constructs a BigFraction object by parsing a string representation of a fraction
   */
  public BigFraction(String str) {
    String[] stringArr = str.split("/");

    if (stringArr.length != 2) {
      throw new IllegalArgumentException("Invalid fraction format: " + str);
    } // if{}

    this.num = new BigInteger(stringArr[0]);
    this.denom = new BigInteger(stringArr[1]);
    boolean isNegative = str.startsWith("-");
    this.num =
        isNegative ? new BigInteger(stringArr[0].substring(1)) : new BigInteger(stringArr[0]);
    this.denom = new BigInteger(stringArr[1]);
    if (denom.equals(BigInteger.ZERO)) {
      throw new IllegalArgumentException("Denominator cannot be zero.");
    }
    simplify();
  } // BigFraction(String)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /*
   * Returns the double representation of the BigFraction
   */
  public double doubleValue() {
    return num.doubleValue() / denom.doubleValue();
  } // doubleValue()

  /*
   * Adds another BigFraction to this BigFraction and returns the result
   */
  public BigFraction add(BigFraction addMe) {
    BigInteger resultNumerator = num.multiply(addMe.denom).add(addMe.num.multiply(denom));
    BigInteger resultDenomninator = denom.multiply(addMe.denom);
    BigFraction result = new BigFraction(resultNumerator, resultDenomninator);
    result.simplify(); // Ensure the result is in simplest form
    return result;
  } // add(BigFraction)

  /*
   * Subtracts another BigFraction from this BigFraction and returns the result
   */
  public BigFraction subtract(BigFraction subtractMe) {
    BigInteger resultNumerator =
        num.multiply(subtractMe.denom).subtract(subtractMe.num.multiply(denom));
    BigInteger resultDenominator = denom.multiply(subtractMe.denom);
    rBigFraction result = new BigFraction(resultNumerator, resultDenominator);
    result.simplify(); // Ensure the result is in simplest form
    return result;
  } // subtract(BigFraction)

  /*
   * Multiplies this BigFraction by another BigFraction and returns the result
   */
  public BigFraction multiply(BigFraction one) {
    BigInteger resultNumerator = num.multiply(one.num);
    BigInteger resultDenominator = denom.multiply(one.denom);
    BigFraction result = new BigFraction(resultNumerator, resultDenominator);
    result.simplify(); // Ensure the result is in simplest form
    return result;
  } // multiply(BigFraction

  /*
   * Divides this BigFraction by antoerh BigFraction and returns the result
   */
  public BigFraction divide(BigFraction divisor) {
    if (divisor.num.equals(BigInteger.ZERO)) {
      throw new ArithmeticException("Division by zero.");
    } // if{}

    BigInteger resultNumerator = num.multiply(divisor.denom);
    BigInteger resultDenominator = denom.multiply(divisor.num);
    BigFraction result = new BigFraction(resultNumerator, resultDenominator);
    result.simplify(); // Ensure the result is in simplest form
    return result;
  } // divide(BigFraction)

  /*
   * Gets the denominator of this BigFraction
   */
  public BigInteger denominator() {
    return denom;
  } // denominator()

  /*
   * Gets the numerator of this BigFraction
   */
  public BigInteger numerator() {
    return num;
  } // numerator()

  /*
   * Returns a string representation of the BigFraction
   */
  @Override
  public String toString() {
    if (denom.equals(BigInteger.ONE)) {
      return num.toString();
    } else {
      return num + "/" + denom;
    }
  } // toString()

  /*
   * Returns a string representation of the BigFraction, handling whole numbers
   */
  public String toSimpleString() {
    if (num.equals(BigInteger.ZERO)) {
      return "0";
    } else if (denom.equals(BigInteger.ONE)) {
      return num.toString();
    } else {
      return num + "/" + denom;
    }
  }

  /*
   * Simplifies the BigFraction to its simplest form
   */
  private void simplify() {
    BigInteger gcd = num.gcd(denom);
    num = num.divide(gcd);
    denom = denom.divide(gcd);
    if (denom.compareTo(BigInteger.ZERO) < 0) {
      num = num.negate();
      denom = denom.negate();
    } // if{}
  } // simplify()
} // class BigFraction{}
