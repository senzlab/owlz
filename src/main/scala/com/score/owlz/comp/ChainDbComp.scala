package com.score.owlz.comp


trait ChainDbComp {

  val chainDb: ChainDb

  trait ChainDb {
    def createItem(): Unit

    def getItem()

    def getItems()
  }

}

