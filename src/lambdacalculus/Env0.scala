package lambdacalculus

/**
 * Created by Administrator on 2015/5/24.
 */

class Env0 extends Env {
  val env: Map[String, KeenObject] = Map()

  override def lookup(symbol: Symbol): KeenObject = {
    if (env.contains(symbol.name)) {
      env(symbol.name)
    } else {
      NullObject
    }
  }
}

