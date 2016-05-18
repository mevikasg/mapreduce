package com.vikasg.usernamecount.master;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class WordMapper extends Mapper<Object, Text, Text, IntWritable> {
  private Text username = new Text();
  // private final static IntWritable value = new IntWritable(1);
 Log log = LogFactory.getLog(WordMapper.class);
 
 @Override
 public void map(Object key, Text value,
   Context contex) throws IOException, InterruptedException {
  // Break line into words for processing
    // StringTokenizer wordList = new StringTokenizer(value.toString());
    StringTokenizer wordList = new StringTokenizer(value.toString());
    IntWritable count = new IntWritable(1);

    while (wordList.hasMoreTokens()) {

      username.set(wordList.nextToken().contains("Vikas Gupta") ? "Vikas"
          : "No");
      
      count.set(Integer.parseInt(wordList.nextToken()));

      contex.write(username, count);
      // System.out.println(""+word.toString()+ " = "+one.toString());
      log.info("" + username.toString() + " = " + count.toString());
  }
 }
}