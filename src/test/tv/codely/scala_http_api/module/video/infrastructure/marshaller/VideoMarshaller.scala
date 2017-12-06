package tv.codely.scala_http_api.module.video.infrastructure.marshaller

import spray.json.{JsArray, JsNumber, JsObject, JsString}
import tv.codely.scala_http_api.module.video.domain.Video

object VideoMarshaller {
  def marshall(videos: Seq[Video]): JsArray = JsArray(
    videos
      .map(
        v =>
          JsObject(
            "id"       -> JsString(v.id.value.toString),
            "title"    -> JsString(v.title.value),
            "duration" -> JsNumber(v.duration.value.toSeconds),
            "category" -> JsString(v.category.toString),
        ))
      .toVector
  )
}
