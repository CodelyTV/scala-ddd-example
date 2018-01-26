package tv.codely.scala_http_api.module.video.infrastructure.marshaller

import java.util.UUID

import spray.json.{DeserializationException, JsNumber, JsString, JsValue, JsonFormat, _}
import tv.codely.scala_http_api.module.shared.marshaller.infrastructure.UuidJsonFormatMarshaller._
import tv.codely.scala_http_api.module.video.domain._

object VideoAttributesJsonFormatMarshaller {
  implicit object VideoIdMarshaller extends JsonFormat[VideoId] {
    override def write(value: VideoId): JsValue = value.value.toJson

    override def read(value: JsValue): VideoId = VideoId(value.convertTo[UUID])
  }

  implicit object VideoTitleMarshaller extends JsonFormat[VideoTitle] {
    override def write(value: VideoTitle): JsValue = JsString(value.value)

    override def read(value: JsValue): VideoTitle = value match {
      case JsString(name) => VideoTitle(name)
      case _              => throw DeserializationException("Expected 1 string for VideoTitle")
    }
  }

  implicit object VideoDurationMarshaller extends JsonFormat[VideoDuration] {
    override def write(value: VideoDuration): JsValue = JsNumber(value.value.toSeconds)

    override def read(value: JsValue): VideoDuration = value match {
      case JsNumber(seconds) => VideoDuration(seconds)
      case _                 => throw DeserializationException("Expected 1 string for VideoDuration")
    }
  }

  implicit object VideoCategoryMarshaller extends JsonFormat[VideoCategory] {
    override def write(value: VideoCategory): JsValue = JsString(value.toString)

    override def read(value: JsValue): VideoCategory = value match {
      case JsString(name) => VideoCategory(name)
      case _              => throw DeserializationException("Expected 1 string for VideoCategory")
    }
  }
}
