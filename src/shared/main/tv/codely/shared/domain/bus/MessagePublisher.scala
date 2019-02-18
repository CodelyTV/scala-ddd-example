package tv.codely.shared.domain.bus

import spray.json.RootJsonFormat

trait MessagePublisher {
  def publish[T <: Message](message: T)(implicit marshaller: RootJsonFormat[Message]): Unit
}
