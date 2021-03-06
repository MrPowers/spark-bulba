name := "spark-bulba"

version := "5.3.0"

scalaVersion := "2.11.8"

sparkVersion := "2.2.0"

sparkComponents ++= Seq("sql")

spDependencies += "mrpowers/spark-daria:2.2.0_0.12.0"

libraryDependencies += "MrPowers" % "spark-fast-tests" % "2.2.0_0.5.0" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:+CMSClassUnloadingEnabled")

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  artifact.name + "_" + sv.binary + "-" + sparkVersion.value + "_" + module.revision + "." + artifact.extension
}
