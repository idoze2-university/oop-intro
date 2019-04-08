import java.util.List;
import java.util.ArrayList;
import java.util.Map;

class BaseExpression implements Expression {
  protected Expression a;

  public BaseExpression(Expression a) {
    this.a = a;
  }

  public Expression getA() {
    return a;
  }

  public BaseExpression(double a) {
    this(new Num(a));
  }

  public BaseExpression(String a) {
    this(new Var(a));
  }

  public double evaluate(Map<String, Double> assignment) throws Exception {
    for (String var : getVariables()) {
      Double value = assignment.get(var);
      if (value != null) {
        a = a.assign(var, new Num(value));
      } else {
        throw new Exception();
      }
    }
    return evaluate();
  }

  public double evaluate() throws Exception {
    return 0;
  }

  public Expression assign(String var, Expression expression) {
    a = a.assign(var, expression);
    return new BaseExpression(a);
  }

  public List<String> getVariables() {
    ArrayList<String> vars = new ArrayList<String>();
    vars.addAll(a.getVariables());
    return vars;
  }

  public Expression differentiate(String var) {
    BaseExpression de = this;
    de.a = de.a.differentiate(var);
    return de;
  }

  public Expression simplify() throws Exception {
    return this;
  }
}