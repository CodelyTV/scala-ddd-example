package tv.codely.scala_http_api.module.shared.infrastructure.logger.scala_logging

import java.io.{File, PrintWriter}

import spray.json._
import tv.codely.scala_http_api.module.IntegrationTestCase

import scala.io.Source

final class ScalaLoggingLoggerShould extends IntegrationTestCase {
  private val appLogFilePath = "var/log/app_log.json"

  "log info messages in the app log file in a JSON format" in {
    cleanAppLogFileContents()

    val logText = "This is a dummy message to log from the integration test"

    logger.info(logText)

    val fileContents = Source.fromFile(appLogFilePath).getLines.mkString

    val currentThreadName = Thread.currentThread().getName

    val expectedLog = Map(
      "@timestamp"  -> JsString("We'll not be able to compare this value"),
      "@version"    -> JsNumber(1),
      "message"     -> JsString(logText),
      "logger_name" -> JsString("codelytv_scala_api"),
      "thread_name" -> JsString(currentThreadName),
      "level"       -> JsString("INFO"),
      "level_value" -> JsNumber(20000)
    )

    val actualLog = fileContents.parseJson.asJsObject.fields

    actualLog.keys shouldBe expectedLog.keys

    (actualLog - "@timestamp") shouldBe (expectedLog - "@timestamp")
  }

  "log warn messages in the app log file in a JSON format" in pending
  "log error messages in the app log file in a JSON format" in pending
  "compress historical logs" in pending
  "delete historical logs older than 10 days ago" in pending

  private def cleanAppLogFileContents(): Unit = {
    val appLogFileWriter = new PrintWriter(new File(appLogFilePath), "UTF-8")
    appLogFileWriter.write("")
    appLogFileWriter.close()
  }
}
