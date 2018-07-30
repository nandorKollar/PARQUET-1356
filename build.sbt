name := "test"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "org.apache.parquet" % "parquet" % "1.10.0"
libraryDependencies += "org.apache.parquet" % "parquet-avro" % "1.9.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.7.3"
libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "2.7.3"
