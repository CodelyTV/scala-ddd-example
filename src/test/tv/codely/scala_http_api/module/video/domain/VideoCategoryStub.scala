package tv.codely.scala_http_api.module.video.domain

import tv.codely.scala_http_api.module.shared.domain.IntStub

object VideoCategoryStub {
  private val categories = Seq("Screencast", "Interview")

  def apply(value: String): VideoCategory = VideoCategory(value)

  def random: VideoCategory = VideoCategory(categories(IntStub.randomBetween(min = 0, max = categories.size - 1)))
}
