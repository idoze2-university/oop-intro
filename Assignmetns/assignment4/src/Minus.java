class Minus extends BinaryExpression {

  /**
   * 
   * @param a
   * @param b
   */
  public Minus(Expression a, Expression b) {
    super(a, b);
  }

  public Minus(Expression a, double b) {
    this(a, new Num(b));
  }

  public Minus(Expression a, String b) {
    this(a, new Var(b));
  }

  public Minus(double a, Expression b) {
    this(new Num(a), b);
  }

  public Minus(double a, double b) {
    this(new Num(a), new Num(b));
  }

  public Minus(double a, String b) {
    this(new Num(a), new Var(b));
  }

  public Minus(String a, Expression b) {
    this(new Var(a), b);
  }

  public Minus(String a, double b) {
    this(new Var(a), new Num(b));
  }

  public Minus(String a, String b) {
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
    return a.evaluate() - b.evaluate();
  }
/**
   * @return Returns a nice string representation of the expression.
   */
  public String toString() {
    return "(" + a.toString() + " - " + b.toString() + ")";
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
    Expression a = this.a.assign(var, expression);
    Expression b = this.b.assign(var, expression);
    return new Minus(a, b);
  }

  public Expression simplify() throws Exception {
    a = a.simplify();
    b = b.simplify();
    if (a.getClass() == Num.class) {
      if (a.evaluate() == 0) {
        return new Neg(b);
      }
      if (b.getClass() == Num.class) {
        return new Num(evaluate());
      }
    }
    if (b.getClass() == Num.class) {
      if (b.evaluate() == 0) {
        return a;
      }
    }
    if (a.toString() == b.toString()) {
      return new Num(0);
    }
    return new Minus(a, b);
  }

  public Expression differentiate(String var) {
    return new Minus(a.differentiate(var), b.differentiate(var));
  }
}