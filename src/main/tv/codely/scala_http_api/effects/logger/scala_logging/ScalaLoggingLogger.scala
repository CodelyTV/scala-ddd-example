package tv.codely.scala_http_api.effects.logger.scala_logging

import com.typesafe.scalalogging.{Logger => ScalaLogging}
import org.slf4j.MDC
import tv.codely.scala_http_api.effects.logger.api.Logger
import cats.Id

final class ScalaLoggingLogger extends Logger[Id] {
  private val logger = ScalaLogging(name = "codelytv_scala_api")

  override def info(message: String, context: Map[String, Any] = Map.empty): Unit =
    addContextParameters(context, log = logger.info(message))

  override def warn(message: String, context: Map[String, Any] = Map.empty): Unit =
    addContextParameters(context, log = logger.warn(message))

  override def error(message: String, context: Map[String, Any] = Map.empty): Unit =
    addContextParameters(context, log = logger.error(message))

  override def info(message: String, cause: Throwable): Unit = logger.info(message, cause)

  override def warn(message: String, cause: Throwable): Unit = logger.warn(message, cause)

  override def error(message: String, cause: Throwable): Unit = logger.error(message, cause)

  override def info(message: String, cause: Throwable, context: Map[String, Any]): Unit =
    addContextParameters(context, log = logger.info(message, cause))

  override def warn(message: String, cause: Throwable, context: Map[String, Any]): Unit =
    addContextParameters(context, log = logger.warn(message, cause))

  override def error(message: String, cause: Throwable, context: Map[String, Any]): Unit =
    addContextParameters(context, log = logger.error(message, cause))

  private def addContextParameters(context: Map[String, Any], log: => Unit): Unit = {
    context.foreach { case (key: String, value: Any) => MDC.put(key, value.toString) }
    log
    MDC.clear()
  }
}
