package com.y2pan.practice.logMaker

import java.io.FileInputStream
import java.util.concurrent.{ExecutorService, Executors, TimeUnit}
import java.util.{Properties, UUID}

import com.y2pan.practice.logMaker.generator._
import org.slf4j.LoggerFactory
import sun.misc.{Signal, SignalHandler}

import scala.concurrent.{ExecutionContext, Future}

object LogMaker extends App with SignalHandler {
  private val logger = LoggerFactory getLogger LogMaker.getClass
  private val poolSize: Int = 10
  private val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)
  private val properties = new Properties()
  properties.load(LogMaker.getClass.getResourceAsStream("/application.properties"))

  private var count: Int = properties.getProperty("count").toInt
  private val timeDuration: Int = properties.getProperty("timeDuration").toInt
  private val path: String = properties.getProperty("path")
  private var start: Boolean = true

  Signal.handle(new Signal("INT"), this)
  Signal.handle(new Signal("TERM"), this)

  val service = Executors.newFixedThreadPool(1)

  service.execute(() => {
    logger info "thread start"
    count = if (count == 0) Int.MaxValue else count
    implicit val ec = ExecutionContext.fromExecutor(pool)
    for (i <- 1 to count if start) {
      try {
        for (i <- 1 to 10 if start) {
          Future {
            logger info f"start create process $count%d:$i%d"
            val processId: String = UUID.randomUUID().toString
            for (t1 <- T1Generator.generate(path, timeDuration, processId); t20 <- T20Generator.generate(path, timeDuration, t1);
                 t21 <- T21Generator.generate(path, timeDuration, t20); t22 <- T22Generator.generate(path, timeDuration, t21);
                 t2p <- T2pGenerator.generate(path, timeDuration, t22); t3 <- T3Generator.generate(path, timeDuration, t2p)) yield t1 + t20 + t21 + t22 + t2p + t3
          }
        }
        Thread.sleep(5000)
      } catch {
        case e: InterruptedException => logger info "thread stopped"
      }
    }
    shutdownNow()
  })


  service.awaitTermination(Long.MaxValue, TimeUnit.DAYS)
  logger info "service terminated done"

  override def handle(signal: Signal): Unit = {
    if (signal.getName.equals("TERM") || signal.getName.equals("INT")) {
      logger info "TERM exit"
      shutdownNow()
    }
    else {
      logger info "Other exit"
      shutdownNow()
    }
  }

  def shutdownNow(): Unit = {
    LogMaker.start = false
    T1Generator.shutdownNow()
    T20Generator.shutdownNow()
    T21Generator.shutdownNow()
    T22Generator.shutdownNow()
    T2pGenerator.shutdownNow()
    T3Generator.shutdownNow()
    pool.shutdownNow()
    LogMaker.service.shutdownNow()
  }
}