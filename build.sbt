name := "CodelyTV Scala HTTP API"
version := "1.0"

disablePlugins(AssemblyPlugin)

lazy val root = (project in file(".")).aggregate(app, shared, mooc, backoffice)

lazy val app = Project(id = "app", base = file("app/"))
               .dependsOn(mooc % "compile->compile;test->test")
               .dependsOn(backoffice % "compile->compile;test->test")
               .dependsOn(shared % "compile->compile;test->test")

lazy val shared = Project(id = "shared", base = file("src/shared"))

lazy val mooc = Project(id = "mooc", base = file("src/mooc")).dependsOn(shared % "compile->compile;test->test")
lazy val backoffice =
  Project(id = "backoffice", base = file("src/backoffice")).dependsOn(shared % "compile->compile;test->test")

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

lazy val pack = taskKey[Unit]("Packages application as a fat jar")
pack := {
  (assembly in app).toTask.value
}

test in assembly := {}
