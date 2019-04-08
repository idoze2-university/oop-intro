class Mult extends BinaryExpression {

  public Mult(Expression a, Expression b) {
    super(a, b);
  }

  public Mult(Expression a, double b) {
    this(a, new Num(b));
  }

  public Mult(Expression a, String b) {
    this(a, new Var(b));
  }

  public Mult(double a, Expression b) {
    this(new Num(a), b);
  }

  public Mult(double a, double b) {
    this(new Num(a), new Num(b));
  }

  public Mult(double a, String b) {
    this(new Num(a), new Var(b));
  }

  public Mult(String a, Expression b) {
    this(new Var(a), b);
  }

  public Mult(String a, double b) {
    this(new Var(a), new Num(b));
  }

  public Mult(String a, String b) {
    this(new Var(a), new Var(b));
  }

  public double evaluate() throws Exception {
    return a.evaluate() * b.evaluate();
  }

  public Expression assign(String var, Expression expression) {
    Expression a = this.a.assign(var, expression);
    Expression b = this.b.assign(var, expression);
    return new Mult(a, b);
  }

  public Expression differentiate(String var) {
    return new Plus(new Mult(a.differentiate(var), b), new Mult(a, b.differentiate(var)));
  }

  public Expression simplify() throws Exception {
    a = a.simplify();
    b = b.simplify();
    if (a.getClass() == Num.class) {
      if (a.evaluate() == 1) {
        return b;
      } else if (a.evaluate() == 0) {
        return new Num(0);
      } else if (b.getClass() == Num.class) {
        return new Num(evaluate());
      }
    }
    if (b.getClass() == Num.class) {
      if (b.evaluate() == 1) {
        return a;
      } else if (b.evaluate() == 0) {
        return new Num(0);
      }
    }
    return new Mult(a, b);
  }

  public String toString() {
    return "(" + a.toString() + " * " + b.toString() + ")";
  }

}