class Plus extends BinaryExpression {

  public Plus(Expression a, Expression b) {
    super(a, b);
  }

  public Plus(Expression a, double b) {
    this(a, new Num(b));
  }

  public Plus(Expression a, String b) {
    this(a, new Var(b));
  }

  public Plus(double a, Expression b) {
    this(new Num(a), b);
  }

  public Plus(double a, double b) {
    this(new Num(a), new Num(b));
  }

  public Plus(double a, String b) {
    this(new Num(a), new Var(b));
  }

  public Plus(String a, Expression b) {
    this(new Var(a), b);
  }

  public Plus(String a, double b) {
    this(new Var(a), new Num(b));
  }

  public Plus(String a, String b) {
    this(new Var(a), new Var(b));
  }

  public double evaluate() throws Exception {
    return a.evaluate() + b.evaluate();
  }

  public Expression assign(String var, Expression expression) {
    Expression a = this.a.assign(var, expression);
    Expression b = this.b.assign(var, expression);
    return new Plus(a, b);
  }

  public String toString() {
    return "(" + a.toString() + " + " + b.toString() + ")";
  }

  public Expression differentiate(String var) {
    return new Plus(a.differentiate(var), b.differentiate(var));
  }

  public Expression simplify() throws Exception {
    a = a.simplify();
    b = b.simplify();
    if (a.getClass() == Num.class) {
      if (a.evaluate() == 0) {
        return b;
      } else if (b.getClass() == Num.class) {
        return new Num(evaluate());
      }
    }
    if (b.getClass() == Num.class) {
      if (b.evaluate() == 0) {
        return a;
      }
    }
    return new Mult(a, b);
  }

}