package com.score.owlz.comp

trait ChainDbCompImpl extends ChainDbComp {

  val chainDb = new ChainDbImpl

  class ChainDbImpl extends ChainDb {
    def createItem = {
    }

    def getItem = {
    }

    def getItems = {
    }

  }

}
