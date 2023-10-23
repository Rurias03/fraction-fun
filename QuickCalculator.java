/**
 * Take the expressions from the command line and then print out the results
 * 
 * @author Rene Urias Jr
 * @version 1.2 of 17 September 2023
 */
public class QuickCalculator {
  private static BFCalculator calculator; // Use a class field for the calculator instance

  public static void main(String[] args) {
    calculator = new BFCalculator();
    QuickCalculator quickCalculator = new QuickCalculator();
    quickCalculator.calculate(args);
  }

  private void calculate(String[] args) {
    for (String expression : args) {
      try {
        BigFraction result = calculator.evaluate(expression);
        System.out.println(expression + " = " + result.toSimpleString());
      } catch (IllegalArgumentException e) {
        System.out.println(expression + " = Error: " + e.getMessage());
      }
    }
  }
} // class QuickCalculator{}
