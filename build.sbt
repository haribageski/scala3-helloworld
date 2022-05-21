val scala3Version = "3.0.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-simple",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-unchecked",
  "-deprecation",
  "-source:future",           // warning for extending a non-open class, new syntax for vararg splices
  "-Yexplicit-nulls",         // explicit Null type
  "-Ysafe-init",              // safe initialization, avoiding non-defined in run-time
  "-language:strictEquality", // disables comparisons over all types, enforcing CanEqual given
  "-new-syntax",              // require then and do in control expressions.
  "-no-indent"                // require classical {…} syntax, indentation is not significant.
)