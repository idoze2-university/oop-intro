xx- represents any expression.
v- represents any variable.
a,b - represents any numeric values.
# - represents bonus simplification.

_Global simplifications_
  I.  Expression(a,b) = evaluation of Expression.

_Mult Simplifications_
  I.    x*1 = x
# II.   1*x = x //Added transitivity.
  III.  x*0 = 0
# IV.   0*x = 0 //Added transitivity.

_Plus Simplifications_
  I.    x+0 = x
# II.   0+x = x //Added transitivity.

_Div Simplifications_
  I.    x/x = 1
  II.   x/1 = x

_Minus Simplifications_
  I.    x-0 = x
  II.   0-x = -x
  III.  x-x = 0

_Log Simplifications_
  I.    log(x,x) = 1
  I.    log(a,a) = 1

_Pow Simplifications_
# I.    x^0 = 1
  II.   1^x = 1