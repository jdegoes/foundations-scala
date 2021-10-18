/**
 * Tony Hoare famously called nulls the "billion dollar mistake".
 * Realistically, the cost they impose on production software is much higher.
 * Functional Scala does not use null values, choosing instead to reflect
 * optionality with data types that convey information at compile-time.
 * This decision results in no NullPointerException, which in turn results
 * in more reliable applications with better-defined error handling.
 *
 * In this module, you will learn how to replace nulls with options.
 */
package net.degoes

import zio.test._
import zio.test.TestAspect._

object Nulls extends DefaultRunnableSpec {
  def spec =
    suite("Nulls") {
      suite("basics") {

        /**
         * EXERCISE
         *
         * The `parentOf` function returns `null` for some paths. Modify the
         * function to return `Option[File]` rather than `File | Null`.
         */
        test("Option.apply") {
          import java.io.File

          def parentOf(file: String) = new File(file).getParent

          assertTrue(parentOf("") != null)
        }
      } +
        suite("porting") {

          /**
           * EXERCISE
           *
           * Rewrite the following code to use `Option` instead of nulls.
           */
          test("Option") {
            final case class Address(street: String)
            final case class Profile(address: Address)
            final case class User(id: String, profile: Profile)

            val user1 =
              User("Sherlock Holmes", null)
            val user2 =
              User("Sherlock Holmes", Profile(null))
            val user3 =
              User("Sherlock Holmes", Profile(Address(null)))

            def getStreet(user: User): String =
              if (user == null) null
              else if (user.profile == null) null
              else if (user.profile.address == null) null
              else if (user.profile.address.street == null) null
              else user.profile.address.street

            def assertFails(value: => Any) = assertTrue(value == null)

            assertFails(getStreet(user1)) &&
            assertFails(getStreet(user2)) &&
            assertFails(getStreet(user3))
          } @@ ignore
        }
    }
}
