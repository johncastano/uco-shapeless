import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "uco.shapeless",
      scalaVersion := "2.12.4",
      version := "0.1.0-SNAPSHOT"
    )
  ),
  fork in run := true,
  name := "shapeless-uco",
  libraryDependencies ++= Seq(
    "com.chuusai" %% "shapeless" % "2.3.2",
    "io.circe"    %% "circe-core" % "0.7.0-M1",
    "io.circe"    %% "circe-generic" % "0.7.0-M1",
    "io.circe"    %% "circe-parser" % "0.7.0-M1",
    scalaTest     % Test
  )
)
scalafmtOnCompile in ThisBuild := true
