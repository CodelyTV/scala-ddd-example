package tv.codely.scala_http_api.application.stubs.video

import tv.codely.scala_http_api.application.stubs.{IntStub, StringStub}
import tv.codely.scala_http_api.application.video.api._

object VideoTitleStub {
  private val minimumChars = 1
  private val maximumChars = 50

  def apply(value: String): VideoTitle = VideoTitle(value)

  def random: VideoTitle = VideoTitle(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
