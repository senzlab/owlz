package com.score.owlz

import akka.actor.ActorSystem
import com.score.owlz.actor.SenzActor
import com.score.owlz.util.{ChainFactory, DbFactory}

object Main extends App {

  // first
  //  1. setup logging
  //  2. setup keys
  //  3. setup db
  ChainFactory.setupLogging()
  ChainFactory.setupKeys()
  DbFactory.initDb()

  // start senz, block creator
  implicit val system = ActorSystem("senz")
  system.actorOf(SenzActor.props, name = "SenzActor")

}
