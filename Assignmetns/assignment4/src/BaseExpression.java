import java.util.List;
import java.util.ArrayList;
import java.util.Map;

class BaseExpression implements Expression {
  protected Expression a;

  public BaseExpression(Expression a) {
    this.a = a;
  }

  public Expression getA() {
    return a;
  }

  public BaseExpression(double a) {
    this(new Num(a));
  }

  public BaseExpression(String a) {
    this(new Var(a));
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
    for (String var : getVariables()) {
      Double value = assignment.get(var);
      if (value != null) {
        a = a.assign(var, new Num(value));
      } else {
        throw new Exception();
      }
    }
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
    a = a.assign(var, expression);
    return new BaseExpression(a);
  }

  /**
   * @return Returns a list of the variables in the expression.
   */
  public List<String> getVariables() {
    ArrayList<String> vars = new ArrayList<String>();
    vars.addAll(a.getVariables());
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
    de.a = de.a.differentiate(var);
    return de;
  }

  public Expression simplify() throws Exception {
    return this;
  }
}