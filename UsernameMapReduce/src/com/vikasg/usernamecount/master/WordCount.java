package com.vikasg.usernamecount.master;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class WordCount {
 public static void main(String[] args) throws Exception {
       
    if (args.length != 2) {
          System.out.println("usage: [input] [output]");
          System.exit(-1);
        }

   
	 	System.out.println("Input: "+args[0]);
	 	System.out.println("Output: "+args[1]);
	 	
    Job job = new Job(new Configuration());
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    job.setMapperClass(WordMapper.class);
    // job.setReducerClass(null);// SumReducer.class);

    job.setInputFormatClass(TextInputFormat.class);

    // job.setOutputKeyClass(Text.class);
    // job.setOutputValueClass(Integer.class);

    job.setGroupingComparatorClass(IntWritable.Comparator.class);
    // job.setOutputValueClass(IntWritable.Comparator.class);

    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    // job.setGroupingComparatorClass(IntWritable.Comparator.class);

    job.setJarByClass(WordCount.class);
        

    job.submit();
 
   
 }
}