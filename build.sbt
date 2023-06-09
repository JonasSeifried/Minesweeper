val scala3Version = "3.3.0"

lazy val root = project
  .in(file("."))
  .settings(
      name := "Minesweeper",
      version := "0.1.0-SNAPSHOT",

      scalaVersion := scala3Version,

      libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.16",
      libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test",
      libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
      libraryDependencies += "net.codingwell" %% "scala-guice" % "7.0.0",
      libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.1.0",
      libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC7",

        jacocoCoverallsServiceName := "github-actions",
        jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
        jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
        jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")

  )
  .enablePlugins(JacocoCoverallsPlugin)
