package tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie

import java.util.UUID

import doobie.Meta

object TypesConversions {
  implicit val uuidMeta: Meta[UUID] = Meta[String].xmap(UUID.fromString, _.toString)
}
