package com.vikasg.usernamecount.master;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PartitionMapper extends
    Mapper<Object, Text, Text, IntWritable> {

  @Override
  protected void map(Object key, Text value,
      org.apache.hadoop.mapreduce.Mapper.Context context) throws IOException,
      InterruptedException {

    StringTokenizer wordList = new StringTokenizer(value.toString(), "|");
    String gender = null;
    String nameAge = null;
    String salary = null;
    
    while (wordList.hasMoreTokens()) {

      nameAge =
 wordList.nextToken() + ',' + wordList.nextToken();

      gender = wordList.nextToken();

      salary = wordList.nextToken();
      
      context.write(new Text(gender), new Text(nameAge + ',' + salary));

    }

  }

}
