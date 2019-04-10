import java.util.List;
import java.util.Map;

/**
 * The Expression interface provides a basic protocol for an evaluatable
 * expression.
 */
public interface Expression {
  /**
   * Evaluate the expression using the variable values provided in the assignment,
   * and return the result.
   *
   * @param assignment set of variables and their values.
   * @return result of the evaluated expression.
   * @throws Exception If the expression contains a variable which is not in the
   *                   assignment, an exception is thrown.
   */
  double evaluate(Map<String, Double> assignment) throws Exception;

  /**
   * A convenience method. Like the `evaluate(assignment)` method above, but uses
   * an empty assignment.
   *
   * @return result of the evaluated expression.
   * @throws Exception If the expression contains a variable which is not in the
   *                   assignment, an exception is thrown.
   */
  double evaluate() throws Exception;

  /**
   * @return Returns a list of the variables in the expression.
   */
  List<String> getVariables();

  /**
   * @return Returns a nice string representation of the expression.
   */
  String toString();

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
  Expression assign(String var, Expression expression);

  /**
   * Returns the expression tree resulting from differentiating the current
   * expression relative to variable `var`.
   *
   * @param var Variable to differentiate by.
   * @return the expression result.
   */
  Expression differentiate(String var);

  /**
   * Simplifies the current expression and returns its simplified value.
   *
   * @return a simplified version of the current expression
   * @throws Exception If the expression contains a variable which is not in the
   *                   assignment, an exception is thrown.
   */
  Expression simplify() throws Exception;
 }
