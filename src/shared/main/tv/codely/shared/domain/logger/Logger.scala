package tv.codely.shared.domain.logger

trait Logger {
  def info(message: String, context: Map[String, Any] = Map.empty): Unit
  def warn(message: String, context: Map[String, Any] = Map.empty): Unit
  def error(message: String, context: Map[String, Any] = Map.empty): Unit

  def info(message: String, cause: Throwable): Unit
  def warn(message: String, cause: Throwable): Unit
  def error(message: String, cause: Throwable): Unit

  def info(message: String, cause: Throwable, context: Map[String, Any]): Unit
  def warn(message: String, cause: Throwable, context: Map[String, Any]): Unit
  def error(message: String, cause: Throwable, context: Map[String, Any]): Unit
}
