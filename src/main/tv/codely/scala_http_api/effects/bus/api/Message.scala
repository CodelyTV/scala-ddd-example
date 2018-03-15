package tv.codely.scala_http_api.effects.bus.api

import tv.codely.scala_http_api.effects.bus.api.Message.application

object Message {
  val application: String = "codelytv_scala_api"
}

abstract class Message {
  val subType: String
  lazy val `type`: String = s"$application.$subType"
}
