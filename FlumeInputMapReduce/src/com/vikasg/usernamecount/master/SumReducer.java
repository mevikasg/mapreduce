package com.vikasg.usernamecount.master;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class SumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
 
 private IntWritable totalWordCount = new IntWritable();
 Log log = LogFactory.getLog(SumReducer.class);
 
 @Override
 public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
  int wordCount = 0;
  Iterator<IntWritable> it=values.iterator();
  while (it.hasNext()) {
   wordCount += it.next().get();
  }
  totalWordCount.set(wordCount);
  context.write(key, totalWordCount);
  //System.out.println(""+key+ " = "+totalWordCount);
  log.info(""+key+ " = "+totalWordCount);
  
 }
}