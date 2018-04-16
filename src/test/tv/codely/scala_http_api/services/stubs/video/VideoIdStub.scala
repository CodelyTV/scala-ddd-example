package tv.codely.scala_http_api.application.stubs.video

import java.util.UUID

import tv.codely.scala_http_api.application.stubs.UuidStub
import tv.codely.scala_http_api.application.video.api._

object VideoIdStub {
  def apply(value: String): VideoId = VideoIdStub(UuidStub(value))

  def apply(value: UUID): VideoId = VideoId(value)

  def random: VideoId = VideoId(UuidStub.random)
}
