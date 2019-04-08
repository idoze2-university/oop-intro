class Div extends BinaryExpression implements Expression {

  public Div(Expression a, Expression b) {
    super(a, b);
  }

  public Div(Expression a, double b) {
    this(a, new Num(b));
  }

  public Div(Expression a, String b) {
    this(a, new Var(b));
  }

  public Div(double a, Expression b) {
    this(new Num(a), b);
  }

  public Div(double a, double b) {
    this(new Num(a), new Num(b));
  }

  public Div(double a, String b) {
    this(new Num(a), new Var(b));
  }

  public Div(String a, Expression b) {
    this(new Var(a), b);
  }

  public Div(String a, double b) {
    this(new Var(a), new Num(b));
  }

  public Div(String a, String b) {
    this(new Var(a), new Var(b));
  }

  public double evaluate() throws Exception {
    return a.evaluate() / b.evaluate();
  }

  public Expression assign(String var, Expression expression) {
    Expression a = this.a.assign(var, expression);
    Expression b = this.b.assign(var, expression);
    return new Div(a, b);
  }

  public String toString() {
    return "(" + a.toString() + " / " + b.toString() + ")";
  }

  public Expression differentiate(String var) {
    return new Div(new Minus(new Mult(a.differentiate(var), b), new Mult(a, b.differentiate(var))), new Pow(b, 2));
  }

  public Expression simplify() throws Exception {
    a = a.simplify();
    b = b.simplify();
    if (a.getClass() == Num.class) {
      if (b.getClass() == Num.class) {
        return new Num(evaluate());
      }
    }
    try {
      if (b.evaluate() == 1) {
        return a;
      }
    } catch (Exception e) {
    }
    return new Div(a, b);
  }
}