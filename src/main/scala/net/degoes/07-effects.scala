/**
 * In functional Scala, programs do not interact with the external world.
 * Rather, they construct "blueprints"", which describe how to process input
 * and produce output in the external world. This separation between model,
 * which describes _what_ to do, and execution, which describes _how_ to do it,
 * results in increased expressive power, which is why functional programming
 * is revolutionizing the design of reactive applications, which are difficult
 * to build correctly without a functional approach.
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
            /**
             * EXERCISE
             *
             * Using `ZIO#flatMap`, `Console.printLine`, and
             * `Console.readLine`, write a program that prints out,
             * "What is your name?", then reads the name of the user, and
             * finally, prints out, "Hello, <name>!", where <name> is the
             * name of the user.
             */
            test("flatMap") {

              def program: ZIO[Has[Console], IOException, Unit] = ???

              val expected =
                Vector("What is your name?\n", "Sherlock\n", "Hello, Sherlock!\n")

              for {
                _      <- TestConsole.feedLines("Sherlock")
                _      <- program
                output <- TestConsole.output
              } yield assertTrue(output == expected)
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `ZIO.catchAll` and `ZIO.succeed`, recover from the failed
             * effect by succeeding with the string "Recovered!"
             */
            test("catchAll") {
              def effect: ZIO[Any, Nothing, String] =
                ZIO.fail("Uh oh!").FIXME

              for {
                value <- effect
              } yield assertTrue(value == "Recovered!")
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `ZIO#foldZIO`, handle both error and success cases for
             * the provided effect, producing the constant string "Did it!".
             */
            test("foldZIO") {
              def effect: ZIO[Any, Nothing, String] =
                ZIO.fail("Failure").FIXME

              for {
                value <- effect
              } yield assertTrue(value == "Did it!")
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using recursion, implement the `iterate` function so that it
             * iterates on the state value for as long as the predicate
             * returns true (but no longer).
             */
            test("recursion") {
              def iterate[R, E, S](start: S)(pred: S => Boolean)(f: S => ZIO[R, E, S]): ZIO[R, E, S] = ???

              val iterationResult =
                iterate(List.empty[String])(_.length < 3) { list =>
                  ZIO.succeed("a" :: list)
                }

              for {
                list <- iterationResult
              } yield assertTrue(list == List("a", "a", "a"))
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Use ZIO to allow combining lots of different effect types.
             */
            test("mixed") {
              import scala.util._

              final case class UnknownUserError(message: String) extends Exception(message)
              case class NoPreferencesError()                    extends Exception("Preferences could not be loaded")

              type User  = String
              type Docs  = List[String]
              type Prefs = Map[String, Boolean]

              def getUser(): Either[String, User] = Right("sherlock@holmes.com")
              def getDocs(): Try[Docs]            = Try(List("Doc 1", "Doc 2"))
              def getPrefs(): Option[Prefs]       = Some(Map("autosave" -> true))

              def getUserZIO: IO[UnknownUserError, User] = {
                getUser()
                ???
              }

              def getDocsZIO: IO[Throwable, Docs] = {
                getDocs()
                ???
              }

              def getPrefsZIO: IO[NoPreferencesError, Prefs] = {
                getPrefs()
                ???
              }

              for {
                user  <- getUserZIO
                docs  <- getDocsZIO
                prefs <- getPrefsZIO
              } yield assertTrue(
                user == "sherlock@holmes.com" && docs == List("Doc 1", "Doc 2") && prefs == Map("autosave" -> true)
              )

            } @@ ignore
        } +
        suite("control flow") {

          /**
           * EXERCISE
           *
           * Using `ZIO#forever`, make the worker run forever so that it
           * will continuously accumulate results until stopped.
           */
          test("forever") {

            def makeWorker(ref: Ref[List[String]]) =
              ref.update("All work and no play makes Jack a dull boy" :: _)

            for {
              accum  <- Ref.make[List[String]](Nil)
              worker = makeWorker(accum)
              fiber  <- worker.fork
              _      <- accum.get.repeatUntil(_.length > 10) *> fiber.interrupt
              result <- accum.get
            } yield assertTrue(result.length > 10)
          } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `ZIO#eventually`, make the worker repeat until it
             * succeeds.
             */
            test("eventually") {
              def makeWorker(ref: Ref[Int]) =
                for {
                  count <- ref.updateAndGet(_ + 1)
                  _     <- if (count < 10) ZIO.fail("Uh oh!") else ZIO.succeed(())
                } yield "Success!"

              for {
                ref    <- Ref.make(0)
                worker = makeWorker(ref)
                result <- worker
              } yield assertTrue(result == "Success!")
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `ZIO#repeatN`, repeat the provided effect for 5 times.
             */
            test("repeatN") {

              for {
                ref    <- Ref.make(0)
                effect = ref.update(_ + 1)
                _      <- effect
                result <- ref.get
              } yield assertTrue(result == 6)
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `ZIO.whenZIO`, set the provided ref to `true` whenever the
             * `continue` effect succeeds with `true`.
             */
            test("whenZIO") {
              def isYes(line: String): Boolean =
                line.toLowerCase match {
                  case "y" | "yes" => true
                  case _           => false
                }

              val continue =
                for {
                  _    <- Console.printLine("Do you want to continue (y/n)?")
                  line <- Console.readLine
                } yield isYes(line)

              for {
                ref    <- Ref.make(false)
                _      <- TestConsole.feedLines("y")
                _      <- continue
                result <- ref.get
              } yield assertTrue(result)
            } @@ ignore
        }
    }
}
