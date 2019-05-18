
name := "NewYorker"

version := "1.0"

scalaVersion := "2.11.8"

val sparkVersion = "2.3.3"
val connectorVersion = "2.0.0"

//Spark and Cassandra
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "provided",
  "com.datastax.spark" %% "spark-cassandra-connector" % connectorVersion

).map(_.exclude("org.slf4j", "log4j-over-slf4j"))

//Compress
libraryDependencies += "org.apache.commons" % "commons-compress" % "1.11"

//json
libraryDependencies += "io.argonaut" %% "argonaut" % "6.2.2"

//Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

//Assembly
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

//assembly strategy - deduplication
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

