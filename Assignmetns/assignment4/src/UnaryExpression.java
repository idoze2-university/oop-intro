/**
 * The UnaryExpression class extends the BaseExpression class and implements an
 * expression which holds a single inner expression.
 */
class UnaryExpression extends BaseExpression {

  /**
   * Default Constructor.
   *
   * @param a inner expression a.
   */
  protected UnaryExpression(Expression a) {
    super(a);
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
    if (getA().getClass() == Num.class) {
      return new Num(getA().evaluate());
    }
    return this;
  }

}