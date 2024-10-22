import com.stuart.sbt.StuartDockerPlugin.autoImport.*
import com.typesafe.sbt.SbtNativePackager.*
import com.typesafe.sbt.packager.Keys.*
import com.typesafe.sbt.packager.docker.DockerAlias
import com.typesafe.sbt.packager.docker.DockerPlugin.autoImport.{Docker, dockerBaseImage, dockerRepository}
import sbt.Keys.*
import sbt.io.Path.directory
import sbt.*

object DockerSettings {

  private val baseImg             = "docker-registry-proxy.internal.stuart.com/openjdk:21-jdk-slim"
  private val githubRepo          = "https://github.com/StuartApp/courier-routes-pipeline"
  private val userName            = "platform"
  private val imageAliasTagEnvVar = "IMAGE_ALIAS_TAG"

  private val sharedSettings = Seq(
    Universal / mappings ++= {
      val resources = (Compile / resourceDirectory).value
      directory(resources / "assets") ++ directory(resources / "bin")
    },
    Docker / defaultLinuxInstallLocation := "/usr/local",
    githubRepositoryUrl                  := githubRepo,
    defaultDockerUsername                := userName,
    dockerBaseImage                      := baseImg,
    Docker / dockerRepository            := None,
    dockerAliases                       ++= Seq(dockerAlias.value.withTag(sys.env.get(imageAliasTagEnvVar)))
  )

  val groupStepSettings: Seq[
    Def.Setting[? >: Task[Seq[(File, String)]] & String & Option[String] & Seq[DockerAlias] <: Object]
  ] = sharedSettings ++ Seq(
    defaultDockerPackageName := "courier-routes-pipeline-group-step"
  )

  val citySpeedsStepSettings: Seq[
    Def.Setting[? >: Task[Seq[(File, String)]] & String & Option[String] & Seq[DockerAlias] <: Object]
  ] = sharedSettings ++ Seq(
    defaultDockerPackageName := "courier-routes-pipeline-city-speeds-step"
  )

  val segmentStepSettings: Seq[
    Def.Setting[? >: Task[Seq[(File, String)]] & String & Option[String] & Seq[DockerAlias] <: Object]
  ] = sharedSettings ++ Seq(
    defaultDockerPackageName := "courier-routes-pipeline-segment-step"
  )

  val speedTrendSettings: Seq[
    Def.Setting[? >: Task[Seq[(File, String)]] & String & Option[String] & Seq[DockerAlias] <: Object]
  ] = sharedSettings ++ Seq(
    defaultDockerPackageName := "courier-route-speed-trend"
  )

}
