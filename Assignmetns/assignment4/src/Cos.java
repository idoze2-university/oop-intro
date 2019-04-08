class Cos extends UnaryExpression implements Expression {

  public Cos(Expression a) {
    super(a);
  }

  public Cos(String a) {
    this(new Var(a));
  }

  public Cos(double a) {
    this(new Num(a));
  }

  public double evaluate() throws Exception {
    return Math.cos(getA().evaluate());
  }

  public Expression assign(String var, Expression expression) {
    Expression a = this.a.assign(var, expression);
    return new Cos(getA());
  }

  public String toString() {
    return "Cos(" + getA().toString() + ")";
  }

  public Expression differentiate(String var) {
    return new Mult(new Neg(new Sin(a)), a.differentiate(var));
  }
}