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
  // | Fields  |
  // +---------+

  BigFraction lastValue;                                                                   // Stores the last computed value
  Map<Character, BigFraction> registers;                                                   // Maps characters to BigFraction values for registers

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /*
   * Constructs a BFCalculator with initial values.
   */
  public BFCalculator() {
    lastValue = BigFraction.ZERO;     // Initialize lastValue with zero
    registers = new HashMap<>();     // Initialize the registers map
  } // BFCalculator()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /*
   * Evaluates the given expression and returns the result as a BigFraction
   */
  public BigFraction evaluate(String exp) {
    String[] parts = exp.split("\\s+");

    for (String part : parts) {
      if (isRegister(part)) {
        // if the part is a register name (e.g. 'a' or 'b'), update lastValue
        char register = part.charAt(0);
        lastValue = registers.getOrDefault(register, BigFraction.ZERO);
      } else {
        try {
          // if the part is not a register, parse it as a BigFraction and update lastValue
          lastValue = new BigFraction(part);
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Invalid expression " + exp);
        } // try{} . . . catch{}
      } // if{} . . . else{}
    } // for{}

    return lastValue;
  } // evaluate(String)

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