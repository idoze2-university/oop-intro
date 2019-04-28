class Neg extends UnaryExpression {

  public Neg(Expression a) {
    super(a);
  }

  public Neg(String a) {
    this(new Var(a));
  }

  public Neg(double a) {
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
    return -1 * (a.evaluate());
  }

  public String toString() {
    return "Neg(" + a.toString() + ")";
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
    return new Neg(a);
  }

  public Expression differentiate(String var) {
    return new Neg(a.differentiate(var));
  }

}