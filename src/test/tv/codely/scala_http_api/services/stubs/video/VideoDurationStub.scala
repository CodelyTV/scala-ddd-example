package tv.codely.scala_http_api.application.stubs.video

import scala.concurrent.duration.Duration

import tv.codely.scala_http_api.application.stubs.DurationStub
import tv.codely.scala_http_api.application.video.api._

object VideoDurationStub {
  def apply(value: Duration): VideoDuration = VideoDuration(value)

  def random: VideoDuration = VideoDuration(DurationStub.random)
}
