import java.util.Scanner;

/**
 * Using a calculator to compute a result, and print it from main that will repeatedly read a line
 * the user types.
 * 
 * @author Rene Urias Jr
 * @version 1.2 of 17 September 2023
 */
public class InteractiveCalculator {
  private BFCalculator calculator; // Use a class field for the calculator

  public InteractiveCalculator() {
    calculator = new BFCalculator();
  }

  public static void main(String[] args) {
    InteractiveCalculator interactiveCalculator = new InteractiveCalculator();
    interactiveCalculator.run();
  }

  private void run() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Interactive Calculator");
    System.out.println("Enter expressions, store values, or 'QUIT' to exit");

    while (true) {
      System.out.print("> ");
      String input = scanner.nextLine().trim();

      if (input.equalsIgnoreCase("QUIT")) {
        break;
      } else if (input.startsWith("STORE")) {
        char register = input.charAt(6);
        calculator.store(register);
      } else {
        processInput(input);
      }
    }

    scanner.close();
  }

  private void processInput(String input) {
    try {
      BigFraction result = calculator.evaluate(input);
      System.out.println(result.toSimpleString());
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
} // class InteractiveCalculator{}
