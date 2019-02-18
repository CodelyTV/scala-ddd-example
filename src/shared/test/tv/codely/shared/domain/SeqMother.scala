package tv.codely.shared.domain

object SeqMother {
  val maximumElements = 10

  def randomOf[T](apply: => T): Seq[T] = (0 to IntMother.randomBetween(1, maximumElements)).map(_ => apply)
}
