import sbt._

lazy val foundationsScala = (project in file(".")).settings(
  name := "foundations",
  organization := "net.degoes",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.12.10"
)

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias(
  "check",
  "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck"
)

/* scala versions and options */
scalaVersion := "2.13.6"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-encoding",
  "UTF-8",
  "-Xlint",
  "-Xverify",
  "-feature",
  "-language:_"
)

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-source", "1.7", "-target", "1.7")

val ZIOVersion = "2.0.0-M4"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio"          % ZIOVersion,
  "dev.zio" %% "zio-streams"  % ZIOVersion,
  "dev.zio" %% "zio-test"     % ZIOVersion,
  "dev.zio" %% "zio-test"     % ZIOVersion % "test",
  "dev.zio" %% "zio-test-sbt" % ZIOVersion % "test"
)

testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))

resolvers ++= Seq(
  "Secured Central Repository" at "https://repo1.maven.org/maven2",
  Resolver.sonatypeRepo("snapshots")
)
