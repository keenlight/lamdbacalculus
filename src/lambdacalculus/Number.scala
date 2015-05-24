package lambdacalculus

/**
 * Created by Administrator on 2015/5/23.
 */
case class Number (value : Double) extends KeenObject with Expression{

  override def toString() : String = {
    value.toString
  }

  def +(other : Number) : Number = {
    new Number(value + other.value)
  }

  def -(other : Number) : Number = {
    new Number(value - other.value)
  }

  def *(other : Number) : Number = {
    new Number(value * other.value)
  }

  def /(other : Number) : Number = {
    if(other.value == 0) throw new RuntimeException("除数不能为0")
    new Number(value / other.value)
  }
}

object Number{}