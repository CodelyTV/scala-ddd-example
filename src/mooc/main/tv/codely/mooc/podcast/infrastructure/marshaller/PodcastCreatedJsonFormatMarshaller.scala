package tv.codely.mooc.podcast.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat, _}
import tv.codely.mooc.podcast.domain._
import tv.codely.mooc.podcast.infrastructure.marshaller.PodcastAttributesJsonFormatMarshaller._

object PodcastCreatedJsonFormatMarshaller extends DefaultJsonProtocol {

  implicit object PodcastCreatedJsonFormat extends RootJsonFormat[PodcastCreated] {
    override def write(c: PodcastCreated): JsValue = JsObject(
      "type"                -> JsString(c.`type`),
      "id"                  -> c.id.toJson,
      "title"               -> c.title.toJson,
      "duration_in_seconds" -> c.duration.toJson,
      "description"         -> c.description.toJson,
      "rating"              -> c.rating.toJson,
      "votes"               -> c.votes.toJson
    )

    override def read(value: JsValue): PodcastCreated =
      value.asJsObject.getFields("id", "title", "duration_in_seconds", "description", "rating", "votes") match {
        case Seq(JsString(id), JsString(title), JsNumber(duration), JsString(description), JsNumber(rating), JsNumber(votes)) =>
          PodcastCreated(id, title, duration, description, rating, votes.intValue())
        case unknown =>
          throw DeserializationException(
            s"Error reading PodcastCreated JSON <$unknown>"
          )
      }
  }

}