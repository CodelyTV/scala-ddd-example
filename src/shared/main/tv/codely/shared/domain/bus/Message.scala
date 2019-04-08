package tv.codely.shared.domain.bus

import tv.codely.shared.domain.bus.Message.application

object Message {
  val application: String = "cqrs_ddd_scala_example"
}

abstract class Message {
  val subType: String
  lazy val `type`: String = s"$application.$subType"
}
