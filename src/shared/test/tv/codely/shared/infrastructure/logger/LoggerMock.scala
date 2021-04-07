package tv.codely.shared.infrastructure.logger

import org.scalamock.scalatest.MockFactory
import tv.codely.shared.domain.logger.Logger
import tv.codely.shared.infrastructure.unit.UnitTestCase

trait LoggerMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val logger: Logger = mock[Logger]

  protected def loggerShouldInfo(): Unit =
    (logger.info(_: String, _: Map[String, Any]))
        .expects(*, *)
        .returning(Unit)
}
