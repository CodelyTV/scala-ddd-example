package tv.codely.scala_http_api.module.shared.infrastructure.message_broker.rabbitmq

import com.newmotion.akka.rabbitmq.ConnectionFactory
import com.rabbitmq.client.Channel
import tv.codely.scala_http_api.module.shared.infrastructure.config.MessageBrokerConfig

final class RabbitMqChannelFactory(config: MessageBrokerConfig) {
  val channel: Channel = {
    val factory = new ConnectionFactory()
    factory.setHost(config.host)
    factory.setPort(config.port)
    factory.setUsername(config.user)
    factory.setPassword(config.password)

    val connection = factory.newConnection()

    connection.createChannel()
  }
}
