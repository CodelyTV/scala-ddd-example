package tv.codely.scala_http_api.module.shared.domain

trait Logger {
  def info(message: String): Unit
  def warn(message: String): Unit
  def error(message: String): Unit

  def info(message: String, context: Map[String, Any]): Unit
  def warn(message: String, context: Map[String, Any]): Unit
  def error(message: String, context: Map[String, Any]): Unit
}
