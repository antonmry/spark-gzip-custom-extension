package com.galiglobal.databricks

import org.apache.hadoop.io.compress.GzipCodec

class GZCodec extends GzipCodec {
  override def getDefaultExtension(): String = ".GZ"
}

