package tv.codely.scala_http_api.module.shared.domain

trait Logger {
  def info(message: String)
  def warn(message: String)
  def error(message: String)
}
