val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
      name := "Minesweeper",
      version := "0.1.0-SNAPSHOT",

      scalaVersion := scala3Version,

      libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
      libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test",
      libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
      libraryDependencies += "net.codingwell" %% "scala-guice" % "7.0.0"

  )
