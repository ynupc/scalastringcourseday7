package util

import java.io.File
import java.nio.file.{Path, Paths}

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

  private val defaultPath: String = "src/main/resources"

  final lazy val resourcesDir: String = {
    val path: String = config.as[Option[String]]("resourcesDir").getOrElse(defaultPath)
    val dir: File = new File(path)
    if (dir.canRead && dir.isDirectory) {
      dir.toString
    } else {
      defaultPath
    }
  }

  def resourceFile(filename: String*): Path = {
    val file: File = Paths.get(resourcesDir, filename: _*).toAbsolutePath.toFile
    if (file.canRead && file.isFile) {
      file.toPath
    } else {
      Paths.get(defaultPath, filename: _*).toAbsolutePath
    }
  }

  final lazy val nGram: Int = config.as[Option[Int]]("concept.nGram.n") match {
    case Some(n) if 1 <= n =>
      n
    case otherwise =>
      1
  }

  final lazy val nGramGap: Int = config.as[Option[Int]]("concept.nGram.gap") match {
    case Some(gap) if 0 <= gap =>
      gap
    case Some(gap) if gap < 0 =>
      Int.MaxValue
    case otherwise =>
      0
  }

  final lazy val tokenizer: String = config.as[Option[String]]("concept.tokenizer").getOrElse("CharacterNGram")
}
