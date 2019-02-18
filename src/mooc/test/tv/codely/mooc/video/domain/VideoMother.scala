package tv.codely.mooc.video.domain

import tv.codely.mooc.user.domain.UserIdMother
import scala.concurrent.duration.Duration

import tv.codely.shared.domain.SeqMother

object VideoMother {
  def apply(
      id: String = VideoIdMother.random.value.toString,
      title: String = VideoTitleMother.random.value.toString,
      duration: Duration = VideoDurationMother.random.value,
      category: String = VideoCategoryMother.random.toString,
      creatorId: String = UserIdMother.random.value.toString
  ): Video = Video(id, title, duration, category, creatorId)

  def random: Video = apply()

  def randomSeq: Seq[Video] = SeqMother.randomOf(apply())
}
