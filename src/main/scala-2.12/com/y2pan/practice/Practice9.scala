package com.y2pan.practice

import java.io.{File, PrintWriter}

import scala.io.Source

object Practice9 extends App {
  val source = Source.fromFile("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/myfile.txt", "UTF-8")
  val words = source.getLines().toArray

  val out = new PrintWriter("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/myfile1.txt")

  for (l <- words.reverse) {
    println(l)
    out.println(l)
  }
  out.close()
  var source2 = Source.fromFile("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/myfile2.txt")
  val arr = source2.getLines.toArray
  val max = arr.map { x => x.split("\t")(0).length() }.max
  println(max)
  println("")
  var arr2 = for (i <- arr.indices) yield {
    val num = max - arr(i).split("\t")(0).length() + 1
    print(num)
    arr(i).replaceAll("\t", "-" * num)
  }
  arr2.foreach {
    println
  }

  var source3 = Source.fromFile("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/myfile2.txt")
  println(source3.getLines().toArray.map(word => word.replaceAll("\\s+", "")).filter(word => word.length >= 12).mkString("|"))

  var source4 = Source.fromFile("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/myfile3.txt")
  val nums = source4.mkString.split("\\s+")
  println(nums.mkString("|"))
  println(nums.filter(n => !n.trim.equals("") && n.matches("""\d+""".r.toString())).map(n => n.trim.toDouble).sum)
  println(nums.filter(n => !n.trim.equals("") && n.matches("""\d+""".r.toString())).map(n => n.trim.toDouble).max)
  println(nums.filter(n => !n.trim.equals("") && n.matches("""\d+""".r.toString())).map(n => n.trim.toDouble).min)
  println(nums.filter(n => !n.trim.equals("") && n.matches("""\d+""".r.toString())).map(n => n.trim.toDouble).sum / nums.count(n => !n.trim.equals("")))

  val pw = new PrintWriter("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/test.txt")

  for (n <- 0 to 20) {
    val t = BigDecimal(2).pow(n)
    pw.write(t.toString())
    pw.write("\t\t")
    pw.write((1 / t).toString())
    pw.write("\n")
  }

  pw.close()
  println()

  val numPattern = """[^\.]+""".r
  val imgPattern = """<img.*>""".r
  nums.foreach(num => {
    if (numPattern.findFirstIn(num).isDefined) {
      println(numPattern.findFirstIn(num).get)
    }
    if (imgPattern.findFirstIn(num.trim).isDefined) {
      println(s"img:$num")
    }
  })

  import scala.sys.process._
  "pwd".!
  "grep '.class' -r . ".!
  val dir = new File("/Users/test/project/spark/rdb-server-log-maker/")
  println(dir.listFiles().filter(_.isFile).count(_.toString.endsWith(".class")))

}

