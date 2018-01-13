package tv.codely.scala_http_api.video.domain

object VideoCategory {
  def apply(value: String): VideoCategory = value match {
    case "Screencast" => Screencast
    case "Interview"  => Interview
    case "Lesson"     => Lesson
    case _            => throw new RuntimeException(s"Video category not recognized <$value>.")
  }
}

sealed trait VideoCategory

case object Screencast extends VideoCategory
case object Interview  extends VideoCategory
case object Lesson     extends VideoCategory
