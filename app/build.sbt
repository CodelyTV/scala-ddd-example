Configuration.commonSettings

scalaSource in Compile := baseDirectory.value / "main/"
scalaSource in Test := baseDirectory.value / "test/"
resourceDirectory in Compile := baseDirectory.value / "conf"

libraryDependencies ++= Dependencies.shared

fork in run := true

// Assembly
mainClass in assembly := Some("tv.codely.Launcher")
assemblyJarName in assembly := "codelytv.jar"
assemblyOutputPath in assembly := baseDirectory.value / ".." / "package" / (assemblyJarName in assembly).value
test in assembly := {}
