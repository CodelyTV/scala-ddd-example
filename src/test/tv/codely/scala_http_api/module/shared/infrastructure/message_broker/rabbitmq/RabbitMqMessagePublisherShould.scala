package tv.codely.scala_http_api.module.shared.infrastructure.message_broker.rabbitmq

import org.scalatest.concurrent.Eventually
import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.shared.domain.Message
import tv.codely.scala_http_api.module.video.domain.VideoCreatedStub

import scala.concurrent.duration._

final class RabbitMqMessagePublisherShould extends IntegrationTestCase with Eventually {
  override implicit val patienceConfig: PatienceConfig = PatienceConfig(timeout = 1.second, interval = 50.millis)

  // @ToDo: Fix this test. Since the `MessagePublisher` publishes out the event asynchronously,
  // we validate that the queue is empty even before publishing the event.
  // Furthermore, the `assertConsumes` assertion could contain an invalid actual result that it will not fail.
  "publish VideoCreated domain events" in {
    val videoCreated = VideoCreatedStub.random

    messagePublisher.publish(videoCreated)

    videoCreatedQueueConsumer.startConsuming(handler = assertConsumes(videoCreated))

    waitUntilQueueIsEmpty()
  }

  private def assertConsumes(expectedMessage: Message)(consumedMessage: Message): Boolean = {
    consumedMessage shouldBe expectedMessage
    true
  }

  private def waitUntilQueueIsEmpty(): Unit = eventually(
    if (videoCreatedQueueConsumer.isEmpty) ()
    else throw new RuntimeException("Queue is not empty. Waiting a little bit more…")
  )
}
