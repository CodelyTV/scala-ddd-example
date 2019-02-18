package tv.codely.shared.infrastructure.rabbitmq

import tv.codely.shared.infrastructure.bus.rabbitmq.RabbitMqChannelFactory

final class RabbitMqMessagePurger(channelFactory: RabbitMqChannelFactory)(queueName: String) extends MessagePurger {
  private val channel = channelFactory.channel

  override def purgeQueue(): Unit = channel.queuePurge(queueName)
}
