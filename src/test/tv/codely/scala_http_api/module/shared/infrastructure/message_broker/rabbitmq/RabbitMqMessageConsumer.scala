package tv.codely.scala_http_api.module.shared.infrastructure.message_broker.rabbitmq
import com.newmotion.akka.rabbitmq.{BasicProperties, ConnectionFactory, DefaultConsumer, Envelope}
import tv.codely.scala_http_api.module.shared.domain.Message
import tv.codely.scala_http_api.module.shared.infrastructure.config.MessageBrokerConfig
import tv.codely.scala_http_api.module.shared.infrastructure.marshaller.MessageJsonFormatMarshaller._
import spray.json._

final class RabbitMqMessageConsumer(brokerConfig: MessageBrokerConfig)(queueName: String) extends MessageConsumer {
  private val factory = new ConnectionFactory()
  factory.setHost(brokerConfig.host)
  factory.setPort(brokerConfig.port)
  factory.setUsername(brokerConfig.user)
  factory.setPassword(brokerConfig.password)
  private val connection = factory.newConnection()
  private val channel    = connection.createChannel()

  override def startConsuming(handler: Message => Boolean): Unit = {
    val consumer = new DefaultConsumer(channel) {
      override def handleDelivery(
          consumerTag: String,
          envelope: Envelope,
          properties: BasicProperties,
          body: Array[Byte]
      ) {
        val stringBody                 = new String(body)
        val message                    = stringBody.parseJson.convertTo[Message]
        val hasBeenHandledSuccessfully = handler(message)

        if (hasBeenHandledSuccessfully) {
          val waitForMultipleAckToSendThem = false
          channel.basicAck(envelope.getDeliveryTag, waitForMultipleAckToSendThem)
        }
      }
    }

    val autoAckAfterConsume = false

    channel.basicConsume(queueName, autoAckAfterConsume, consumer).map(_ => ())
  }

  override def hasMessagesToConsume: Boolean = channel.messageCount(queueName) > 0
}
