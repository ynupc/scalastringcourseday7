name := "scala string course"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-encoding", "UTF-8")

javacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % "test" withSources() withJavadoc(),
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test" withSources() withJavadoc(),
  "net.ceedubs" % "ficus_2.11" % "1.1.2" withSources() withJavadoc()
)