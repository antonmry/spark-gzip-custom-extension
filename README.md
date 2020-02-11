## Introduction 

Spark isn't able to read files when they don't have the proper extension. For example, it's able to read CSV 
compressed with GZIP if they end with `.gz` but it fails if they end with `.GZ`.

With this small library, you can fix it.

## Setup

Start the Databricks cluster with the following property:

```
spark.hadoop.io.compression.codecs com.galiglobal.databricks.GZCodec
```

See [Spark configuration](https://docs.microsoft.com/en-us/azure/databricks/clusters/configure#spark-config) for more info.

Then, import the library to your cluster. You can download it from [Releases](https://github.com/antonmry/spark-gzip-custom-extension/releases/)
or you can clone the project and compile it with `sbt package`.

See [Uploading libraries](https://docs.databricks.com/libraries.html#uploading-libraries) to know more about how to
upload libraries to Databricks.

Now you should able to read GZ files:

```scala
val df = spark.read.format("csv")
  .option("header", "false")
  .option("inferSchema", "true") 
  .option("sep", "\t")
  .load("/FileStore/tables/antonmry/example.GZ")
```

## Customize

If you want to use a different extension, just modify `src/main/scala/com/galiglobal/databricks/GZCodec.scala` replacing
`GZ` with your extension.

To do it with a Spark cluster, you can also use [the following approach](https://stackoverflow.com/questions/44372995/read-a-compressed-file-with-custom-extension-with-spark).

