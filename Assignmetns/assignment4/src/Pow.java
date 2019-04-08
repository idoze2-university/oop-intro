class Pow extends BinaryExpression {

  public Pow(Expression a, Expression b) {
    super(a, b);
  }

  public Pow(Expression a, double b) {
    this(a, new Num(b));
  }

  public Pow(Expression a, String b) {
    this(a, new Var(b));
  }

  public Pow(double a, Expression b) {
    this(new Num(a), b);
  }

  public Pow(double a, double b) {
    this(new Num(a), new Num(b));
  }

  public Pow(double a, String b) {
    this(new Num(a), new Var(b));
  }

  public Pow(String a, Expression b) {
    this(new Var(a), b);
  }

  public Pow(String a, double b) {
    this(new Var(a), new Num(b));
  }

  public Pow(String a, String b) {
    this(new Var(a), new Var(b));
  }

  public double evaluate() throws Exception {
    return Math.pow(a.evaluate(), b.evaluate());
  }

  public Expression assign(String var, Expression expression) {
    Expression a = this.a.assign(var, expression);
    Expression b = this.b.assign(var, expression);
    return new Pow(a, b);
  }

  public String toString() {
    return "(" + a.toString() + "^" + b.toString() + ")";
  }

  public Expression simplify() throws Exception {
    a = a.simplify();
    b = b.simplify();
    if (a.getClass() == Num.class) {
      if (a.evaluate() == 1) {
        return new Num(1);
      }
      if (b.getClass() == Num.class) {
        return new Num(evaluate());
      }
    }
    if (b.getClass() == Num.class) {
      if (b.evaluate() == 0) {
        return new Num(1);
      }
    }
    return new Pow(a, b);
  }

  public Expression differentiate(String var) {
    return new Mult(this,
        new Plus(new Mult(a.differentiate(var), new Div(b, a)), new Mult(b.differentiate(var), new Log("e", a))))
            .assign("e", new Num(2.71828));
  }

}