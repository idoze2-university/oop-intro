import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class UnaryExpression extends BaseExpression {
  protected Expression a;

  public UnaryExpression(Expression a) {
    super(a);
  }

  public Expression simplify() throws Exception {
    super.a = super.a.simplify();
    if (super.a.getClass() == Num.class) {
      return new Num(super.a.evaluate());
    }
    return this;
  }

}