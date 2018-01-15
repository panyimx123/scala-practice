package com.y2pan.practice

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Practice3 extends App {
  def makeArray(n: Int): Array[Int] = {
    val a = new Array[Int](n)
    for (i <- a) yield Random.nextInt(n)
  }

  var b = Array(1, 2, 3, 4, 5)

  for (i <- b.indices by 2 if i < b.length - 1) {
    val tmp = b(i)
    b(i) = b(i + 1)
    b(i + 1) = tmp
  }

  println(b.mkString(" and "))

  b = Array(1, 2, 3, 4, 5)

  val c = (for (i <- 0 until(b.length, 2))
    yield
      if (i < b.length - 1)
        Array(b(i + 1), b(i))
      else
        Array(b(i))).flatten.toArray

  println(c.mkString(" and "))


  var d = Array(1, -1, 2, 0, 5)

  d = d.sortWith((a, b) => if (a <= 0) false else if (b <= 0) true else a < b)
  println(d.mkString(" and "))

  var e = Array(3.4, 2.5, 1.2, 3.4d, 44.33)
  println(e.sum / e.length)

  val f = Array(1, 2, 3, 2, 2, 4, 2)
  val g = ArrayBuffer(1, 2, 3, 2, 2, 4, 2)
  println(f.reverse.mkString(" and "))
  println(g.reverse.mkString(" and "))
  println(f.distinct.mkString(" and "))

  val h = ArrayBuffer(1, 2, 3, 4, -1, -2, -3)

  var first: Boolean = true
  val indices = for (i <- h.indices if h(i) < 0) yield i

  indices.drop(0)
  for (i <- indices.reverse) h.remove(i)

  println(h.mkString(" and "))

  val o = java.util.TimeZone.getAvailableIDs()


  println(o.filter(_.startsWith("America/")).map(_.replaceFirst("America/", "")).sorted.mkString(" and "))

  import java.awt.datatransfer._

  val flavors = SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]
  val p = flavors.getNativesForFlavor(DataFlavor.imageFlavor).toArray()

  println(p.mkString(" and "))
}
