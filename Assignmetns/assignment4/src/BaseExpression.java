import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * The BaseExpression class implements the Expression interface and is the
 * foundation of an unary expression.
 */
class BaseExpression implements Expression {
  private Expression aField;

  /**
   * Default Constructor.
   *
   * @param a inner expression a.
   */
  protected BaseExpression(Expression a) {
    this.setA(a);
  }

  /**
   * @param a the a to set
   */
  protected void setA(Expression a) {
    this.aField = a;
  }

  /**
   * @return inner expression a.
   */
  protected Expression getA() {
    return aField;
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
    Expression orgA = getA();
    for (String var : getVariables()) {
      Double value = assignment.get(var);
      if (value != null) {
        setA(getA().assign(var, new Num(value)));
      } else {
        throw new Exception();
      }
    }
    double eval = evaluate();
    setA(orgA);
    return eval;
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
    return 0;
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
    setA(getA().assign(var, expression));
    return new BaseExpression(getA());
  }

  /**
   * @return Returns a list of the variables in the expression.
   */
  public List<String> getVariables() {
    ArrayList<String> vars = new ArrayList<String>();
    for (String var : getA().getVariables()) {
      if (!vars.contains(var)) {
        vars.add(var);
      }
    }
    return vars;
  }

  /**
   * Returns the expression tree resulting from differentiating the current
   * expression relative to variable `var`.
   *
   * @param var Variable to differentiate by.
   * @return the expression result.
   */
  public Expression differentiate(String var) {
    BaseExpression de = this;
    de.setA(de.getA().differentiate(var));
    return de;
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