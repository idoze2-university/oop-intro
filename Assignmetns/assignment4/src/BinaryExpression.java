import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The UnaryExpression class extends the BaseExpression class and implements an
 * expression which holds two inner expressions.
 */
class BinaryExpression extends BaseExpression {
  private Expression bField;

  /**
   * Default Constructor.
   *
   * @param a inner expression a.
   * @param b inner expression b.
   */
  protected BinaryExpression(Expression a, Expression b) {
    super(a);
    this.setB(b);
  }

  /**
   * @param b the b to set
   */
  protected void setB(Expression b) {
    this.bField = b;
  }

  /**
   * @return inner expression b.
   */
  protected Expression getB() {
    return bField;
  }

  @Override
  /**
   * Evaluate the expression using the variable values provided in the assignment,
   * and return the result.
   *
   * @param assignment set of variables and their values.
   * @return result of the evaluated expression.
   * @throws Exception If the expression contains a variable which is not in the
   *                   assignment, an exception is thrown.
   */
  public double evaluate(Map<String, Double> assignment) throws Exception {
    Expression orgA = getA();
    Expression orgB = getB();
    List<String> vars = getVariables();
    for (String var : vars) {
      Double value = assignment.get(var);
      if (value != null) {
        setA(getA().assign(var, new Num(value)));
        setB(getB().assign(var, new Num(value)));
      } else {
        throw new Exception();
      }
    }
    double eval = evaluate();
    setA(orgA);
    setB(orgB);
    return eval;
  }

  /**
   * Returns a new expression in which all occurrences of the variable var are
   * replaced with the provided expression (Does not modify the current
   * expression).
   *
   * @param var        the variable which the expression should be assigned to.
   * @param expression expression to assign to var.
   * @return a new expression where all occurences of var are replaced with the
   *         provided expression.
   */
  public Expression assign(String var, Expression expression) {
    Expression a = getA().assign(var, expression);
    Expression b = getB().assign(var, expression);
    return new BinaryExpression(a, b);
  }

  /**
   * @return Returns a list of the variables in the expression.
   */
  public List<String> getVariables() {
    ArrayList<String> vars = new ArrayList<String>();
    vars.addAll(super.getVariables());
    for (String var : getB().getVariables()) {
      if (!vars.contains(var)) {
        vars.add(var);
      }
    }
    return vars;
  }

  /**
   * Returns the expression tree resulting from differentiating the current
   * expression relative to variable `var`.
   *
   * @param var Variable to differentiate by.
   * @return the expression result.
   */
  public Expression differentiate(String var) {
    BinaryExpression de = this;
    de.setA(de.getA().differentiate(var));
    de.setB(de.getB().differentiate(var));
    return de;
  }

}