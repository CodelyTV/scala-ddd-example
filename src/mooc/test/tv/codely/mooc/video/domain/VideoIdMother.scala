package tv.codely.mooc.video.domain

import java.util.UUID

import tv.codely.shared.domain.UuidMother

object VideoIdMother {
  def apply(value: String): VideoId = VideoIdMother(UuidMother(value))

  def apply(value: UUID): VideoId = VideoId(value)

  def random: VideoId = VideoId(UuidMother.random)
}
