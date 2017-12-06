package tv.codely.scala_http_api.video.infrastructure.stub

import tv.codely.scala_http_api.shared.infrastructure.stub.{IntStub, StringStub}
import tv.codely.scala_http_api.video.domain.VideoTitle

object VideoTitleStub {
  private val minimumChars = 1
  private val maximumChars = 50

  def apply(value: String): VideoTitle = VideoTitle(value)

  def random: VideoTitle = VideoTitle(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
