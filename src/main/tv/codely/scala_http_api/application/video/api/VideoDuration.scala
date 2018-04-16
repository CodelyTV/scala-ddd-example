package tv.codely.scala_http_api.application.video.api

import scala.concurrent.duration._

object VideoDuration {
  def apply(seconds: BigDecimal): VideoDuration = VideoDuration(seconds.longValue().seconds)
}

case class VideoDuration(value: Duration)
