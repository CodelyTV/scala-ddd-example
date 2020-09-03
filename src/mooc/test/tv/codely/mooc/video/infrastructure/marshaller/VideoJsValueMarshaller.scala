package tv.codely.mooc.video.infrastructure.marshaller

import spray.json.{JsArray, JsNumber, JsObject, JsString}
import tv.codely.mooc.video.domain.Video

object VideoJsValueMarshaller {
  def marshall(video: Video): JsObject = JsObject(
    "id"                  -> JsString(video.id.value.toString),
    "title"               -> JsString(video.title.value),
    "duration_in_seconds" -> JsNumber(video.duration.value.toSeconds),
    "category"            -> JsString(video.category.toString),
    "creator_id"          -> JsString(video.creatorId.value.toString)
  )

  def marshall(videos: Seq[Video]): JsArray = JsArray(
    videos
      .map(v => marshall(v))
      .toVector
  )
}
