package com.vikasg.usernamecount.master;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class CustomPartitionerMR {

  /**
   * @param args
   */
  public static void main(String[] args) {

    try {

      Configuration conf = new Configuration();
      // conf.set("fs.default.name", "hdfs://localhost:9000");
      // conf.set("mapred.job.tracker", "localhost:9001");

      Job job = new Job(conf, "CustomPartitioner");

      job.setJarByClass(CustomPartitionerMR.class);

      // Input
      job.setInputFormatClass(TextInputFormat.class);
      TextInputFormat.addInputPath(job, new Path(args[0]));

      // Output
      job.setOutputFormatClass(TextOutputFormat.class);
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(Text.class);
      TextOutputFormat.setOutputPath(job, new Path(args[1]));


      job.setMapperClass(PartitionMapper.class);
      job.setPartitionerClass(CustomPartition.class);
      job.setReducerClass(PartitionReducer.class);
      job.setNumReduceTasks(3);


      System.exit(job.waitForCompletion(true) ? 0 : 1);
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
