package tv.codely.mooc.video.domain

import tv.codely.shared.domain.IntMother

object VideoCategoryMother {
  private val categories = Seq("Screencast", "Interview")

  def apply(value: String): VideoCategory = VideoCategory(value)

  def random: VideoCategory = VideoCategory(categories(IntMother.randomBetween(min = 0, max = categories.size - 1)))
}
