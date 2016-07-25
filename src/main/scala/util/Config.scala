package util

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}
import net.ceedubs.ficus.Ficus._

/**
  * @author ynupc
  *         Created on 2016/07/25
  */
object Config {
  final private[this] var config: Config = ConfigFactory.load()

  def set(configFile: File): Unit = {
    config = ConfigFactory.load(ConfigFactory.parseFile(configFile))
  }

  final lazy val resourcesDir: String = config.as[Option[String]]("resourcesDir").getOrElse("../../src/main/resources/")
}
