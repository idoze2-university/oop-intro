/**
 * The Cos class extends the UnaryExpression class and implements a cos
 * operation expression Cos(a).
 */
class Cos extends UnaryExpression {

  /**
   * Default Constructor.
   *
   * @param a an Expression.
   **/
  public Cos(Expression a) {
    super(a);
  }

  /**
   * Overloading Constructor.
   *
   * @param a a String.
   **/
  public Cos(String a) {
    this(new Var(a));
  }

  /**
   * Overloading Constructor.
   *
   * @param a a String.
   **/
  public Cos(double a) {
    this(new Num(a));
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
    return Math.cos(getA().evaluate());
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
    Expression a = this.getA().assign(var, expression);
    return new Cos(a);
  }

  /**
   * @return Returns a nice string representation of the expression.
   */
  public String toString() {
    return "Cos(" + getA().toString() + ")";
  }

  /**
   * Returns the expression tree resulting from differentiating the current
   * expression relative to variable `var`.
   *
   * @param var Variable to differentiate by.
   * @return the expression result.
   */
  public Expression differentiate(String var) {
    return new Mult(new Neg(new Sin(getA())), getA().differentiate(var));
  }
}