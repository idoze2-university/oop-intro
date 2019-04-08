import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Num implements Expression {
  double value;

  public Num(double value) {
    this.value = value;
  }

  public double evaluate(Map<String, Double> assignment) throws Exception {
    return evaluate();
  }

  public double evaluate() throws Exception {
    return value;
  }

  public List<String> getVariables() {
    return new ArrayList<String>();
  }

  public String toString() {
    return String.valueOf(value);
  }

  public Expression assign(String var, Expression expression) {
    return this;
  }

  public Expression differentiate(String var) {
    return new Num(0);
  }

  public Expression simplify() {
    return this;
  }
}