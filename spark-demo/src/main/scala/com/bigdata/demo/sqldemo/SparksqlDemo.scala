package com.bigdata.demo.sqldemo

import org.apache.spark.{SparkConf, SparkContext}

object SparksqlDemo {


   def main(args:Array[String]) = {

      val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("KuduClientTest"))
      val sqlContext = new org.apache.spark.sql.SQLContext(sc)

   }
}
