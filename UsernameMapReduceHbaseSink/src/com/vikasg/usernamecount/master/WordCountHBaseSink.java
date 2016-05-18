package com.vikasg.usernamecount.master;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

public class WordCountHBaseSink {
 public static void main(String[] args) throws Exception {
       
    /*
     * if (args.length != 2) { System.out.println("usage: [input] [output]");
     * System.exit(-1); }
     */
    // http://sujee.net/hbase-map-reduce-freq-counter/
   
	 	System.out.println("Input: "+args[0]);
    // System.out.println("Output: "+args[1]);
	 	
	 	Configuration conf = HBaseConfiguration.create();
	 	
    Job job = new Job(conf, "MapReduceHBaseSink Job");
    job.setJarByClass(WordCountHBaseSink.class);


    // System.out.println("HADOOP_HOME: " + System.getenv("HADOOP_HOME"));
    
    
    // job.setMapperClass(WordMapper.class);
    // job.setInputFormatClass(TextInputFormat.class);

    Scan scan = new Scan();
    scan.setCaching(100);
    scan.setCacheBlocks(false);

    TableMapReduceUtil.initTableMapperJob("user", scan, WordMapper.class,
        Text.class, IntWritable.class, job);

    TableMapReduceUtil.initTableReducerJob("user2", TableSumReducer.class, job);

    job.setNumReduceTasks(1);

    boolean jobStatus = job.waitForCompletion(true);
 
    if (!jobStatus) {
      throw new IOException("Error while running the job!!!");
    }
   
 }
}