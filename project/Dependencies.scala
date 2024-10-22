import sbt.*
import sbt.librarymanagement.{CrossVersion, DependencyBuilders}

object V {

  // ZIO versions
  lazy val zioLoggingVersion = "2.3.1"
  lazy val zioVersion        = "2.1.9"

  lazy val zioPrelude = "1.0.0-RC15"
  lazy val zioConfig  = "4.0.2"
  lazy val zioMock    = "1.0.0-RC12"

  // Logging versions
  val logback         = "1.5.7"
  val logbackLogstash = "8.0"
  val janino          = "3.1.6"

  // Gatling
  val gatlingVersion = "3.7.4"

  // jts2geojson
  lazy val jts2geojsonVersion = "0.18.1"

  // Scala test
  val scalaTestVersion = "3.2.19"
  val mockitoVersion   = "3.2.15.0"

  // Test Containers
  val testContainersScalaTest    = "0.41.4"
  lazy val testContainersVersion = "1.20.1"

  // Parquet
  val parquet4sVersion = "2.19.0"
  val hadoopVersion    = "3.4.0"

  // Http
  val sttpVersion = "3.9.8"

  // AWS SDK
  lazy val awsCrtVersion = "0.31.1"
  lazy val awsSdkVersion = "2.28.6"

  // CLI
  lazy val declineVersion = "2.4.1"

  // Circe
  lazy val circeVersion = "0.14.10"

  // Cats
  lazy val catsVersion = "2.12.0"

  // Geotools
  lazy val geoToolsVersion = "31.3"

  // Difflicious
  lazy val diffliciousVersion = "0.4.3"

  lazy val duckDBVersion = "1.1.0"

}

object Dependencies {

  lazy val h3Dependencies: Seq[ModuleID] = Seq(
    "com.uber" % "h3" % "4.1.1"
  )

  lazy val awsDependencies: Seq[ModuleID] = Seq(
    "software.amazon.awssdk"     % "s3"                  % V.awsSdkVersion,
    "software.amazon.awssdk"     % "sts"                 % V.awsSdkVersion,
    "software.amazon.awssdk"     % "sso"                 % V.awsSdkVersion,
    "software.amazon.awssdk"     % "s3-transfer-manager" % V.awsSdkVersion,
    "software.amazon.awssdk.crt" % "aws-crt"             % V.awsCrtVersion
  )

  lazy val doobieDependencies: Seq[ModuleID] = Seq(
    "org.tpolecat" %% "doobie-core"     % "1.0.0-RC6",
    "org.tpolecat" %% "doobie-hikari"   % "1.0.0-RC6",
    "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC6",
    "org.questdb"   % "questdb"         % "8.1.1"
  )

  private lazy val geoToolsDependencies = Seq(
    "org.geotools" % "gt-main"        % V.geoToolsVersion,
    "org.geotools" % "gt-render"      % V.geoToolsVersion,
    "org.geotools" % "gt-shapefile"   % V.geoToolsVersion,
    "org.geotools" % "gt-swing"       % V.geoToolsVersion,
    "org.geotools" % "gt-tile-client" % V.geoToolsVersion,
    "org.geotools" % "gt-epsg-hsql"   % V.geoToolsVersion
  )

  private lazy val jtsDependencies = Seq(
    "org.locationtech.jts" % "jts-core" % "1.20.0"
  )

  lazy val redshiftDependencies: Seq[ModuleID] = Seq(
    "com.amazon.redshift" % "redshift-jdbc42" % "2.1.0.30"
  )

  private lazy val zioPreludeDependencies = Seq("dev.zio" %% "zio-prelude" % V.zioPrelude)

  lazy val zioDependencies: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio"                 % V.zioVersion,
    "dev.zio" %% "zio-logging"         % V.zioLoggingVersion,
    "dev.zio" %% "zio-logging-slf4j"   % V.zioLoggingVersion,
    "dev.zio" %% "zio-config"          % V.zioConfig,
    "dev.zio" %% "zio-config-magnolia" % V.zioConfig,
    "dev.zio" %% "zio-config-typesafe" % V.zioConfig,
    "dev.zio" %% "zio-config-yaml"     % V.zioConfig,
    "dev.zio" %% "zio-test-sbt"        % V.zioVersion % Test,
    "dev.zio" %% "zio-interop-cats"    % "23.1.0.2",
    "dev.zio" %% "zio-json"            % "0.7.3"
    // silencer library added to fix an issue with zio-config3 (impacts scala >= 3.3)
    // https://github.com/zio/zio-config/pull/1171
    // this will be probably fixed in zio-config4
    // "com.github.ghik" % "silencer-lib_2.13.11" % "1.17.13"
  ).map(
    _.excludeAll(
      ExclusionRule(organization = "io.circe")
    )
  )

  lazy val sttpClientDependencies: Seq[ModuleID] = Seq(
    "com.softwaremill.sttp.client3" %% "core"                          % V.sttpVersion,
    "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % V.sttpVersion,
    "com.softwaremill.sttp.client3" %% "zio-json"                      % V.sttpVersion
  ).map(
    _.excludeAll(
      ExclusionRule(organization = "com.fasterxml.jackson")
    )
  )

  lazy val geojsonDependencies: Seq[ModuleID] = Seq(
    "org.wololo" % "jts2geojson" % V.jts2geojsonVersion
  )

  lazy val commandLineParserDependencies: Seq[ModuleID] = Seq(
    "com.monovore" %% "decline"        % V.declineVersion,
    "com.monovore" %% "decline-effect" % V.declineVersion
  )

  lazy val parquetDependencies: Seq[ModuleID] = Seq(
    "com.github.mjakubowski84" %% "parquet4s-fs2"  % V.parquet4sVersion,
    "com.github.mjakubowski84" %% "parquet4s-core" % V.parquet4sVersion,
    "org.apache.hadoop"         % "hadoop-client"  % V.hadoopVersion
  )

  private lazy val googleDependencies = Seq(
    "com.google.maps" % "google-maps-services" % "2.2.0"
  )

  lazy val circeDependencies: Seq[ModuleID] = Seq(
    "io.circe" %% "circe-core"    % V.circeVersion,
    "io.circe" %% "circe-generic" % V.circeVersion,
    "io.circe" %% "circe-parser"  % V.circeVersion
  )
    .map(
      _.excludeAll(
        ExclusionRule(organization = "org.typelevel")
      )
    )

  private lazy val jawnAstDependencies = Seq(("org.typelevel" %% "jawn-ast" % "1.6.0").cross(CrossVersion.for3Use2_13))

  private lazy val csvDependencies = Seq(
    "com.github.tototoshi" %% "scala-csv" % "2.0.0"
  )

  private lazy val catsDependencies = Seq(
    "org.typelevel" %% "cats-core" % V.catsVersion
  )

  private lazy val duckDbDependencies = Seq(
    "org.duckdb" % "duckdb_jdbc" % V.duckDBVersion
  )

  val runtime: Seq[ModuleID] = Seq(
    "ch.qos.logback"       % "logback-classic"          % V.logback,
    "net.logstash.logback" % "logstash-logback-encoder" % V.logbackLogstash,
    "org.codehaus.janino"  % "janino"                   % V.janino
  ).map(_ % Runtime)

  val test: Seq[ModuleID] = Seq(
    "dev.zio"             %% "zio-test"                       % V.zioVersion,
    "dev.zio"             %% "zio-test-sbt"                   % V.zioVersion,
    "dev.zio"             %% "zio-mock"                       % V.zioMock,
    "org.scalatest"       %% "scalatest"                      % V.scalaTestVersion,
    "org.scalatestplus"   %% "mockito-4-6"                    % V.mockitoVersion,
    "org.testcontainers"   % "testcontainers"                 % V.testContainersVersion,
    "org.testcontainers"   % "minio"                          % V.testContainersVersion,
    "com.dimafeng"        %% "testcontainers-scala-scalatest" % V.testContainersScalaTest,
    "com.github.jatcwang" %% "difflicious-scalatest"          % V.diffliciousVersion,
    "org.duckdb"           % "duckdb_jdbc"                    % V.duckDBVersion
  ).map(_ % Test)


}
