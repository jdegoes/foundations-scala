/**
 * In functional Scala, programs do not interact with the external world.
 * Rather, they build blueprints, which describe how to process intput
 * and produce output in the external world. This separation between
 * model, which describes _what_ to do, and execution, which describes
 * _how_ to do it, results in increased expressive power, which is why
 * functional programming is revolutionizing the design of reactive
 * applications, which are difficult to build correctly without a functional
 * approach.
 */
package net.degoes

import zio._
import zio.test._
import zio.test.TestAspect._
import java.io.IOException
import zio.test.environment.TestConsole

object Effects extends DefaultRunnableSpec {
  def spec =
    suite("Effects") {
      suite("constructors") {

        /**
         * EXERCISE
         *
         * Using `ZIO.succeed`, construct an effect that succeeds with the
         * value `42`.
         */
        test("succeed") {
          def effect: ZIO[Any, Nothing, Int] = ???

          for {
            result <- effect
          } yield assertTrue(result == 42)
        } @@ ignore +
          /**
           * EXERCISE
           *
           * Using `ZIO.fromEither`, construct an effect from the provided either
           * value.
           */
          test("fromEither (success)") {
            val either: Either[String, Int] = Right(42)

            val _ = either

            def effect: ZIO[Any, String, Int] = ???

            for {
              result <- effect
            } yield assertTrue(result == 42)
          } @@ ignore +
          /**
           * EXERCISE
           *
           * Using `ZIO.fromOption`, construct an effect from the provided either
           * value.
           */
          test("fromOption (success)") {
            val option: Option[Int] = Some(42)

            val _ = option

            def effect: ZIO[Any, Option[Nothing], Int] = ???

            for {
              result <- effect
            } yield assertTrue(result == 42)
          } @@ ignore
      } +
        suite("operators") {

          /**
           * EXERCISE
           *
           * Using `ZIO#map`, map the success value of the `Console.readLine`
           * effect into an integer representing the length of the line of
           * text that is read from the console.
           */
          test("map") {
            val readInt: IO[IOException, Int] = ???

            for {
              _   <- TestConsole.feedLines("Sherlock")
              int <- readInt
            } yield assertTrue(int == 8)
          } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `ZIO#mapError`, turn the integer failure of the provided
             * effect into a string representation of the integer.
             */
            test("mapError") {
              val errorCode = 42

              val failure = IO.fail(errorCode)

              val mappedFailure: IO[String, Nothing] = failure.mapError(???)

              for {
                value <- mappedFailure.flip
              } yield assertTrue(value == "42")
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `ZIO#zip`, sequentially combine the provided two effects.
             */
            test("zip") {
              val first = Console.printLine("Sherlock")
              val last  = Console.printLine("Holmes")

              val _ = first
              val _ = last

              val zipped: ZIO[Has[Console], IOException, Unit] = ???

              for {
                _      <- zipped
                output <- TestConsole.output
              } yield assertTrue(output == Vector("Sherlock\n", "Holmes\n"))
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `ZIO.*>`, sequentially zip the provided two effects together,
             * but return the success value of the right hand side.
             */
            test("*>") {
              val first  = ZIO.succeed(42)
              val second = ZIO.succeed("Roger Rabbit")

              val _ = first
              val _ = second

              val zipLeft: UIO[String] = ???

              for {
                result <- zipLeft
              } yield assertTrue(result == "Roger Rabbit")
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `ZIO.<*`, sequentially zip the provided two effects together,
             * but return the success value of the left hand side.
             */
            test("<*") {
              val first  = ZIO.succeed(42)
              val second = ZIO.succeed("Roger Rabbit")

              val _ = first
              val _ = second

              val zipLeft: UIO[Int] = ???

              for {
                result <- zipLeft
              } yield assertTrue(result == 42)
            } @@ ignore +
            test("flatMap") {
              assertTrue(true)
            } @@ ignore +
            test("catchAll") {
              assertTrue(true)
            } @@ ignore +
            test("foldZIO") {
              assertTrue(true)
            } @@ ignore +
            test("recursion") {
              assertTrue(true)
            } @@ ignore
        } +
        suite("control flow") {
          test("forever") {
            assertTrue(true)
          } @@ ignore +
            test("eventually") {
              assertTrue(true)
            } @@ ignore +
            test("repeatN") {
              assertTrue(true)
            } @@ ignore +
            test("whenZIO") {
              assertTrue(true)
            } @@ ignore
        }
    }
}
