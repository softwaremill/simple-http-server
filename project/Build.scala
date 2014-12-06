import sbt.Keys._
import sbt._

object Resolvers {
  val projectResolvers = Seq(
    "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases/",
    "SotwareMill Public Releases" at "https://nexus.softwaremill.com/content/repositories/releases/"
  )
}

object BuildSettings {

  import Resolvers._

  val buildSettings = Defaults.coreDefaultSettings ++ Seq(

    organization := "pl.softwaremill",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.10.4",

    resolvers := projectResolvers,
    scalacOptions += "-unchecked"
  )

}
