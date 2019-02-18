package tv.codely

object Launcher {
  private val MoocApiArgument       = "mooc-api"
  private val MoocConsumersArgument = "mooc-consumers"

  private val BackofficeApiArgument       = "backoffice-api"
  private val BackofficeConsumersArgument = "backoffice-api"

  def main(args: Array[String]): Unit = {
    args(0) match {
      case MoocApiArgument       => MoocApiApp.start()
      case MoocConsumersArgument => MoocConsumerApp.start()

      case BackofficeApiArgument       => BackofficeApiApp.start()
      case BackofficeConsumersArgument => BackofficeConsumerApp.start()
    }
  }
}
