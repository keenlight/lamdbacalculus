package lambdacalculus

/**
 * Created by Administrator on 2015/5/24.
 */
object Interp {

  def main(args: Array[String]) {

    var exp: Expression = BinaryExpression(Number(1), "+", Number(2))
    var result = Interp.run(exp)
    println(result) //expect 3
    println("-" * 10)

    exp = BinaryExpression(BinaryExpression(Number(1), "+", Number(2)), "*", Number(3))
    result = Interp.run(exp)
    println(result) //expect 9
    println("-" * 10)

    exp = Call(Lambda(Symbol("x"), BinaryExpression(Symbol("x"), "+", Number(2))), Number(10))
    result = Interp.run(exp)
    println(result) //expect 12
    println("-" * 10)

    /**
     *((lambda (y) (((lambda (y) (lambda (x) (* y 2))) 3) 0)) 4)
     *(lambda (y) (((lambda (y) (lambda (x) (* y 2))) 3) 0))
     * (((lambda (y) (lambda (x) (* y 2))) 3) 0)
     *((lambda (y) (lambda (x) (* y 2))) 3)
     * (lambda (y) (lambda (x) (* y 2)))
     * (lambda (x) (* y 2))
     */
    exp = Call(Lambda(Symbol("y"), Call(Call(Lambda(Symbol("y"), Lambda(Symbol("x"), BinaryExpression(Symbol("y"), "*", Number(2)))), Number(3)), Number(0))), Number(4))
    result = Interp.run(exp)
    println(result) //expect 6
    println("-" * 10)
  }

  def run(exp: Expression): KeenObject = {
    interp1(exp, new Env0)
  }

  def interp1(exp: Expression, env: Env): KeenObject = {
    exp match {
      case x: Symbol => env.lookup(x)
      case x: Number => x
      case Lambda(param, e) => Closure(exp, env)
      case Call(func, param) => {
        val v1 = interp1(func, env)
        val v2 = interp1(param, env)
        v1 match {
          case Closure(Lambda(param, e), env1) => interp1(e, ExtEnv(param, v2, env1))
        }
      }
      case BinaryExpression(e1, op, e2) => {
        val v1 = interp1(e1, env) match {
          case num: Number => num
          case _ => throw new RuntimeException("不支持的操作数：" + op)
        }
        val v2 = interp1(e2, env) match {
          case num: Number => num
          case _ => throw new RuntimeException("不支持的操作数：" + op)
        }
        op match {
          case "+" => v1 + v2
          case "-" => v1 - v2
          case "*" => v1 * v2
          case "/" => v1 / v2
          case _ => throw new RuntimeException("不支持的操作符：" + op)
        }
      }
      case _ => throw new RuntimeException("不支持的表达式：" + exp)
    }
  }
}
