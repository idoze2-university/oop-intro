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

  public double evaluate() throws Exception {
    return -1 * (a.evaluate());
  }

  public String toString() {
    return "Neg(" + a.toString() + ")";
  }

  public Expression assign(String var, Expression expression) {
    Expression a = this.a.assign(var, expression);
    return new Neg(a);
  }

  public Expression differentiate(String var) {
    return new Neg(a.differentiate(var));
  }

}