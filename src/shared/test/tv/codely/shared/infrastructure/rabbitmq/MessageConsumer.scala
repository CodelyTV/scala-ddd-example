package tv.codely.shared.infrastructure.rabbitmq

import tv.codely.shared.domain.bus.Message

trait MessageConsumer {
  def startConsuming(handler: Message => Boolean): Unit
  def hasMessagesToConsume: Boolean
  def isEmpty: Boolean = !hasMessagesToConsume
}
