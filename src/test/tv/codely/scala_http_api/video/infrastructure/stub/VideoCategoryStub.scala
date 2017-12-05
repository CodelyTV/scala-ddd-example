package tv.codely.scala_http_api.video.infrastructure.stub

import tv.codely.scala_http_api.shared.infrastructure.stub.IntStub
import tv.codely.scala_http_api.video.domain.VideoCategory

object VideoCategoryStub {
  private val categories = Seq("Screencast", "Interviews")

  def apply(value: String): VideoCategory = VideoCategory(value)

  def random: VideoCategory = VideoCategory(categories(IntStub.randomBetween(min = 0, max = categories.size - 1)))
}
