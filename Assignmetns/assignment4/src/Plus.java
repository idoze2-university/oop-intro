/**
 * The Plus class extends the BinaryExpression class and implements a addition
 * expression a+b.
 */
class Plus extends BinaryExpression {

  /**
   * Default Constructor.
   *
   * @param a an Expression.
   * @param b an Expression.
   */
  public Plus(Expression a, Expression b) {
    super(a, b);
  }

  /**
   * An overloading constructor.
   *
   * @param a an Expression.
   * @param b a double.
   */
  public Plus(Expression a, double b) {
    this(a, new Num(b));
  }

  /**
   * An overloading constructor.
   *
   * @param a an Expression.
   * @param b a String.
   */
  public Plus(Expression a, String b) {
    this(a, new Var(b));
  }

  /**
   * An overloading constructor.
   *
   * @param a a double.
   * @param b an Expression.
   */
  public Plus(double a, Expression b) {
    this(new Num(a), b);
  }

  /**
   * An overloading constructor.
   *
   * @param a a double.
   * @param b a double.
   */
  public Plus(double a, double b) {
    this(new Num(a), new Num(b));
  }

  /**
   * An overloading constructor.
   *
   * @param a a double.
   * @param b a String.
   */
  public Plus(double a, String b) {
    this(new Num(a), new Var(b));
  }

  /**
   * An overloading constructor.
   *
   * @param a a String.
   * @param b an Expression.
   */
  public Plus(String a, Expression b) {
    this(new Var(a), b);
  }

  /**
   * An overloading constructor.
   *
   * @param a a String.
   * @param b a double.
   */
  public Plus(String a, double b) {
    this(new Var(a), new Num(b));
  }

  /**
   * An overloading constructor.
   *
   * @param a a String.
   * @param b a String.
   */
  public Plus(String a, String b) {
    this(new Var(a), new Var(b));
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
    return getA().evaluate() + getB().evaluate();
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
    Expression na = this.getA().assign(var, expression);
    Expression nb = this.getB().assign(var, expression);
    return new Plus(na, nb);
  }

  /**
   * @return Returns a nice string representation of the expression.
   */
  public String toString() {
    return "(" + getA().toString() + " + " + getB().toString() + ")";
  }

  /**
   * Returns the expression tree resulting from differentiating the current
   * expression relative to variable `var`.
   *
   * @param var Variable to differentiate by.
   * @return the expression result.
   */
  public Expression differentiate(String var) {
    return new Plus(getA().differentiate(var), getB().differentiate(var));
  }

  /**
   * Simplifies the current expression and returns its simplified value.
   *
   * @return a simplified version of the current expression
   * @throws Exception If the expression contains a variable which is not in the
   *                   assignment, an exception is thrown.
   */
  public Expression simplify() throws Exception {
    setA(getA().simplify());
    setB(getB().simplify());
    if (getA().getClass() == Num.class) {
      if (getA().evaluate() == 0) {
        return getB();
      } else if (getB().getClass() == Num.class) {
        return new Num(evaluate());
      }
    }
    if (getB().getClass() == Num.class) {
      if (getB().evaluate() == 0) {
        return getA();
      }
    }
    return new Plus(getA(), getB());
  }

}