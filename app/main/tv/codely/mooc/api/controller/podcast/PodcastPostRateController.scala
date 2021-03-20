package tv.codely.mooc.api.controller.podcast

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import spray.json.DefaultJsonProtocol
import tv.codely.mooc.podcast.application.rating.PodcastRater
import tv.codely.mooc.podcast.domain.{PodcastId, PodcastRating}

final class PodcastPostRateController(rater: PodcastRater) extends SprayJsonSupport with DefaultJsonProtocol {
  def post(id: String, rate: Int): StandardRoute = {
    rater.rate(PodcastId(id), PodcastRating(rate))

    complete(HttpResponse(NoContent))
  }
}