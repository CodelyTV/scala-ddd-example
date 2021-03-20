package tv.codely.mooc.podcast.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import tv.codely.mooc.podcast.domain._
import tv.codely.mooc.podcast.infrastructure.marshaller.PodcastAttributesJsonFormatMarshaller._


object PodcastJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val podcastFormat: RootJsonFormat[Podcast] = jsonFormat(
    Podcast.apply(_: PodcastId, _: PodcastTitle, _: PodcastDuration, _: PodcastDescription, _: PodcastRating, _:PodcastVotes),
    "id",
    "title",
    "duration_in_seconds",
    "description",
    "rating",
    "votes"
  )
}