package tv.codely.mooc.podcast.infrastructure.marshaller

import java.util.UUID

import spray.json.{DeserializationException, JsNumber, JsString, JsValue, JsonFormat, _}
import tv.codely.mooc.podcast.domain._
import tv.codely.shared.infrastructure.marshaller.UuidJsonFormatMarshaller._

import java.util.UUID

object PodcastAttributesJsonFormatMarshaller {
  implicit object PodcastIdMarshaller extends JsonFormat[PodcastId] {
    override def write(value: PodcastId): JsValue = value.value.toJson

    override def read(value: JsValue): PodcastId = PodcastId(value.convertTo[UUID])
  }

  implicit object PodcastTitleMarshaller extends JsonFormat[PodcastTitle] {
    override def write(value: PodcastTitle): JsValue = JsString(value.title)

    override def read(value: JsValue): PodcastTitle = value match {
      case JsString(name) => PodcastTitle(name)
      case _              => throw DeserializationException("Expected 1 string for PodcastTitle")
    }
  }

  implicit object PodcastDurationMarshaller extends JsonFormat[PodcastDuration] {
    override def write(value: PodcastDuration): JsValue = JsNumber(value.value.toSeconds)

    override def read(value: JsValue): PodcastDuration = value match {
      case JsNumber(seconds) => PodcastDuration(seconds)
      case _                 => throw DeserializationException("Expected 1 string for PodcastDuration")
    }
  }

  implicit object PodcastCategoryMarshaller extends JsonFormat[PodcastDescription] {
    override def write(value: PodcastDescription): JsValue = JsString(value.description)

    override def read(value: JsValue): PodcastDescription = value match {
      case JsString(name) => PodcastDescription(name)
      case _              => throw DeserializationException("Expected 1 string for PodcastDescription")
    }
  }

  implicit object PodcastRatingMarshaller extends JsonFormat[PodcastRating] {
    override def write(value: PodcastRating): JsValue = JsNumber(value.value)

    override def read(value: JsValue): PodcastRating = value match {
      case JsNumber(value) => PodcastRating(value.intValue())
      case _               => throw DeserializationException("Expected 1 string for PodcastRating")
    }
  }

  implicit object PodcastVotesMarshaller extends JsonFormat[PodcastVotes] {
    override def write(value: PodcastVotes): JsValue = JsNumber(value.votes)

    override def read(value: JsValue): PodcastVotes = value match {
      case JsNumber(value) => PodcastVotes(value.intValue())
      case _               => throw DeserializationException("Expected 1 string for PodcastVotes")
    }
  }
}