package com.vikasg.usernamecount.master;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class WordMapper extends TableMapper<Text, IntWritable> {
  private Text text = new Text();
  private IntWritable ONE = new IntWritable(1);
  // private final static IntWritable value = new IntWritable(1);
 Log log = LogFactory.getLog(WordMapper.class);
 
  /*
   * public void map123(Object key, Text value, Context contex) throws
   * IOException, InterruptedException { // Break line into words for processing
   * // StringTokenizer wordList = new StringTokenizer(value.toString());
   * StringTokenizer wordList = new StringTokenizer(value.toString());
   * IntWritable count = new IntWritable(1);
   * 
   * while (wordList.hasMoreTokens()) {
   * 
   * username.set(wordList.nextToken());
   * 
   * count.set(Integer.parseInt(wordList.nextToken()));
   * 
   * contex.write(username, count); // System.out.println(""+word.toString()+
   * " = "+one.toString()); log.info("" + username.toString() + " = " +
   * count.toString()); } }
   */

  @Override
  public void map(ImmutableBytesWritable key, Result value,
      org.apache.hadoop.mapreduce.Mapper.Context context) throws IOException,
      InterruptedException {
   
      String res = new String(value.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
      
      text.set(res);
    
      context.write(text,ONE );
      // System.out.println(""+word.toString()+ " = "+one.toString());
      log.info("" + res.toString() + " = " + ONE.toString());
    }
  }

