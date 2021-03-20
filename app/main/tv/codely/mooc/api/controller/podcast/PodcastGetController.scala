package tv.codely.mooc.api.controller.podcast

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import spray.json.DefaultJsonProtocol
import tv.codely.mooc.podcast.application.search.PodcastsSearcher
import tv.codely.mooc.podcast.infrastructure.marshaller.PodcastJsonFormatMarshaller._


final class PodcastGetController(searcher: PodcastsSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}