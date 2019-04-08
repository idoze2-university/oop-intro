import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class BinaryExpression extends BaseExpression {
  protected Expression b;

  public BinaryExpression(Expression a, Expression b) {
    super(a);
    this.b = b;
  }

  @Override
  public double evaluate(Map<String, Double> assignment) throws Exception {
    for (String var : getVariables()) {
      Double value = assignment.get(var);
      if (value != null) {
        a = a.assign(var, new Num(value));
        b = b.assign(var, new Num(value));
      } else {
        throw new Exception();
      }
    }
    return evaluate();
  }

  public Expression assign(String var, Expression expression) {
    Expression a = this.a.assign(var, expression);
    Expression b = this.b.assign(var, expression);
    return new BinaryExpression(a, b);
  }

  public List<String> getVariables() {
    ArrayList<String> vars = new ArrayList<String>();
    vars.addAll(super.getVariables());
    vars.addAll(b.getVariables());
    return vars;
  }

  public Expression differentiate(String var) {
    BinaryExpression de = this;
    de.a = de.a.differentiate(var);
    de.b = de.b.differentiate(var);
    return de;
  }

}