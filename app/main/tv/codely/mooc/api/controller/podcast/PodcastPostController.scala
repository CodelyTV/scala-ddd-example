package tv.codely.mooc.api.controller.podcast

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.mooc.podcast.application.create.PodcastsCreator
import tv.codely.mooc.podcast.domain.{PodcastDescription, PodcastDuration, PodcastId, PodcastTitle}

import scala.concurrent.duration.Duration

final class PodcastPostController(creator: PodcastsCreator) {
  def post(id: String, title: String, duration: Duration, description: String): StandardRoute = {
    creator.create(PodcastId(id), PodcastTitle(title), PodcastDuration(duration), PodcastDescription(description))

    complete(HttpResponse(NoContent))
  }
}