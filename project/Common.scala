import sbt.Keys.*
import sbt.{Test, file}

object Common {
  val settings: Seq[sbt.Setting[?]] = Seq(
    organization         := "com.stuart.platform",
    scalaVersion         := "3.4.2",
    scalacOptions       ++= ScalaC.commonScalacOptions,
    Test / envVars       := Map("QDB_LOG_W_STDOUT_LEVEL" -> "ERROR"),
    Test / fork          := true,     // required because envVars is only meant to work when forking a new JVM from sbt,
    Test / baseDirectory := file(".") // forking the JVM changes the baseDirectory to the submodule directory,
    // so we restore it to the root one to be able to find Andorra maps
  )
}
