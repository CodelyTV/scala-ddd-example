package tv.codely.scala_http_api.module.shared.domain

trait Logger {
  def info(message: String)
  def warn(message: String)
  def error(message: String)

  def info(message: String, context: Map[String, Any]): Unit
  def warn(message: String, context: Map[String, Any]): Unit
  def error(message: String, context: Map[String, Any]): Unit
}
