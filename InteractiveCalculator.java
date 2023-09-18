import java.util.Scanner;

/**
 * Using a calculator to compute a result, and print it from main that will repeatedly 
 * read a line the user types.
 * 
 * @author Rene Urias Jr
 * @version 1.2 of 17 September 2023
 */
public class InteractiveCalculator {
  public static void main(String[] args) {
    BFCalculator calculator = new BFCalculator(); // Creates a calculator instance
    Scanner scanner = new Scanner(System.in); // Create a scanner for user input

    System.out.println("Interactive Calculator");
    System.out.println("Enter expressions, store values, or 'QUIT' to exit");

    while (true) {
      System.out.print("> ");
      String input = scanner.nextLine().trim(); // Read user input and trim whitespace

      if (input.equalsIgnoreCase("QUIT")) {
        // Exit the calculator when 'QUIT' is entered
        break;
      } else if (input.startsWith("STORE")) {
        //Handle store commands (e.g., "STORE a")
        char register = input.charAt(6); // Extract the register name from the input 
        calculator.store(register); // Store the current result in the register
      } else {
        try {
          // Evaluate the expression and print the result
          BigFraction result = calculator.evaluate(input);
          System.out.println(result);
        } catch (IllegalArgumentException e) {
          // Handle invalid expressions with error messages
          System.out.println("Error: " + e.getMessage());
        } // try{} . . . catch{}
      } // if{} . . . else if{} . . . else{}
    } // while{}

    scanner.close(); // Close the scanner to release resources
  } // main(String)
} // class InteractiveCalculator{}