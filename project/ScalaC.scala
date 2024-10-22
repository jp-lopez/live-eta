object ScalaC {

  lazy val commonScalacOptions: Seq[String] = Seq(
//    "-Xlint:infer-any",
    "-Xmax-inlines:100"
  )

}
