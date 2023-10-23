import java.util.HashMap;
import java.util.Map;

/**
 * The primary workhorse of the BigFraction class.
 * 
 * @author Rene Urias Jr
 * @version 1.3 of 17 September 2023
 */
public class BFCalculator {
  // +---------+-------------------------------------------------------
  // | Fields |
  // +---------+

  BigFraction lastValue; // Stores the last computed value
  Map<Character, BigFraction> registers; // Maps characters to BigFraction values for registers

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /*
   * Constructs a BFCalculator with initial values.
   */
  public BFCalculator() {
    lastValue = BigFraction.ZERO; // Initialize lastValue with zero
    registers = new HashMap<>(); // Initialize the registers map
  } // BFCalculator()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /*
   * Handles the evaluation of expressions with multiple operations
   */
  private BigFraction evaluateMultiOperation(String[] parts) {
    BigFraction result = new BigFraction(parts[0]);

    for (int i = 1; i < parts.length; i += 2) {
      char operator = parts[i].charAt(0);
      BigFraction operand = new BigFraction(parts[i + 1]);

      switch (operator) {
        case '+':
          result = result.add(operand);
          break;
        case '-':
          result = result.subtract(operand);
          break;
        case '*':
          result = result.multiply(operand);
          break;
        case '/':
          result = result.divide(operand);
          break;
        default:
          throw new IllegalArgumentException("Invalid operator: " + operator);
      }
    }

    return result;
  }

  /*
   * Evaluates the given expression and returns the result as a BigFraction
   */
  public BigFraction evaluate(String exp) {
    String[] parts = exp.split("\\s+");

    if (parts.length == 1) {
      // Handle expressions with no operations
      return isRegister(parts[0]) ? registers.getOrDefault(parts[0].charAt(0), BigFraction.ZERO)
          : new BigFraction(parts[0]);
    } else if (parts.length == 3) {
      // Handle expressions with two fractions and one operation
      return evaluateBinaryOperation(parts);
    } else {
      // Handle expressions with multiple operations
      return evaluateMultiOperation(parts);
    }
  }

  private BigFraction evaluateBinaryOperation(String[] parts) {
    BigFraction operand1 = new BigFraction(parts[0]);
    char operator = parts[1].charAt(0);
    BigFraction operand2 = new BigFraction(parts[2]);

    switch (operator) {
      case '+':
        return operand1.add(operand2);
      case '-':
        return operand1.subtract(operand2);
      case '*':
        return operand1.multiply(operand2);
      case '/':
        return operand1.divide(operand2);
      default:
        throw new IllegalArgumentException("Invalid operator: " + operator);
    }
  }

  /*
   * Stores the current lastValue in a specified register
   */
  public void store(char register) {
    registers.put(register, lastValue);
  } // store(char)

  public boolean isRegister(String part) {
    // Checks if a string part represents a register (single character)
    return part.length() == 1 && Character.isLetter(part.charAt(0));
  } // isRegister(String)
} // class BFCalculator{}
