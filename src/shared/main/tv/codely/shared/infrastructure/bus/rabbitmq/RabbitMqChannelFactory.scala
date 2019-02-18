package tv.codely.shared.infrastructure.bus.rabbitmq

import com.newmotion.akka.rabbitmq.ConnectionFactory
import com.rabbitmq.client.Channel

final class RabbitMqChannelFactory(config: RabbitMqConfig) {
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
