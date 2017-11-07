val sampleStringTask = taskKey[String]("A sample string task.")
val sampleIntTask = taskKey[Int]("A sample int task.")
val makeGitHubRelease = taskKey[Unit]("Makes a GitHub release")

lazy val commonSettings = Seq(
  name := "spark-bulba",
  version := "0.0.9",
  scalaVersion := "2.11.8",
  sparkVersion := "2.2.0"
)

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

lazy val library = (project in file("library"))
  .settings(
    commonSettings,
    sampleStringTask := System.getProperty("user.home"),
    sampleIntTask := {
      val sum = 1 + 2
      println("sum: " + sum)
      sum
    },
    makeGitHubRelease := {
      scala.sys.process.Process(s""" git commit -am "Version bump to ${version.value}" """.trim).!
//      scala.sys.process.Process(s""" git add ." """.trim).!
//      scala.sys.process.Process(s""" git commit -m "Version bump to ${version.value}" """.trim).!
//      scala.sys.process.Process("git commit -am 'Version bump to $1'").!
    }
  )
