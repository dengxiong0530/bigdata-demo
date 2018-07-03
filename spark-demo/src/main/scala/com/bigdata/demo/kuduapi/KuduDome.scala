package com.bigdata.demo.kuduapi


import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.kudu.spark.kudu._

object KuduDome {

  Logger.getLogger("org").setLevel(Level.ERROR)


  def main(args: Array[String]): Unit = {
    val tableName = "test01"
    val kuduMaster = "xxxxx:7051"

    val spark = SparkSession
      .builder()
      .appName("KuduDome").master("local[2]")
      //      .config("spark.executor.memory", "2147480000").config("spark.driver.memory","21474800001")
      .getOrCreate()

    val sqlContext = spark.sqlContext
    //
    //    val df = sqlContext.read.options(Map("kudu.master" -> kuduMaster, "kudu.table" -> tableName)).kudu
    //    df.select("id").filter(df("id") <= 100).show(50)

    val kuduContext = new KuduContext(kuduMaster, sqlContext.sparkContext)
    import sqlContext.implicits._
    val testDF = sqlContext.sparkContext.makeRDD(1 to 10000).map(i => (i, "Tom" + i)).toDF("id", "name")
    kuduContext.insertRows(testDF, "test01")
    kuduContext.insertIgnoreRows(testDF, "test01")
    kuduContext.updateRows(testDF, "test01")
    kuduContext.upsertRows(testDF, "test01")
  }

}
