package com.y2pan.practice.logMaker.generator

import java.util.concurrent.{ExecutorService, Executors}

import org.slf4j.{Logger, LoggerFactory}

object T1Generator extends Generator {
  val name = "T1"
  val wholeName = "snippetAnalyzer"
  val logger: Logger = LoggerFactory getLogger T1Generator.getClass
  val poolSize: Int = 10
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)
}

object T20Generator extends Generator {
  val name = "T2.0"
  val wholeName = "foregroundDBMerger"
  val logger: Logger = LoggerFactory getLogger T20Generator.getClass
  val poolSize: Int = 10
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)
}

object T21Generator extends Generator {
  val name = "T2.1"
  val wholeName = "foregroundDBUpdater"
  val logger: Logger = LoggerFactory getLogger T21Generator.getClass
  val poolSize: Int = 10
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)
}

object T22Generator extends Generator {
  val name = "T2.2"
  val wholeName = "vehicleDBGenerator"
  val logger: Logger = LoggerFactory getLogger T22Generator.getClass
  val poolSize: Int = 10
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)
}

object T2pGenerator extends Generator {
  val name = "T2.0`"
  val wholeName = "roadGeometryMerger"
  val logger: Logger = LoggerFactory getLogger T2pGenerator.getClass
  val poolSize: Int = 10
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)
}

object T3Generator extends Generator {
  val name = "T3"
  val wholeName = "logicInfoExtractor"
  val logger: Logger = LoggerFactory getLogger T3Generator.getClass
  val poolSize: Int = 10
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)
}
