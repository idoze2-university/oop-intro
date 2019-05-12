import java.util.Map;
import java.util.TreeMap;

/**
 * The ExpressionTest Class implements a static operation of testing the
 * Expression interface and it's child classes.
 */
public class ExpressionTest {
  /**
   * Main method.
   *
   * @param args CLI arguments.
   * @throws Exception Exception if an evaluation is preformed incorrectly.
   */
  public static void main(String[] args) throws Exception {
    Expression e1 = new Mult(2, "x");
    Expression e2 = new Sin(new Mult(4, "y"));
    Expression e3 = new Pow("e", "x");
    Expression e = new Plus(e1, new Plus(e2, e3));
    System.out.println(e.toString());
    Map<String, Double> assignment = new TreeMap<String, Double>();
    assignment.put("x", 2.0);
    assignment.put("y", 0.25);
    assignment.put("e", 2.71);
    System.out.println(e.evaluate(assignment));
    Expression de = e.differentiate("x");
    System.out.println(de.toString());
    System.out.println(de.evaluate(assignment));
    System.out.println(de.simplify().toString());

  }
}