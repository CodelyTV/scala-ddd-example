package tv.codely.scala_http_api.module.video.infrastructure

import java.util.UUID

import spray.json.{DeserializationException, JsNumber, JsString, JsValue, JsonFormat, RootJsonFormat}
import spray.json.DefaultJsonProtocol._
import tv.codely.scala_http_api.module.video.domain._

object VideoMarshaller {
  implicit object UuidMarshaller extends JsonFormat[UUID] {
    def write(value: UUID): JsValue = JsString(value.toString)

    def read(value: JsValue): UUID = value match {
      case JsString(uuid) => UUID.fromString(uuid)
      case _              => throw DeserializationException("Expected hexadecimal UUID string")
    }
  }

  implicit object VideoIdMarshaller extends JsonFormat[VideoId] {
    def write(value: VideoId): JsValue = JsString(value.value.toString)

    def read(value: JsValue): VideoId = value match {
      case JsString(id) => VideoId(id)
      case _            => throw DeserializationException("Expected 1 string for VideoId")
    }
  }

  implicit object VideoTitleMarshaller extends JsonFormat[VideoTitle] {
    def write(value: VideoTitle): JsValue = JsString(value.value)

    def read(value: JsValue): VideoTitle = value match {
      case JsString(name) => VideoTitle(name)
      case _              => throw DeserializationException("Expected 1 string for VideoTitle")
    }
  }

  implicit object VideoDurationMarshaller extends JsonFormat[VideoDuration] {
    def write(value: VideoDuration): JsValue = JsNumber(value.value.toSeconds)

    def read(value: JsValue): VideoDuration = value match {
      case JsNumber(seconds) => VideoDuration(seconds)
      case _                 => throw DeserializationException("Expected 1 string for VideoDuration")
    }
  }

  implicit object VideoCategoryMarshaller extends JsonFormat[VideoCategory] {
    def write(value: VideoCategory): JsValue = JsString(value.toString)

    def read(value: JsValue): VideoCategory = value match {
      case JsString(name) => VideoCategory(name)
      case _              => throw DeserializationException("Expected 1 string for VideoCategory")
    }
  }

  implicit val videoFormat: RootJsonFormat[Video] = jsonFormat4(
    Video.apply(_: VideoId, _: VideoTitle, _: VideoDuration, _: VideoCategory)
  )
}
