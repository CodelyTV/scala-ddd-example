name := "CodelyTV Scala HTTP API"
version := "1.0"

Configuration.settings

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6")

libraryDependencies ++= Dependencies.production
libraryDependencies ++= Dependencies.test

enablePlugins(JavaAppPackaging) // App packaging. More info: https://github.com/sbt/sbt-native-packager

addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("tq", "testQuick")
addCommandAlias("tsf", "testShowFailed")

addCommandAlias("c", "compile")
addCommandAlias("tc", "test:compile")

addCommandAlias("f", "scalafmt")             // Format production files according to ScalaFmt
addCommandAlias("fc", "scalafmtCheck")       // Check if production files are formatted according to ScalaFmt
addCommandAlias("tf", "test:scalafmt")       // Format test files according to ScalaFmt
addCommandAlias("tfc", "test:scalafmtCheck") // Check if test files are formatted according to ScalaFmt

// All the needed tasks before pushing to the repository (compile, compile test, format check in prod and test)
addCommandAlias("prep", ";c;tc;fc;tfc")

TaskKey[Unit]("createDbTables") := (runMain in Compile)
  .toTask(" tv.codely.scala_http_api.entry_point.cli.DbTablesCreator")
  .value
