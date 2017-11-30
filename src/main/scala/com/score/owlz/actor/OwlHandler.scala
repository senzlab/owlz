package com.score.owlz.actor

import akka.actor.{Actor, Props}
import com.score.owlz.actor.OwlHandler.PutOwl
import com.score.owlz.comp.OwlDbCompImpl
import com.score.owlz.config.{AppConf, DbConf}
import com.score.owlz.protocol.{Msg, Owl, User}
import com.score.owlz.util.SenzLogger

object OwlHandler {

  def props = Props(classOf[OwlHandler])

  case class PutOwl(sender: String, attr: Map[String, String])

}

class OwlHandler extends Actor with OwlDbCompImpl with DbConf with AppConf with SenzLogger {
  val senzActor = context.actorSelection("/user/SenzActor")

  override def preStart(): Unit = {
    logger.debug("Start actor: " + context.self.path)
  }

  override def receive: Receive = {
    case PutOwl(sen, attr) =>
      val phn = attr.get("#phn")
      val from = attr("#from")
      val to = attr("#to")
      val date = attr("#date")
      val desc = attr.get("#desc")
      val recv = attr("#recv").toBoolean
      logger.info(s"new owl user: $sen from: $from to: $to date $date desc $desc receive $recv")

      // create owl
      owlDb.createOwl(Owl(user = User(sen, phn), from = from, to = to, date = date, desc = desc, recv = recv))

      // todo find matching owls and send them back
      senzActor ! Msg(s"DATA #owl @$sen")

      // todo notify about new owl to other matching(from, to) users
      senzActor ! Msg("DATA #owl <other users>")
    case msg =>
      logger.error(s"unexpected message $msg")
  }

}
