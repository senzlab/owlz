package com.score.owlz.actor

import akka.actor.{Actor, Props}
import com.score.owlz.comp.ChainDbCompImpl
import com.score.owlz.config.AppConf
import com.score.owlz.util.SenzLogger

object TransHandler {

  def props = Props(classOf[TransHandler])

}

class TransHandler extends Actor with ChainDbCompImpl with AppConf with SenzLogger {
  val senzActor = context.actorSelection("/user/SenzActor")

  override def preStart(): Unit = {
    logger.debug("Start actor: " + context.self.path)
  }

  override def receive: Receive = {
    case msg =>
      logger.error(s"unexpected message $msg")
  }

}
