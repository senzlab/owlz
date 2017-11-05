package com.score.owlz.comp

import com.score.owlz.protocol.Owl


trait OwlDbComp {

  val owlDb: OwlDb

  trait OwlDb {
    def createOwl(owl: Owl): Unit

    def getOwl()

    def getOwls(from: String, to: String, date: String)
  }

}

