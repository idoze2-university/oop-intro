class Log extends BinaryExpression {

  public Log(Expression a, Expression b) {
    super(a, b);
  }

  public Log(Expression a, double b) {
    this(a, new Num(b));
  }

  public Log(Expression a, String b) {
    this(a, new Var(b));
  }

  public Log(double a, Expression b) {
    this(new Num(a), b);
  }

  public Log(double a, double b) {
    this(new Num(a), new Num(b));
  }

  public Log(double a, String b) {
    this(new Num(a), new Var(b));
  }

  public Log(String a, Expression b) {
    this(new Var(a), b);
  }

  public Log(String a, double b) {
    this(new Var(a), new Num(b));
  }

  public Log(String a, String b) {
    this(new Var(a), new Var(b));
  }

  public double evaluate() throws Exception {
    return Math.log(a.evaluate()) / Math.log(b.evaluate());
  }

  public Expression assign(String var, Expression expression) {
    Expression a = this.a.assign(var, expression);
    Expression b = this.b.assign(var, expression);
    return new Log(a, b);
  }

  public String toString() {
    return "Log(" + a.toString() + " , " + b.toString() + ")";
  }

  public Expression differentiate(String var) {
    return new Div(b.differentiate(var), new Mult(b, new Log("e", a))).assign("e", new Num(2.71828));
  }

  public Expression simplify() throws Exception {
    a = a.simplify();
    b = b.simplify();
    if (a.getClass() == Num.class) {
      if (b.getClass() == Num.class) {
        if (Math.abs(evaluate() - b.evaluate()) <= 0.01) {
          return new Num(1);
        }
        return new Num(evaluate());
      }
    }
    String a_ = a.toString();
    String b_ = b.toString();
    if (a_.equals(b_)) {
      return new Num(1);
    }
    return new Log(a, b);
  }
}