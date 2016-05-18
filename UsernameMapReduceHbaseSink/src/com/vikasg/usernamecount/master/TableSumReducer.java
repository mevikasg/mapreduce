package com.vikasg.usernamecount.master;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;


public class TableSumReducer extends
    TableReducer<Text, IntWritable, ImmutableBytesWritable> {
 
 private IntWritable totalWordCount = new IntWritable();
 Log log = LogFactory.getLog(TableSumReducer.class);
 
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
            throws IOException, InterruptedException {


  int wordCount = 0;

  Iterator<IntWritable> it=values.iterator();

  while (it.hasNext()) {
   wordCount += it.next().get();
  }

    // totalWordCount.set(wordCount)



    System.out.println("wordCount:  " + wordCount);

    Put put = new Put(Bytes.toBytes(key.toString()));
    put.add(Bytes.toBytes("info"), Bytes.toBytes("name"),
        Bytes.toBytes(wordCount));

    context.write(null, put);

  //System.out.println(""+key+ " = "+totalWordCount);
  log.info(""+key+ " = "+totalWordCount);
  
    // Scan scan = new Scan();

    // scan.get

 }
}