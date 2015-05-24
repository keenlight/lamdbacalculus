package lambdacalculus

case class BinaryExpression(expr1: Expression, op: String, expr2: Expression) extends Expression {

  override def toString: String = {
    expr1.toString + op + expr2.toString
  }

}

object BinaryExpression {}
