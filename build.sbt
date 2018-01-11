name := "Scala HTTP API"
version := "1.0"

Configuration.settings

libraryDependencies ++= Dependencies.production
libraryDependencies ++= Dependencies.test

addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("tq", "testQuick")
addCommandAlias("tsf", "testShowFailed")

addCommandAlias("c", "compile")
addCommandAlias("tc", "test:compile")

addCommandAlias("f", "scalafmt")       // Format all files according to ScalaFmt
addCommandAlias("fc", "scalafmtCheck") // Check if all files are formatted according to ScalaFmt

// All the needed tasks before pushing to the repository (compile, compile test & format check)
addCommandAlias("prep", ";c;tc;fc")

TaskKey[Unit]("createDbTables") := (runMain in Compile)
  .toTask(" tv.codely.scala_http_api.entry_point.cli.DbTablesCreator")
  .value
