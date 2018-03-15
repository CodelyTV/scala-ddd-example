package tv.codely.scala_http_api.entry_point.cli

import java.io.File
import java.sql.{Connection, DriverManager}

import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.effects.repositories.doobie.JdbcConfig

import scala.io.Source._
import scala.util.Try
import scala.util.matching.Regex

/**
  * View usage: runMain tv.codely.scala_http_api.entry_point.cli.DbTablesCreator --usage
  * Execute: runMain tv.codely.scala_http_api.entry_point.cli.DbTablesCreator
  */
object DbTablesCreator {
  private val databaseNameFromUrlRegex = new Regex("""\w+:\w+:\/\/\d+.\d+.\d+.\d+(?::\w+)?\/(\w+)""")

  case class CommandConfig(
      tablesFolder: String = "database",
      configFile: String = "application",
      dbConfigKey: String = "database"
  )

  def main(args: Array[String]): Unit = {
    val parser = new scopt.OptionParser[CommandConfig]("DbTablesCreator") {
      head("Build needed environment to run tests", "1.0")
      opt[String]('f', "tablesFolder")
        .action((tablesFolder, currentConfig) => currentConfig.copy(tablesFolder = tablesFolder))
        .text("Folder containing all the `.sql` files with the `CREATE TABLE` definitions.")

      opt[String]('c', "configFile")
        .action((configFile, currentConfig) => currentConfig.copy(configFile = configFile))
        .text("Configuration file to use while searching for the DB connection parameters.")

      opt[String]('k', "dbConfigKey")
        .action((dbConfigKey, currentConfig) => currentConfig.copy(dbConfigKey = dbConfigKey))
        .text("The configuration key inside the configuration file where we'll find the DB connection parameters.")
    }

    parser.parse(args, CommandConfig()).fold(println("[ERROR] Invalid parameters")) { commandConfig =>
      val dbConfig     = JdbcConfig(ConfigFactory.load("application").getConfig("database"))
      val dbNameOption = for (grouped <- databaseNameFromUrlRegex findFirstMatchIn dbConfig.url) yield grouped group 1

      dbNameOption.fold(
        println(s"[ERROR] We couldn't extract the DB name from the DB URL configuration parameter: ${dbConfig.url}")
      ) { dbName =>
        Try(Class.forName(dbConfig.driver)).toOption.fold(
          println(s"[ERROR] Invalid driver specified in the database config: ${dbConfig.driver}")
        ) { _ =>
          val connection = DriverManager.getConnection(dbConfig.url, dbConfig.user, dbConfig.password)

          createTables(dbName, commandConfig.tablesFolder, connection)

          connection.close()
        }
      }
    }
  }

  private def createTables(dbName: String, tablesFolder: String, connection: Connection): Unit = {
    val tablesFolderFile = new File(tablesFolder)
    val tablesFiles      = tablesFolderFile.listFiles()

    println(s"[INFO] Creating the following tables: ${tablesFiles.mkString(", ")}…")

    val createTablesQueries = tablesFiles.map(fromFile(_).getLines.mkString)

    val applySchemaStatement = connection.createStatement

    applySchemaStatement.executeUpdate(s"USE $dbName;")
    createTablesQueries.foreach(applySchemaStatement.executeUpdate)
  }
}
