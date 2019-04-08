import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {
  private String label;

  public Var(String label) {
    this.label = label.toLowerCase();
  }

  public String getLabel() {
    return label;
  }

  public double evaluate(Map<String, Double> assignment) throws Exception {
    Expression ret = this;
    if (assignment == null || !assignment.containsKey(label)) {
      throw new Exception(label + " is unassigned in assignment.");
    }
    for (String var : assignment.keySet()) {
      ret = ret.assign(var, new Num(assignment.get(var)));
    }
    return ret.evaluate();
  }

  public double evaluate() throws Exception {
    return evaluate(null);
  }

  public Expression assign(String var, Expression expression) {
    if (var.toLowerCase() != label) {
      return this;
    } else {
      return expression;
    }
  }

  public List<String> getVariables() {
    ArrayList<String> a = new ArrayList<String>();
    a.add(getLabel());
    return a;
  }

  public Expression differentiate(String var) {
    if (var.toLowerCase() == label) {
      return new Num(1);
    } else
      return new Num(0);
  }

  public Expression simplify() {
    return this;
  }

  public String toString() {
    return label;
  }

}