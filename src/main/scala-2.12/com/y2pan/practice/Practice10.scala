package com.y2pan.practice

import java.awt.Point
import java.io.BufferedInputStream

object Practice10 extends App {
  val egg = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike {
    def translate(x: Int, y: Int): Unit = {
      this.x += x;
      this.y += y
    }

    def grow(x: Int, y: Int): Unit = {
      this.x += x;
      this.y += y
    }
  }
  egg.translate(3, 4)
  egg.grow(5, 6)

  println(egg.x)

  val p1 = new OrderedPoint(3, 4)
  val p2 = new OrderedPoint(5, 6)

  val p = Array(new OrderedPoint(3, 4), new OrderedPoint(3, 7), new OrderedPoint(3, 6))

  println(p.sortWith(_.compare(_) < 0).mkString(","))

  val plain = "我们是"

  println("明文为：" + plain)
  println("加密后为：" + new CryptoLogger().log(plain))
  println("加密后为：" + new CryptoLogger().log(plain, -3))

  //val pt = new java.awt.Point with PropertyChange
  val b = new BlueBird()
  b.walk()
  b.flywithnowing()
  b.fly()
}

trait RectangleLike {
  def translate(x: Int, y: Int)

  def grow(x: Int, y: Int)
}

class OrderedPoint(x: Int, y: Int) extends java.awt.Point(x, y) with scala.math.Ordered[Point] {
  def compare(that: Point): Int = {
    if (x == that.x) y - that.y else x - that.x
  }
}

trait Logger {
  def log(str: String, key: Int = 3): String
}

class CryptoLogger extends Logger {
  def log(str: String, key: Int): String = {
    for (i <- str) yield if (key >= 0) (97 + ((i - 97 + key) % 26)).toChar else (97 + ((i - 97 + 26 + key) % 26)).toChar
  }
}

trait PropertyChange extends java.beans.PropertyChangeSupport

trait Fly {
  def fly() {
    println("flying")
  }

  def flywithnowing()
}

trait Walk {
  def walk() {
    println("walk")
  }
}

class Bird {
  var name: String = _
}

class BlueBird extends Bird with Fly with Walk {
  def flywithnowing() {
    println("BlueBird flywithnowing")
  }
}


trait CustomBufferedInputStream extends BufferedInputStream {
  override def read(): Int = super.read()
}

class IterableInputStream extends java.io.InputStream with Iterable[Byte] {
  def read(): Int = 0

  def iterator: Iterator[Byte] = null
}