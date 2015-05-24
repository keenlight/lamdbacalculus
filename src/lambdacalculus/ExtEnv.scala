package lambdacalculus

/**
 * Created by Administrator on 2015/5/24.
 */
class ExtEnv(x: Symbol, v: KeenObject, env: Env) extends Env {
  val selfEnv: Map[String, KeenObject] = Map(x.name -> v)

  override def lookup(symbol: Symbol): KeenObject = {
    if (selfEnv.contains(symbol.name)) selfEnv(symbol.name) else env.lookup(symbol)
  }
}

object ExtEnv {
  def apply(x: Symbol, v: KeenObject, env: Env): ExtEnv = {
    new ExtEnv(x, v, env)
  }
}
