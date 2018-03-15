package tv.codely.scala_http_api.effects.bus.rabbitmq

import com.newmotion.akka.rabbitmq.{BasicProperties, DefaultConsumer, Envelope}
import tv.codely.scala_http_api.application.akkaHttp.marshaller.MessageJsonFormatMarshaller._
import spray.json._
import tv.codely.scala_http_api.effects.bus.api.Message
import tv.codely.scala_http_api.effects.bus.rabbit_mq.RabbitMqChannelFactory

final class RabbitMqMessageConsumer(channelFactory: RabbitMqChannelFactory)(queueName: String) extends MessageConsumer {
  private val channel = channelFactory.channel

  override def startConsuming(handler: Message => Boolean): Unit = {
    val consumer = new DefaultConsumer(channel) {
      override def handleDelivery(
          consumerTag: String,
          envelope: Envelope,
          properties: BasicProperties,
          body: Array[Byte]
      ): Unit = {
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
