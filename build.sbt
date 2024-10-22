


lazy val liveEtaModels = project
  .in(file("live-eta-models"))
  .settings(Common.settings)
  .settings(
    name                 := "live-eta-models",
  )

lazy val liveEtaCommon = project
  .in(file("live-eta-common"))
  .settings(Common.settings)
  .settings(
    name                 := "live-eta-common",
  )

lazy val liveEtaService = project
  .in(file("live-eta-service"))
  .settings(Common.settings)
  .settings(
    name                 := "live-eta-service",
  )

lazy val liveEtaApi = project
  .in(file("live-eta-api"))
  .settings(Common.settings)
  .settings(
    name                 := "live-eta-api",
  )

lazy val liveEtaIntegrationTest = project
  .in(file("live-eta-integration-test"))
  .settings(Common.settings)
  .settings(
    name                 := "live-eta-integration-test",
  )

lazy val liveEtaE2ETest = project
  .in(file("live-eta-e2e-test"))
  .settings(Common.settings)
  .settings(
    name                 := "live-eta-e2e-test",
  )


lazy val root = (project in file("."))
  .settings(
    name := "live-eta"
  )
  .settings(Common.settings)
  .aggregate(
    liveEtaModels,
    liveEtaCommon,
    liveEtaService,
    liveEtaApi
  )


addCommandAlias(
  "fmtAll",
  // this is not possible to use an alias in an other alias definition so we have to rewrite explicitly what the CodeStylePlugin fmt alias is doing
  "scalafmtSbt; all scalafixAll; all scalafmtAll"
)
addCommandAlias(
  "check",
  // this is not possible to use an alias in an other alias definition so we have to rewrite explicitly what the CodeStylePlugin lint alias is doing
  "all scalafmtSbtCheck scalafmtCheckAll; scalafixAll --check"
)
addCommandAlias(
  "compileAll",
  "all clean cleanFiles;all compile Test/compile"
)
addCommandAlias(
  "publishDockerLocal",
  "pipelineGroupStep/Docker/publishLocal;pipelineSegmentStep/Docker/publishLocal;pipelineCitySpeedsStep/Docker/publishLocal;courierRouteSpeedTrend/Docker/publishLocal"
)
addCommandAlias("coverageAll", "clean;coverage;test;coverageAggregate")
