import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Num class implements a Numeric value that can be evaluated as an
 * expression.
 */
public class Num implements Expression {
  private double value;

  /**
   * Default constructor.
   *
   * @param value The value which the class represents.
   */
  public Num(double value) {
    this.value = value;
  }

  /**
   * Evaluate the expression using the variable values provided in the assignment,
   * and return the result.
   *
   * @param assignment set of variables and their values.
   * @return result of the evaluated expression.
   * @throws Exception If the expression contains a variable which is not in the
   *                   assignment, an exception is thrown.
   */
  public double evaluate(Map<String, Double> assignment) throws Exception {
    return evaluate();
  }

  /**
   * A convenience method. Like the `evaluate(assignment)` method above, but uses
   * an empty assignment.
   *
   * @return result of the evaluated expression.
   * @throws Exception If the expression contains a variable which is not in the
   *                   assignment, an exception is thrown.
   */
  public double evaluate() throws Exception {
    return value;
  }

  /**
   * @return Returns a list of the variables in the expression.
   */
  public List<String> getVariables() {
    return new ArrayList<String>();
  }

  /**
   * @return Returns a nice string representation of the expression.
   */
  public String toString() {
    return String.valueOf(value);
  }

  /**
   * Returns a new expression in which all occurrences of the variable var are
   * replaced with the provided expression (Does not modify the current
   * expression).
   *
   * @param var        the variable which the expression should be assigned to.
   * @param expression expression to assign to var.
   * @return a new expression where all occurences of var are replaced with the
   *         provided expression.
   */
  public Expression assign(String var, Expression expression) {
    return this;
  }

  /**
   * Returns the expression tree resulting from differentiating the current
   * expression relative to variable `var`.
   *
   * @param var Variable to differentiate by.
   * @return the expression result.
   */
  public Expression differentiate(String var) {
    return new Num(0);
  }

  /**
   * Simplifies the current expression and returns its simplified value.
   *
   * @return a simplified version of the current expression
   * @throws Exception If the expression contains a variable which is not in the
   *                   assignment, an exception is thrown.
   */
  public Expression simplify() throws Exception {
    return this;
  }
}