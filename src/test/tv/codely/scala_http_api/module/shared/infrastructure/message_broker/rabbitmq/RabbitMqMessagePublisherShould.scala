package tv.codely.scala_http_api.module.shared.infrastructure.message_broker.rabbitmq

import org.scalatest.concurrent.Eventually
import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.shared.domain.Message
import tv.codely.scala_http_api.module.video.domain.VideoCreatedStub

import scala.collection.mutable
import scala.concurrent.duration._

final class RabbitMqMessagePublisherShould extends IntegrationTestCase with Eventually {
  override implicit val patienceConfig: PatienceConfig = PatienceConfig(timeout = 1.second, interval = 50.millis)

  private val queueName                                  = "codelytv_scala_api.video_created"
  private val videoCreatedQueuePurger: MessagePurger     = new RabbitMqMessagePurger(publisherConfig)(queueName)
  private val videoCreatedQueueConsumer: MessageConsumer = new RabbitMqMessageConsumer(publisherConfig)(queueName)

  private val consumedMessages: mutable.Buffer[Message] = mutable.Buffer.empty

  "publish VideoCreated domain events" in {
    videoCreatedQueuePurger.purgeQueue()
    waitUntilQueueIsEmpty()

    val videoCreated = VideoCreatedStub.random
    messagePublisher.publish(videoCreated)
    waitUntilQueueHasMessages()

    videoCreatedQueueConsumer.startConsuming(extractConsumedMessagesHandler)
    waitUntilQueueIsEmpty()

    consumedMessages.synchronized(consumedMessages shouldBe Seq(videoCreated))
  }

  private def extractConsumedMessagesHandler(consumedMessage: Message): Boolean = {
    consumedMessages.synchronized(consumedMessages += consumedMessage)
    val handledSuccessfully = true
    handledSuccessfully
  }

  private def waitUntilQueueHasMessages(): Unit = eventually(
    if (videoCreatedQueueConsumer.hasMessagesToConsume) ()
    else throw new RuntimeException("Queue has no messages. Waiting a little bit more…")
  )

  private def waitUntilQueueIsEmpty(): Unit = eventually(
    if (videoCreatedQueueConsumer.isEmpty) {
      // If the RabbitMQ queue doesn't has any message, it doesn't mean we're not processing the last ones.
      // Wait a little in order to let consuming and acknowledge these messages.
      // More info under the `message-count` domain concept: https://www.rabbitmq.com/amqp-0-9-1-reference.html#domains
      Thread.sleep(50)
    } else throw new RuntimeException("Queue is not empty. Waiting a little bit more…")
  )
}
