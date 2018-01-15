package com.y2pan.practice

import scala.collection.mutable.ArrayBuffer
import scala.language.dynamics


class Fraction(n: Int, d: Int) {
  private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d)
  private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d)

  override def toString: String = s"$num/$den"

  def sign(a: Int): Int = if (a > 0) 1 else if (a < 0) -1 else 0

  def gcd(a: Int, b: Int): Int = if (b == 0) scala.math.abs(a) else gcd(b, a % b)

  def +(f: Fraction): Fraction = {
    new Fraction(this.num * f.den + f.num * this.den, this.den * f.den)
  }

  def -(f: Fraction): Fraction = {
    new Fraction(this.num * f.den - f.num * this.den, this.den * f.den)
  }

  def *(f: Fraction): Fraction = {
    new Fraction(this.num * f.num, this.den * f.den)
  }

  def /(f: Fraction): Fraction = {
    new Fraction(this.num * f.den, this.den * f.num)
  }
}

class Money(d: Int, c: Int) {
  private val dollar: Int = if (c > 100) d + c / 100 else d
  private val cent: Int = if (c > 100) c % 100 else c

  def +(that: Money): Money = {
    new Money(this.dollar + that.dollar, this.cent + that.cent)
  }

  override def toString: String = s"$$$dollar.$cent"

  def -(that: Money): Money = {
    if (this < that) new Money(0, 0)
    else if (this.cent < that.cent) new Money(this.dollar - that.dollar - 1, this.cent + 100 - that.cent)
    else new Money(this.dollar - that.dollar, this.cent - that.cent)
  }

  def ==(that: Money): Boolean = {
    this.dollar == that.dollar && this.cent == that.cent
  }

  def <(that: Money): Boolean = {
    if (this.dollar == that.dollar) this.cent < that.cent
    else this.dollar < that.dollar
  }
}

class Table {
  private val td: ArrayBuffer[String] = ArrayBuffer()
  private val tr: ArrayBuffer[Int] = ArrayBuffer()

  def |(content: String): Table = {
    if (tr.isEmpty) tr += 0
    td += content
    this
  }

  def ||(content: String): Table = {
    tr += td.size
    this | content
  }

  def apply: Table = new Table()

  override def toString: String = {
    var str: String = "<table>"
    for (i <- tr.indices) {
      val s = tr(i)
      val e = if (i < tr.length - 1) tr(i + 1) else td.length
      str += "<tr>"
      for (j <- s until e) {
        str += "<td>"
        str += td(j)
        str += "</td>"
      }
      str += "</tr>"
    }
    str
  }
}

object Table {
  def apply(): Table = new Table()
}


class ASCIIArt(art: String) {
  private val s = art.split("\\n")

  override def toString: String = {
    s.mkString("\n")
  }

  def +(that: ASCIIArt): ASCIIArt = {
    val art = new Array[String](if (s.length > that.s.length) s.length else that.s.length)
    val maxL = s.maxBy(s => s.length).length
    for (i <- s.indices if i < that.s.length) {
      val diff = (0 to (maxL - s(i).length)).map(i => " ").mkString("")
      art(i) = s(i) + diff + that.s(i)
    }
    new ASCIIArt(art.mkString("\n"))
  }
}

class BigSequence {
  var num = new Array[Int](64)
  for (i <- num.indices) {
    num(i) = -1
  }

  def pack(): Long = {
    num.filter(_ >= 0).mkString.toLong
  }
}

object BigSequence {
  def apply(num: Int): BigSequence = {
    val b = new BigSequence()
    var i = 0
    num.toString.foreach {
      n =>
        b.num(i) = n.getNumericValue
        i += 1
    }
    b
  }

  def update(num: Int, value: Int): Unit = {
    this.apply(num)
    println(value)
  }
}

class Matrix(x: Int, y: Int) {
  private val m = x
  private val n = y
  private var mat: Array[Array[Int]] = Array.ofDim[Int](m, n)
  for (i <- 0 until m) {
    for (j <- 0 until n) {
      mat(i)(j) = 1
    }
  }

  override def toString: String = {
    var str = ""
    for (i <- 0 until m) {
      str += "\n"
      for (j <- 0 until n) {
        str += mat(i)(j) + " "
      }
    }
    str
  }

  def +(that: Matrix): Matrix = {
    if (this.m == that.m && this.n == that.n) {
      var mat: Array[Array[Int]] = Array.ofDim[Int](m, n)
      for (i <- 0 until m) {
        for (j <- 0 until n) {
          mat(i)(j) = this.mat(i)(j) + that.mat(i)(j)
        }
      }
      val r = new Matrix(m, n)
      r.mat = mat
      r
    } else {
      throw new Exception("can't plus")
    }
  }

  def *(that: Matrix): Matrix = {
    if (this.n == that.m) {
      val r = new Matrix(m, that.n)
      var mat: Array[Array[Int]] = Array.ofDim[Int](m, that.n)
      for (i <- 0 until m) {
        for (j <- 0 until that.n) {
          var v = 0
          for (k <- 0 until n) {
            for (l <- 0 until n) {
              v += this.mat(i)(k) * that.mat(l)(j)
            }
          }
          mat(i)(j) = v
        }
      }
      r.mat = mat
      r
    } else {
      throw new Exception("can't multiply")
    }
  }

  def *(that: Int): Matrix = {
    var mat: Array[Array[Int]] = Array.ofDim[Int](m, n)
    for (i <- 0 until m) {
      for (j <- 0 until n) {
        mat(i)(j) = this.mat(i)(j) * that
      }
    }
    val r = new Matrix(m, n)
    r.mat = mat
    r
  }
}

object Matrix {
  def apply(x: Int, y: Int): Matrix = new Matrix(x, y)
}

class RichFile(val path: String) {

}

object RichFile {
  private var fileName: String = _
  private var path: String = _

  def apply(path: String): RichFile = new RichFile(path)

  def unapply(richFile: RichFile): Option[(String, String, String)] = {
    if (richFile.path == null) {
      None
    } else {
      val reg = "([/\\w+]+)/(\\w+)\\.(\\w+)".r
      val reg(r1, r2, r3) = richFile.path
      Some((r1, r2, r3))
    }
  }

  def unapplySeq(richFile: RichFile): Option[Seq[String]] = {
    if (richFile.path == null) {
      None
    } else {
      Some(richFile.path.split("/"))
    }
  }
}

class DynamicProps(val props: java.util.Properties) extends scala.Dynamic {
  private var key: String = ""
  private var value: String = ""
  def updateDynamic(name: String)(value: String): Unit = {
    props.setProperty(name.replaceAll("_", "."), value)
  }

  def selectDynamic(name: String): DynamicProps = {
    if (key !=null && !key.trim.isEmpty) key += "."
    key += name
    value = props.getProperty(key)
    this
  }

  override def toString: String = value
}

object Practice11 extends App {
  val s = scala.math.sqrt _

  println(s(2))

  val f1 = new Fraction(1, 2)
  val f2 = new Fraction(7, 8)

  println(f1)
  println(f2)
  println(f1 + f2)
  println(f1 - f2)
  println(f1 * f2)
  println(f1 / f2)

  val m1 = new Money(1, 75)
  val m2 = new Money(0, 50)
  println(m1)
  println(m2)
  println(m1 + m2)
  println(m1 - m2)
  println(m1 < m2)
  println(m1 + m2 == new Money(2, 25))

  println(Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET")

  val a = new ASCIIArt(" /\\_/\\\n( ' ' )\n(  -  )\n | | | \n(__|__)")
  val b = new ASCIIArt("    -----    \n  / Hello \\ \n <  Scala  | \n  \\ Coder / \n   -----    ")
  println(a + b)

  val big = BigSequence(10100)
  println(big.pack())
  BigSequence(200) = 300

  val mat1 = Matrix(3, 2)
  val mat2 = Matrix(2, 3)
  println(mat1 + mat1)
  println(mat1 * mat2)
  println(mat1 * mat2 * 2)

  val richFile = RichFile("/home/cay/readme.txt")
  val RichFile(r1, r2, r3) = richFile
  val RichFile(r) = richFile
  println(r)
  println(r1)
  println(r2)
  println(r3)

  val sysProps = new DynamicProps(System.getProperties)
  val home = sysProps.java.home
  println(home)
  println(System.getProperties.getProperty("java.home"))

}