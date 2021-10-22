/**
 * Functional Scala uses functional programming, which emphasizes composing
 * programs from expressions that are referentially transparent, with
 * functions that are total and deterministic. This property, which is
 * sometimes called _purity_, fully inverts control in a functional program,
 * allowing the caller of functions complete control, which in turn makes
 * programs easier to understand, cheaper to safely modify, and easier to
 * test.
 *
 * In this module, you will explore purity and techniques for using purity
 * inside your applications.
 */
package net.degoes

import zio.test._
import zio.test.TestAspect._
import scala.annotation.tailrec
import java.time.temporal.ChronoUnit
import java.time.MonthDay
import scala.util.Random

object Purity extends DefaultRunnableSpec {
  def spec =
    suite("Purity") {
      suite("functions") {

        /**
         * EXERCISE
         *
         * Make the following function total.
         */
        test("total 1") {
          def reduce[A](elements: List[A], f: (A, A) => A): A = {
            @tailrec
            def loop(current: A, elements: List[A]): A =
              elements match {
                case Nil              => current
                case next :: elements => loop(f(current, next), elements)
              }

            loop(elements.head, elements.tail)
          }

          assertTrue(reduce[Int](List.empty[Int], _ + _) ne null)
        } @@ ignore +
          /**
           * EXERCISE
           *
           * Make the following function total.
           */
          test("total 2") {
            type Make = String

            def generateEmailSubject(make: Make, city: String, total: Double, start: MonthDay, end: MonthDay)
              : String = {
              val ppd = total / ChronoUnit.DAYS.between(end.atYear(2022), start.atYear(2022)).toDouble

              s"Don't lose your ${make} car rental on your trip to ${city}, for only ${ppd}!"
            }

            assertTrue(
              generateEmailSubject("Toyota Accord", "New York City", 295.00, MonthDay.now(), MonthDay.now()) != null
            )
          } @@ ignore +
          /**
           * EXERCISE
           *
           * Make the following function pure.
           */
          test("pure 1") {
            def normal(): Double = {
              val random1 = Random.nextDouble()
              val random2 = Random.nextDouble()

              -2.0 * Math.log(random1) * Math.cos(2 * Math.PI * random2)
            }

            assertTrue(normal() == normal())
          } @@ ignore +
          /**
           * EXERCISE
           *
           * Make the following function pure.
           *
           * WARNING: Advanced.
           */
          test("pure 2") {
            def getName(): String = {
              println("What is your name?")
              scala.io.StdIn.readLine()
            }

            assertTrue(getName() == getName())
          } @@ ignore
      }
    }
}

/**
 * Functional programming enables an unprecedented ability to reason about
 * and test code in principled ways. The secret to this power is so-called
 * referential transparency, which involves only writing code that computes
 * values, with all functions being total, deterministic, and free of side
 * effects.
 *
 * In this graduation project, you will explore the limits of pure stateful
 * computation, as you build out a data type to model stateful computations,
 * and use this to update an application from procedural style to
 * functional style.
 */
object PurityGraduation extends DefaultRunnableSpec {
  final case class Stateful[State, +A](compute: State => (State, A)) { self =>
    def map[B](f: A => B): Stateful[State, B] =
      self.flatMap(a => Stateful.succeed(f(a)))

    /**
     * EXERCISE
     *
     * Implement the `flatMap` method so that state is threaded through
     * both computations.
     */
    def flatMap[B](f: A => Stateful[State, B]): Stateful[State, B] = ???

    def zip[B](that: => Stateful[State, B]): Stateful[State, (A, B)] =
      for {
        a <- self
        b <- that
      } yield (a, b)
  }
  object Stateful {

    /**
     * EXERCISE
     *
     * Implement the `succeed` method in a way that does not change
     * state.
     */
    def succeed[S, A](a: => A): Stateful[S, A] = ???

    /**
     * EXERCISE
     *
     * Implement `get` to return the state unmodified.
     */
    def get[S]: Stateful[S, S] = ???

    /**
     * EXERCISE
     *
     * Implement `set` to set the state and return unit.
     */
    def set[S](s: S): Stateful[S, Unit] = ???

    def update[S](f: S => S): Stateful[S, S] =
      for {
        s <- Stateful.get[S]
        s <- Stateful.succeed(f(s))
        _ <- set(s)
      } yield s
  }

  final case class Element(
    tag: String,
    attributes: Map[String, String] = Map(),
    children: List[Element] = Nil
  )

  /**
   * Port this procedural example to functional Scala by using the `Stateful`
   * data type that you constructed above.
   */
  def render(element: Element): String = {
    var output: String = ""
    var indent: Int    = 0

    def increaseIndent() = indent += 1

    def decreaseIndent() = indent -= 1

    def print(s: String): Unit = output += s

    def printIndent() = print("  " * indent)

    def printNewline() = {
      print("\n")
      printIndent()
    }

    def printOpenTag(tag: String, attrs: Map[String, String]) = {
      printNewline()
      increaseIndent()
      if (attrs.isEmpty) print(s"<${tag}>")
      else {
        val renderAttrs = attrs.map {
          case (key, value) => key + "=\"" + value + "\""
        }.mkString(" ")

        print(s"<${tag} ${renderAttrs}>")
      }
    }

    def printCloseTag(tag: String) = {
      decreaseIndent()
      printNewline()
      print(s"</${tag}>")
    }

    def loop(element: Element): Unit = {
      printOpenTag(element.tag, element.attributes)

      if (element.children.nonEmpty) {
        printNewline()
        element.children.foreach(loop(_))
        printNewline()
      }

      printCloseTag(element.tag)
    }

    loop(element)

    output
  }

  val data =
    Element("body", Map(), List(Element("h1"), Element("p")))

  val expected =
    """
      |<body>
      |  
      |  <h1>
      |  </h1>
      |  <p>
      |  </p>
      |  
      |</body>""".stripMargin

  def spec =
    test("conversion") {
      assertTrue(render(data) == expected)
    } @@ ignore
}
