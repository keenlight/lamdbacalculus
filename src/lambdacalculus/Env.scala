package lambdacalculus

/**
 * Created by Administrator on 2015/5/24.
 */
trait Env {

  def lookup(symbol: Symbol): KeenObject

}
