package tv.codely.shared.domain.bus

import tv.codely.shared.infrastructure.marshaller.MessageJsonFormatMarshaller

trait MessagePublisher {
  def publish[T <: Message](message: T)(implicit marshaller: MessageJsonFormatMarshaller): Unit
}
