package tv.codely.scala_http_api.effects.loggier.scala_logging

import java.io.{File, PrintWriter, StringWriter}

import spray.json._
import tv.codely.scala_http_api.application.mock.UnitTestCase
import tv.codely.scala_http_api.effects.logger.scala_logging.ScalaLoggingLogger

import scala.io.Source

final class ScalaLoggingLoggerShould extends UnitTestCase {
  private val logger = new ScalaLoggingLogger
  private val appLogFilePath = "var/log/app_log.json"

  "log info messages in the app log file in a JSON format" in {
    cleanAppLogFileContents()

    val message = "This is a dummy info message from the integration test"

    logger.info(message)

    appLogFileShouldContain(message, level = "INFO", levelValue = 20000)
  }

  "log warn messages in the app log file in a JSON format" in {
    cleanAppLogFileContents()

    val message = "This is a dummy warn message from the integration test"

    logger.warn(message)

    appLogFileShouldContain(message, level = "WARN", levelValue = 30000)
  }

  "log error messages in the app log file in a JSON format" in {
    cleanAppLogFileContents()

    val message = "This is a dummy error message from the integration test"

    logger.error(message)

    appLogFileShouldContain(message, level = "ERROR", levelValue = 40000)
  }

  "log messages including context parameters" in {
    cleanAppLogFileContents()

    val message     = "This is a dummy info message with context parameters from the integration test"
    val context     = Map("some_context_parameter" -> "some_value")
    val contextJson = Map("some_context_parameter" -> JsString("some_value"))

    logger.info(message, context)

    appLogFileShouldContain(message, level = "INFO", levelValue = 20000, contextJson)
  }

  "log messages including the Throwable cause" in {
    cleanAppLogFileContents()

    val message   = "This is a dummy info message with a throwable from the integration test"
    val cause     = new Throwable("This is a dummy Throwable for the integration test")
    val causeJson = Map("stack_trace" -> JsString(stackTraceString(cause)))

    logger.info(message, cause)

    appLogFileShouldContain(message, level = "INFO", levelValue = 20000, causeJson)
  }

  "log messages including the Throwable cause and context parameters" in {
    cleanAppLogFileContents()

    val message = "This is a dummy info message with a throwable from the integration test"

    val cause     = new Throwable("This is a dummy Throwable for the integration test")
    val causeJson = Map("stack_trace" -> JsString(stackTraceString(cause)))

    val context     = Map("some_context_parameter" -> "some_value")
    val contextJson = Map("some_context_parameter" -> JsString("some_value"))

    logger.info(message, cause, context)

    appLogFileShouldContain(message, level = "INFO", levelValue = 20000, causeJson ++ contextJson)
  }

  private def cleanAppLogFileContents(): Unit = {
    val appLogFileWriter = new PrintWriter(new File(appLogFilePath), "UTF-8")
    appLogFileWriter.write("")
    appLogFileWriter.close()
  }

  private def appLogFileShouldContain(
      message: String,
      level: String,
      levelValue: Int,
      context: Map[String, JsValue] = Map.empty
  ): Unit = {
    val fileContents = Source.fromFile(appLogFilePath).getLines.mkString

    val currentThreadName = Thread.currentThread().getName

    val expectedLog = Map(
      "@timestamp"  -> JsString("We'll not be able to compare this value"),
      "@version"    -> JsNumber(1),
      "message"     -> JsString(message),
      "logger_name" -> JsString("codelytv_scala_api"),
      "thread_name" -> JsString(currentThreadName),
      "level"       -> JsString(level),
      "level_value" -> JsNumber(levelValue)
    ) ++ context

    val actualLog = fileContents.parseJson.asJsObject.fields

    actualLog.keys shouldBe expectedLog.keys
    (actualLog - "@timestamp") shouldBe (expectedLog - "@timestamp")
  }

  private def stackTraceString(throwable: Throwable) = {
    val writer = new StringWriter
    throwable.printStackTrace(new PrintWriter(writer))
    writer.toString
  }
}
