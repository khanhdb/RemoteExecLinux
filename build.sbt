name := "RemoteExecLinux"
maintainer := "khanhdb"
version := "2.0"

lazy val `remoteexeclinux` = (project in file(".")).enablePlugins(PlayScala, DebianPlugin)

maintainer in Linux := "Khanhdb"

packageSummary in Linux := "Remote Exec Linux as Debian package"

packageDescription := "run it!"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )