# Functional Scala Fundamentals

Most Scala developers come from an object-oriented background, so although they understand classes, methods, and inheritance, the functional side of Scala remains a mystery. Yet thoroughly understanding functional Scala is the key to unlocking Scala’s potential for writing robust and powerful code that is testable and maintainable.

In this course, developers will learn the foundations of functional Scala, including language features that are unique to functional Scala, core concepts in functional programming, and proven techniques for developing robust and powerful applications in functional Scala. Developers will leave the course with new knowledge and hands-on skills for writing quality functional Scala, with more productivity and confidence than ever before.

### Who Should Attend

Scala developers who use Scala as a better object-oriented programming language, but who have not yet explored the functional potential of the programming language.

### Prerequisites

Basic knowledge of the Scala programming language, including classes, traits, and methods.

### Topics

 - Immutable data
 - Pattern matching and destructuring assignment
 - Higher-order functions, including those in Scala collections
 - Using Option instead of nulls for greater type-safety
 - Using Either/Try instead of exceptions for greater type-precision
 - Basic functional data modeling with algebraic data types
 - Using recursion for data processing and transformation
 - Pushing side-effects out of business logic for greater testability
 - For comprehensions & flatMap for Either/Try/Future
 - Favoring composition over inheritance

# Usage

## From the UI

1. Download the repository as a [zip archive](https://github.com/jdegoes/foundations-scala/archive/master.zip).
2. Unzip the archive, usually by double-clicking on the file.
3. Configure the source code files in the IDE or text editor of your choice.

## From the Command Line

1. Open up a terminal window.

2. Clone the repository.

    ```bash
    git clone https://github.com/jdegoes/foundations-scala
    ```
5. Launch project provided `sbt`.

    ```bash
    cd foundations-scala; ./sbt
    ```
6. Enter continuous compilation mode.

    ```bash
    sbt:foundations> ~ test:compile
    ```

Hint: You might get the following error when starting sbt:

> [error] 	typesafe-ivy-releases: unable to get resource for com.geirsson#sbt-scalafmt;1.6.0-RC4: res=https://repo.typesafe.com/typesafe/ivy-releases/com.geirsson/sbt-scalafmt/1.6.0-RC4/jars/sbt-scalafmt.jar: javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested targe

It's because you have an outdated Java version, missing some newer certificates. Install a newer Java version, e.g. using [Jabba](https://github.com/shyiko/jabba), a Java version manager. See [Stackoverflow](https://stackoverflow.com/a/58669704/1885392) for more details about the error.

# Legal

Copyright&copy; 2021 John A. De Goes. All rights reserved.
