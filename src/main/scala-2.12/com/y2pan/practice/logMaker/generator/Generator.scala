package com.y2pan.practice.logMaker.generator

import java.io.{File, PrintWriter}
import java.nio.file.Files
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import java.util.concurrent.ExecutorService

import org.slf4j.Logger

import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source

trait Generator {
  val name: String
  val wholeName: String
  val logger: Logger
  val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
  val poolSize: Int
  val pool: ExecutorService

  def generateLogs(path: String, timeDuration: Int, processId: String): Unit = {
    val pFile = path + UUID.randomUUID().toString + ".log"
    val file = new File(path)
    if(!file.exists())
    {
      file.mkdirs()
    }
    val out = new PrintWriter(pFile)
    val startParamMock = UUID.randomUUID().toString
    out.println(f"[${LocalDateTime.now().format(formatter)}%s][$wholeName][info][${Thread.currentThread()}%s]==========$name,$wholeName,$processId%s param:$startParamMock%s START==========")


    val cFile = path + UUID.randomUUID().toString + ".log"
    val out1 = new PrintWriter(cFile)
    out1.println(f"[${LocalDateTime.now().format(formatter)}%s]$startParamMock%s$processId%s")

    val source = Source.fromFile("message.txt", "UTF-8")
    val lineIterator = source.getLines()
    for (l <- lineIterator) {
      out1.println(l)
    }
    Thread.sleep(timeDuration)
    out.println(f"[${LocalDateTime.now().format(formatter)}%s][$wholeName][info][${Thread.currentThread()}%s]==========$name,$wholeName,$processId%s return code:0 transactionID:43 FINISH==========")
    out.close()
    out1.close()
  }

  def generate(path: String, timeDuration: Int, processId: String): Future[String] = {
    implicit val ec = ExecutionContext.fromExecutor(pool)
    def future = Future {
      try {
        logger info f"$name%s START:$processId"
        generateLogs(path, timeDuration, processId)
        logger info f"$name%s FINISH:$processId"
      } catch {
        case e: InterruptedException => logger info f"${Thread.currentThread()}%s stopped";
      }

      UUID.randomUUID().toString
    }

    future
  }

  def shutdownNow(): Unit = {
    pool.shutdownNow()
  }

  def shutdown(): Unit = {
    pool.shutdown()
  }
}

