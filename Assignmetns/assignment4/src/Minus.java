class Minus extends BinaryExpression {

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

  public double evaluate() throws Exception {
    return a.evaluate() - b.evaluate();
  }

  public String toString() {
    return "(" + a.toString() + " - " + b.toString() + ")";
  }

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