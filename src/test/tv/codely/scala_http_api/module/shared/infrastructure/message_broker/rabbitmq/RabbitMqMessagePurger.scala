package tv.codely.scala_http_api.module.shared.infrastructure.message_broker.rabbitmq

import tv.codely.scala_http_api.module.shared.bus.infrastructure.rabbit_mq.RabbitMqChannelFactory

final class RabbitMqMessagePurger(channelFactory: RabbitMqChannelFactory)(queueName: String) extends MessagePurger {
  private val channel = channelFactory.channel

  override def purgeQueue(): Unit = channel.queuePurge(queueName)
}
