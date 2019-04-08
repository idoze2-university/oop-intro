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

  public double evaluate() throws Exception {
    return Math.sin(getA().evaluate());
  }
  public Expression assign(String var, Expression expression) {
    Expression a = getA().assign(var, expression);
    return new Sin(a);
  }
  public String toString() {
    return "Sin(" + getA().toString() + ")";
  }

  public Expression differentiate(String var) {
    return new Mult(new Cos(getA()), getA().differentiate(var));
  }

}