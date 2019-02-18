// SHARED
disablePlugins(AssemblyPlugin)

Configuration.commonSettings

scalaSource in Compile := baseDirectory.value / "main/"
scalaSource in Test := baseDirectory.value / "test/"
resourceDirectory in Compile := baseDirectory.value / "conf"

libraryDependencies ++= Dependencies.shared

TaskKey[Unit]("createDbTables") := (runMain in Compile)
  .toTask(" tv.codely.shared.infrastructure.environment.DbTablesCreator")
  .value

test in assembly := {}
