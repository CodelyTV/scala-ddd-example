package tv.codely.scala_http_api.module.shared.infrastructure.message_broker.rabbitmq

import com.newmotion.akka.rabbitmq.ConnectionFactory
import tv.codely.scala_http_api.module.shared.infrastructure.config.MessageBrokerConfig

final class RabbitMqMessagePurger(brokerConfig: MessageBrokerConfig)(queueName: String) extends MessagePurger {
  private val factory = new ConnectionFactory()
  factory.setHost(brokerConfig.host)
  factory.setPort(brokerConfig.port)
  factory.setUsername(brokerConfig.user)
  factory.setPassword(brokerConfig.password)
  private val connection = factory.newConnection()
  private val channel    = connection.createChannel()

  override def purgeQueue(): Unit = channel.queuePurge(queueName)
}
