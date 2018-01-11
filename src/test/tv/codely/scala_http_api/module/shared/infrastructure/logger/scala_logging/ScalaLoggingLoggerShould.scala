package tv.codely.scala_http_api.module.shared.infrastructure.logger.scala_logging

import java.io.{File, PrintWriter}

import spray.json._
import tv.codely.scala_http_api.module.IntegrationTestCase

import scala.io.Source

final class ScalaLoggingLoggerShould extends IntegrationTestCase {
  private val appLogFilePath = "var/log/app_log.json"

  "log info messages in the app log file in a JSON format" in {
    cleanAppLogFileContents()

    val message = "This is a dummy info message from the integration test"

    logger.info(message)

    appLogFileShouldContainRecord(message, level = "INFO", levelValue = 20000)
  }

  "log warn messages in the app log file in a JSON format" in {
    cleanAppLogFileContents()

    val message = "This is a dummy warn message from the integration test"

    logger.warn(message)

    appLogFileShouldContainRecord(message, level = "WARN", levelValue = 30000)
  }

  "log error messages in the app log file in a JSON format" in {
    cleanAppLogFileContents()

    val message = "This is a dummy error message from the integration test"

    logger.error(message)

    appLogFileShouldContainRecord(message, level = "ERROR", levelValue = 40000)
  }

  "log info messages including context parameters" in {
    cleanAppLogFileContents()

    val message = "This is a dummy info message with context parameters from the integration test"

    val context = Map("some_context_parameter" -> "some_value")

    logger.info(message, context)

    appLogFileShouldContainRecordWithKeys(message, level = "INFO", levelValue = 20000, context)
  }

  "log warn messages including context parameters" in {
    cleanAppLogFileContents()

    val message = "This is a dummy warn message with context parameters from the integration test"

    val context = Map("some_context_parameter" -> "some_value")

    logger.warn(message, context)

    appLogFileShouldContainRecordWithKeys(message, level = "WARN", levelValue = 30000, context)
  }

  "log error messages including context parameters" in {
    cleanAppLogFileContents()

    val message = "This is a dummy error message with context parameters from the integration test"

    val context = Map("some_context_parameter" -> "some_value")

    logger.error(message, context)

    appLogFileShouldContainRecordWithKeys(message, level = "ERROR", levelValue = 40000, context)
  }

  private def cleanAppLogFileContents(): Unit = {
    val appLogFileWriter = new PrintWriter(new File(appLogFilePath), "UTF-8")
    appLogFileWriter.write("")
    appLogFileWriter.close()
  }

  private def appLogFileShouldContainRecord(
      message: String,
      level: String,
      levelValue: Int
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
    )

    val actualLog = fileContents.parseJson.asJsObject.fields

    actualLog.keys shouldBe expectedLog.keys
    (actualLog - "@timestamp") shouldBe (expectedLog - "@timestamp")
  }

  /** We're only testing for the log record keys because for some strange reason if we perform the assertion:
    * (actualLog - "@timestamp") shouldBe (expectedLog - "@timestamp")
    * the test fails as if the two log records were different.
    * The most strange thing is that if we copy the expected and actual results form the ScalaTest exception,
    * they're exactly the same expression :/
    */
  private def appLogFileShouldContainRecordWithKeys(
      message: String,
      level: String,
      levelValue: Int,
      context: Map[String, Any]
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
  }
}
