import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Var class implements a Numeric value that can be evaluated as an
 * expression.
 */
public class Var implements Expression {
  private String label;

  public Var(String label) {
    this.label = label.toLowerCase();
  }

  public String getLabel() {
    return label;
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
    Expression ret = this;
    if (assignment == null || !assignment.containsKey(label)) {
      throw new Exception(label + " is unassigned in assignment.");
    }
    for (String var : assignment.keySet()) {
      ret = ret.assign(var, new Num(assignment.get(var)));
    }
    return ret.evaluate();
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
    return evaluate(null);
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
    if (var.toLowerCase() != label) {
      return this;
    } else {
      return expression;
    }
  }

  /**
   * @return Returns a list of the variables in the expression.
   */
  public List<String> getVariables() {
    ArrayList<String> a = new ArrayList<String>();
    a.add(getLabel());
    return a;
  }

  public Expression differentiate(String var) {
    if (var.toLowerCase() == label) {
      return new Num(1);
    } else {
      return new Num(0);
    }
  }

  public Expression simplify() {
    return this;
  }

  /**
   * @return Returns a nice string representation of the expression.
   */
  public String toString() {
    return label;
  }

}