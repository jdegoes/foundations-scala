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

object Effects extends DefaultRunnableSpec {
  def spec =
    suite("Effects") {

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
    }
}
