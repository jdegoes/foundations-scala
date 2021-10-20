/**
 * Many programming languages use exceptions as a way to short-circuit the
 * normal return process and signal failure to higher levels of an application.
 * Functional Scala provides another option: using typed return values, which
 * allow the compiler help you handle expected error cases, resulting in much
 * more robust and resilient code that better deals with the complexity of
 * life in the cloud.
 *
 * In this module, you will learn how to eliminate exceptions from your
 * application and program using typed return values.
 */
package net.degoes

import zio.test._
import zio.test.TestAspect._

object Exceptions extends DefaultRunnableSpec {
  def spec =
    suite("Exceptions") {
      suite("constructors") {

        /**
         * EXERCISE
         *
         * Modify `parseInt` to return an `Option`.
         */
        test("Option") {
          def parseInt(s: String) = s.toInt

          def test = (parseInt(""): Any) match {
            case None => "None"
            case _    => "Some"
          }

          assertTrue(test == "None")
        } @@ ignore +
          /**
           * EXERCISE
           *
           * Modify `parseInt` to return a `Try`.
           */
          test("Try") {
            import scala.util._

            def parseInt(s: String) = s.toInt

            def test = (parseInt(""): Any) match {
              case Failure(_) => "Failure"
              case _          => "Success"
            }

            assertTrue(test == "Failure")
          } @@ ignore +
          /**
           * EXERCISE
           *
           * Modify `parseInt` to return an `Either`, where `Left` indicates
           * failure to parse an integer.
           */
          test("Either") {
            def parseInt(s: String) = s.toInt

            def test = (parseInt(""): Any) match {
              case Left(_) => "Left"
              case _       => "Right"
            }

            assertTrue(test == "Left")
          } @@ ignore
      } +
        suite("map") {

          /**
           * EXERCISE
           *
           * Using `Option##map`, use the `parseInt` helper function to implement
           * a correct `Id` constructor.
           */
          test("Option") {
            def parseInt(i: String): Option[Int] =
              try Some(i.toInt)
              catch { case _: Throwable => None }

            final case class Id private (value: Int)

            object Id {
              def fromString(value: String): Option[Id] = {
                parseInt(value)

                ???
              }
            }

            assertTrue(Id.fromString("123").isDefined)
          } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `Try##map`, use the `parseInt` helper function to implement
             * a correct `Natural.fromString` constructor, which will succeed
             * only if the string is a number, and if that number is non-negative.
             */
            test("Try") {
              import scala.util._

              def parseInt(i: String): Try[Int] = Try(i.toInt)

              final case class Id private (value: Int)

              object Id {
                def fromString(value: String): Try[Id] = {
                  parseInt(value)

                  ???
                }
              }

              assertTrue(Id.fromString("123").isSuccess)
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `Either##flatMap`, use the `parseInt` helper function to implement
             * a correct `Natural.fromString` constructor, which will succeed
             * only if the string is a number, and if that number is non-negative.
             */
            test("Either") {
              def parseInt(i: String): Either[String, Int] =
                try Right(i.toInt)
                catch {
                  case e: NumberFormatException => Left(e.getMessage())
                }

              final case class Id private (value: Int)

              object Id {
                def fromString(value: String): Either[String, Id] = {
                  parseInt(value)

                  ???
                }
              }

              assertTrue(Id.fromString("123").isRight)
            } @@ ignore
        } +
        suite("fallback") {

          /**
           * EXERCISE
           *
           * Implement `fallback` in such a way that it prefers the left hand
           * side, if it contains a value, otherwise, it will use the right
           * hand side.
           */
          test("Option") {
            def fallback[A](left: Option[A], right: Option[A]): Option[A] = ???

            assertTrue(fallback(None, Some(42)) == Some(42))
          } @@ ignore +
            /**
             * EXERCISE
             *
             * Implement `fallback` in such a way that it prefers the left hand
             * side, if it contains a value, otherwise, it will use the right
             * hand side.
             */
            test("Try") {
              import scala.util._

              def fallback[A](left: Try[A], right: Try[A]): Try[A] = ???

              assertTrue(fallback(Failure(new Throwable), Success(42)) == Success(42))
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Implement `fallback` in such a way that it prefers the left hand
             * side, if it contains a value, otherwise, it will use the right
             * hand side.
             */
            test("Either") {
              def fallback[E, A](left: Either[E, A], right: Either[E, A]): Either[E, A] = ???

              assertTrue(fallback(Left("Uh oh!"), Right(42)) == Right(42))
            } @@ ignore
        } +
        suite("flatMap") {

          /**
           * EXERCISE
           *
           * Using `Option##flatMap`, use the `parseInt` helper function to implement
           * a correct `Natural.fromString` constructor, which will succeed
           * only if the string is a number, and if that number is non-negative.
           */
          test("Option") {
            def parseInt(i: String): Option[Int] =
              try Some(i.toInt)
              catch { case _: Throwable => None }

            final case class Natural(value: Int)

            object Natural {
              def fromString(value: String): Option[Natural] = {
                parseInt(value)

                ???
              }
            }

            assertTrue(Natural.fromString("123").isDefined)
          } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `Try##flatMap`, use the `parseInt` helper function to implement
             * a correct `Natural.fromString` constructor, which will succeed
             * only if the string is a number, and if that number is non-negative.
             */
            test("Try") {
              import scala.util._

              def parseInt(i: String): Try[Int] = Try(i.toInt)

              final case class Natural(value: Int)

              object Natural {
                def fromString(value: String): Try[Natural] = {
                  parseInt(value)

                  ???
                }
              }

              assertTrue(Natural.fromString("123").isSuccess)
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Using `Either##flatMap`, use the `parseInt` helper function to implement
             * a correct `Natural.fromString` constructor, which will succeed
             * only if the string is a number, and if that number is non-negative.
             */
            test("Either") {
              def parseInt(i: String): Either[String, Int] =
                try Right(i.toInt)
                catch {
                  case e: NumberFormatException => Left(e.getMessage())
                }

              final case class Natural(value: Int)

              object Natural {
                def fromString(value: String): Either[String, Natural] = {
                  parseInt(value)

                  ???
                }
              }

              assertTrue(Natural.fromString("123").isRight)
            } @@ ignore
        } +
        suite("both") {

          /**
           * EXERCISE
           *
           * Implement `both` in a way that, when values are present on both
           * sides, will produce a tuple of those values.
           */
          test("Option") {
            def both[A, B](left: Option[A], right: Option[B]): Option[(A, B)] = ???

            assertTrue(both(Some(4), Some(2)) == Some((4, 2)))
          } @@ ignore +
            /**
             * EXERCISE
             *
             * Implement `both` in a way that, when values are present on both
             * sides, will produce a tuple of those values.
             */
            test("Try") {
              import scala.util._

              def both[A, B](left: Try[A], right: Try[B]): Try[(A, B)] = ???

              assertTrue(both(Try(4), Try(2)) == Try((4, 2)))
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Implement `both` in a way that, when values are present on both
             * sides, will produce a tuple of those values.
             */
            test("Either") {
              def both[E, A, B](left: Either[E, A], right: Either[E, B]): Either[E, (A, B)] = ???

              assertTrue(both(Right(4), Right(2)) == Right((4, 2)))
            } @@ ignore
        } +
        suite("porting") {

          /**
           * EXERCISE
           *
           * Rewrite the following code to use `Option` instead of exceptions.
           */
          test("Option") {
            object Config {
              def getHost(): String = {
                val result = System.getProperty("CONFIG_HOST")

                if (result == null) throw new RuntimeException("Host is missing")

                result
              }

              def getPort(): Int = {
                val result = System.getProperty("CONFIG_HOST")

                if (result == null) throw new RuntimeException("Port is missing")

                result.toInt
              }
            }

            final case class ConnectionInfo(host: String, port: Int)

            def loadConnectionInfo(): ConnectionInfo =
              ConnectionInfo(Config.getHost(), Config.getPort())

            def assertFails(any: => Any) =
              try {
                any; assertTrue(false)
              } catch { case _: Throwable => assertTrue(true) }

            assertFails(loadConnectionInfo())
          } @@ ignore +
            /**
             * EXERCISE
             *
             * Rewrite the following code to use `Try` instead of exceptions.
             */
            test("Try") {
              object Config {
                def getHost(): String = {
                  val result = System.getProperty("CONFIG_HOST")

                  if (result == null) throw new RuntimeException("Host is missing")

                  result
                }

                def getPort(): Int = {
                  val result = System.getProperty("CONFIG_HOST")

                  if (result == null) throw new RuntimeException("Port is missing")

                  result.toInt
                }
              }

              final case class ConnectionInfo(host: String, port: Int)

              def loadConnectionInfo(): ConnectionInfo =
                ConnectionInfo(Config.getHost(), Config.getPort())

              def assertFails(any: => Any) =
                try {
                  any; assertTrue(false)
                } catch { case _: Throwable => assertTrue(true) }

              assertFails(loadConnectionInfo())
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Rewrite the following code to use `Either` instead of exceptions.
             */
            test("Either") {
              object Config {
                def getHost(): String = {
                  val result = System.getProperty("CONFIG_HOST")

                  if (result == null) throw new RuntimeException("Host is missing")

                  result
                }

                def getPort(): Int = {
                  val result = System.getProperty("CONFIG_HOST")

                  if (result == null) throw new RuntimeException("Port is missing")

                  result.toInt
                }
              }

              final case class ConnectionInfo(host: String, port: Int)

              def loadConnectionInfo(): ConnectionInfo =
                ConnectionInfo(Config.getHost(), Config.getPort())

              def assertFails(any: => Any) =
                try {
                  any; assertTrue(false)
                } catch { case _: Throwable => assertTrue(true) }

              assertFails(loadConnectionInfo())
            } @@ ignore
        } +
        suite("mixed") {

          /**
           * EXERCISE
           *
           * Find a way to combine an Option and a Try in a way that loses no
           * information.
           */
          test("Option/Try") {
            import scala.util._

            type User = String
            type Docs = List[String]

            def getUser: Option[User] = Some("sherlock@holmes.com")
            def getDocs: Try[Docs]    = Try(List("Doc 1", "Doc 2"))

            def getUserAndDocs = {
              getUser
              getDocs
              ???
            }

            assertTrue(getUserAndDocs == ???)
          } @@ ignore +
            /**
             * EXERCISE
             *
             * Find a way to combine an Either and an Option in a way that loses
             * no information.
             */
            test("Either/Option") {
              import scala.util._

              type User = String
              type Docs = List[String]

              def getUser: Either[String, User] = Right("sherlock@holmes.com")
              def getDocs: Option[Docs]         = Some(List("Doc 1", "Doc 2"))

              def getUserAndDocs = {
                getUser
                getDocs
                ???
              }

              assertTrue(getUserAndDocs == ???)
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Find a way to combine an Either and a Try in a way that loses
             * no information.
             */
            test("Either/Try") {
              import scala.util._

              type User = String
              type Docs = List[String]

              def getUser: Either[String, User] = Right("sherlock@holmes.com")
              def getDocs: Try[Docs]            = Try(List("Doc 1", "Doc 2"))

              def getUserAndDocs = {
                getUser
                getDocs
                ???
              }

              assertTrue(getUserAndDocs == ???)
            } @@ ignore +
            /**
             * EXERCISE
             *
             * Find a way to combine an Either, a Try, and an Option in a way
             * that loses no information.
             */
            test("Either/Try/Option") {
              import scala.util._

              type User  = String
              type Docs  = List[String]
              type Prefs = Map[String, Boolean]

              def getUser: Either[String, User] = Right("sherlock@holmes.com")
              def getDocs: Try[Docs]            = Try(List("Doc 1", "Doc 2"))
              def getPrefs: Option[Prefs]       = Some(Map("autosave" -> true))

              def getUserAndDocsAndPrefs = {
                getUser
                getDocs
                getPrefs
                ???
              }

              assertTrue(getUserAndDocsAndPrefs == ???)
            } @@ ignore
        }
    }
}
