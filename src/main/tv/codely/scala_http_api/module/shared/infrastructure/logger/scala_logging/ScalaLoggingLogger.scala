package tv.codely.scala_http_api.module.shared.infrastructure.logger.scala_logging

import com.typesafe.scalalogging.{Logger => ScalaLogging}
import org.slf4j.MDC
import tv.codely.scala_http_api.module.shared.domain.Logger

final class ScalaLoggingLogger extends Logger {
  private val logger = ScalaLogging(name = "codelytv_scala_api")

  override def info(message: String): Unit = logger.info(message)

  override def warn(message: String): Unit = logger.warn(message)

  override def error(message: String): Unit = logger.error(message)

  override def info(message: String, context: Map[String, Any]): Unit =
    addContextParameters(context, log = logger.info(message))

  override def warn(message: String, context: Map[String, Any]): Unit =
    addContextParameters(context, log = logger.warn(message))

  override def error(message: String, context: Map[String, Any]): Unit =
    addContextParameters(context, log = logger.error(message))

  private def addContextParameters(context: Map[String, Any], log: => Unit): Unit = {
    context.foreach { case (key: String, value: Any) => MDC.put(key, value.toString) }
    log
    MDC.clear()
  }
}
