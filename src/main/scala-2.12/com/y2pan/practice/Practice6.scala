package com.y2pan.practice

import java.awt.Point

object Practice6 extends App {
  val point1 = Point1(3, 4)
  println(point1)

  if (args.length > 0) println(args.reverse.mkString(":"))

  println(Poker)

  println(Poker.isRed(Poker.M))
  println(Poker.isRed(Poker.H))

  println(RGB.RED.id)
}

abstract class UnitConversions {
  def inchesToCentimeters() {}

  def gallonsToLiters() {}

  def milesToKilometers() {}
}

object InchesToCentimeters extends UnitConversions {
  override def inchesToCentimeters(): Unit = {}
}

object GallonsToLiters extends UnitConversions {
  override def gallonsToLiters(): Unit = {}
}

object MilesToKilometers extends UnitConversions {
  override def milesToKilometers(): Unit = {}
}

object Origin extends java.awt.Point {
  override def getLocation: Point = super.getLocation
}

class Point1 private(x: Int, y: Int) {
  override def toString: String = x + ":" + y
}

object Point1 {
  def apply(x: Int, y: Int): Point1 = new Point1(x, y)
}

object Poker extends Enumeration {
  type Poker = Value
  val M = Value("♣")
  val T = Value("♠")
  val H = Value("♥")
  val F = Value("♦")

  override def toString(): String = {
    M + "-" + T + "-" + H + "-" + F
  }

  def isRed(poker: Poker): Boolean = {
    poker == H || poker == F
  }
}

object RGB extends Enumeration {
  val RED = Value(0xff0000, "Red")
  val BLACK = Value(0x000000, "Black")
  val GREEN = Value(0x00ff00, "Green")
  val CYAN = Value(0x00ffff, "Cyan")
  val YELLOW = Value(0xffff00, "Yellow")
  val WHITE = Value(0xffffff, "White")
  val BLUE = Value(0x0000ff, "Blue")
  val MAGENTA = Value(0xff00ff, "Magenta")
}