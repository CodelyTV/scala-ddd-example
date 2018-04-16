package tv.codely.scala_http_api.effects.logger.api

trait Logger[P[_]] {
  def info(message: String, context: Map[String, Any] = Map.empty): P[Unit]
  def warn(message: String, context: Map[String, Any] = Map.empty): P[Unit]
  def error(message: String, context: Map[String, Any] = Map.empty): P[Unit]

  def info(message: String, cause: Throwable): P[Unit]
  def warn(message: String, cause: Throwable): P[Unit]
  def error(message: String, cause: Throwable): P[Unit]

  def info(message: String, cause: Throwable, context: Map[String, Any]): P[Unit]
  def warn(message: String, cause: Throwable, context: Map[String, Any]): P[Unit]
  def error(message: String, cause: Throwable, context: Map[String, Any]): P[Unit]
}
