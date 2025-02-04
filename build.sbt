name := "sqlite4s"
organization := "com.github.david-bouyssie"
version := "0.4.1"
scalaVersion := "2.13.6"
crossScalaVersions := Seq("2.13.6", "2.12.15") // , "2.11.12"

libraryDependencies += "com.outr" %%% "scribe" % "3.6.1"
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.7.10" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")
// Disable parallel execution of tests (as they need to be launched independently)
Test / parallelExecution := false

enablePlugins(ScalaNativePlugin)

// Set to false or remove if you want to show stubs as linking errors
nativeLinkStubs := true

nativeMode := "release-fast" //"release-fast"
nativeLTO := "thin" //"none" //"thin"
nativeLinkingOptions ++= Seq(
  "-L" ++ baseDirectory.value.getAbsolutePath() ++ "/nativelib"
)

// Your profile name of the sonatype account. The default is the same with the organization value
//sonatypeProfileName := "david-bouyssie"

scmInfo := Some(
  ScmInfo(
    url("https://github.com/david-bouyssie/sqlite4s"),
    "scm:git@github.com:david-bouyssie/sqlite4s.git"
  )
)
developers := List(
  Developer(
    id    = "david-bouyssie",
    name  = "David Bouyssié",
    email = "",
    url   = url("https://github.com/david-bouyssie")
  )
)
description := "A Scala Native wrapper of the SQLite C library."
licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/david-bouyssie/sqlite4s"))
pomIncludeRepository := { _ => false }
publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

// Workaround for issue https://github.com/sbt/sbt/issues/3570 (fixed in 1.3.x)
//updateOptions := updateOptions.value.withGigahorse(false)

// To publish to central:
// export GPG_TTY=$(tty) # gpg issue (https://github.com/keybase/keybase-issues/issues/2798)
// sbt ++publishSigned
//useGpg := true // (since 2.0.0): useGpg is true by default
//pgpPublicRing := file("~/.gnupg/pubring.kbx")
//pgpSecretRing := file("~/.gnupg/pubring.kbx")
Test / skip / publish := true

/*
val commonSettings = Seq(
  version := "0.2.0",
  organization := "com.github.david-bouyssie",
  scalaVersion := "2.11.12",
  //nativeLinkStubs := true,
  nativeMode := "release",
  libraryDependencies ++= Seq(
    "biz.enef" %%% "slogging" % "0.6.1",
    //"biz.enef" % "slogging_native0.3_2.11" % "0.6.1",
    "com.lihaoyi" %%% "utest" % "0.6.6" % "test"
  ),
  Test / nativeLinkStubs := true
)

val publishSettings = Seq(
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/david-bouyssie/sqlite4s"),
      "scm:git@github.com:david-bouyssie/sqlite4s.git"
    )
  ),
  developers := List(
    Developer(
      id    = "david-bouyssie",
      name  = "David Bouyssié",
      email = "",
      url   = url("https://github.com/david-bouyssie")
    )
  ),
  description := "A Scala Native wrapper of the SQLite C library.",
  licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  homepage := Some(url("https://github.com/david-bouyssie/sqlite4s")),
  pomIncludeRepository := { _ => false },
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
    else Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  publishMavenStyle := true,
  useGpg := true,
  Test / skip in publish := true
)

val sqlite4s = project
  .settings(commonSettings)
  .settings(publishSettings)
  .enablePlugins(ScalaNativePlugin)
*/