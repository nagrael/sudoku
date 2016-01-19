name := "sudoku"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.60-R9"

libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))
