package com.score.owlz.util

import ch.qos.logback.classic.{Level, Logger}
import com.score.owlz.config.AppConf
import org.slf4j.LoggerFactory

object ChainFactory extends AppConf {
  val setupLogging = () => {
    val rootLogger = LoggerFactory.getLogger("root").asInstanceOf[Logger]

    senzieMode match {
      case "DEV" =>
        rootLogger.setLevel(Level.DEBUG)
      case "PROD" =>
        rootLogger.setLevel(Level.INFO)
      case _ =>
        rootLogger.setLevel(Level.INFO)
    }
  }

  val setupKeys = () => {
    RSAFactory.initRSAKeys()
  }
}
