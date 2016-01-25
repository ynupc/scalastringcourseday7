name := "scala string course"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-encoding", "UTF-8")

javacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)