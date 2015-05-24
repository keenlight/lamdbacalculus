package lambdacalculus

/**
 * Created by Administrator on 2015/5/23.
 */
case class BinaryExpression(expr1 : Expression, op : String, expr2 : Expression) extends Expression{

  override def toString() : String = {
    expr1.toString + op + expr2.toString
  }

}

object BinaryExpression{}
