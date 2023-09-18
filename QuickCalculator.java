/**
 * Take the expressions from the command line and then print out the results
 * 
 * @author Rene Urias Jr
 * @version 1.2 of 17 September 2023
 */
public class QuickCalculator {
  public static void main(String[] args) {
    // Create a BFCalculator instance to perform calculations
    BFCalculator calculator = new BFCalculator();
    String expression = "";

    // Iterate through each argument provided as expressions
    for (int i = 0; i < args.length; i++) {
      expression = args[i]; // Assign the current argument to expression

      try {
        // Split the expression into parts based on whitespace
        String[] parts = expression.split("\\s+");
        // Initialize the result with the first part of the expression
        BigFraction result = calculator.evaluate(parts[0]);

        // Process the rest of the expression in pairs (operator and operand)
        for (int j = 1; i < parts.length; j += 2) {
          char operator = parts[i].charAt(0); // Extract the operator character
          BigFraction operand = calculator.evaluate(parts[i + 1]); // Evaluate the operand

          // Perform the appropriate arithmetic operation based on the operator
          if (operator == '+') {
            result = result.add(operand);
          } else if (operator =='-') {
            result = result.subtract(operand);
          } else if (operator =='*') {
            result = result.multiply(operand);
          } else if (operator =='/') {
            result = result.divide(operand);
          } // if{} . . . else if{} . . . else if{} . . . else if{}
        } // for{}

        // Display the formatted result along with the original expression
        System.out.println(expression + " = " + result);
      } catch (IllegalArgumentException e) {
        // Handle and display errors for invalid expressions
        System.out.println(expression + " = Error: " + e.getMessage());
      } // try{} . . . catch{}
    } // for{}
  } // main(String)
} // class QuickCalculator{}