package io.successfulsoftware.analytics.streaming

import java.util.HashMap

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.kafka.KafkaUtils;

object work {
  def task(kafkaURL: String, zooURL: String, duration: Int, appName: String, topic: String) 
    {
    val sparkConf = new SparkConf().setAppName(appName)
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("checkpoint")

    val topicMap = Map(topic->2)
    val lines = KafkaUtils.createStream(ssc, zooURL, "1", topicMap).map(_._2)              
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L))
      .reduceByKey(_ + _)
    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
    }
}