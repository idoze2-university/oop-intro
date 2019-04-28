class Sin extends UnaryExpression {

  public Sin(Expression a) {
    super(a);
  }

  public Sin(String a) {
    this(new Var(a));
  }

  public Sin(double a) {
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
    return Math.sin(getA().evaluate());
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
    Expression a = getA().assign(var, expression);
    return new Sin(a);
  }
  /**
   * @return Returns a nice string representation of the expression.
   */
  public String toString() {
    return "Sin(" + getA().toString() + ")";
  }

  public Expression differentiate(String var) {
    return new Mult(new Cos(getA()), getA().differentiate(var));
  }

}