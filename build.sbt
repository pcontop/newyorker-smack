
name := "NewYorker"

version := "0.1"

scalaVersion := "2.11.9"

//Spark
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.3"

//Compress
libraryDependencies += "org.apache.commons" % "commons-compress" % "1.11"

//json
libraryDependencies += "io.argonaut" %% "argonaut" % "6.2.2"

//Assembly
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

//Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

//Cassandra
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.0"

//assembly strategy - deduplication
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

