package com.vikasg.usernamecount.master;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PartitionReducer extends
    Reducer<Text, Iterator<String>, Text, Text> {

  protected void reduce(Text key, Iterable<Text> values,
      Context context) throws IOException, InterruptedException {

    long maxSalary = Long.MIN_VALUE;
    String name = null;
    String age = null;
    long salary = 0;
    String gender = key.toString();
    
    for (Text s : values) {
      StringTokenizer value = new StringTokenizer(s.toString(), ",");
      if (maxSalary < salary) {
        while (value.hasMoreTokens()) {
          name = value.nextToken();
          age = value.nextToken();
          maxSalary = Long.parseLong(value.nextToken());
        }
      }

      context.write(new Text(name), new Text(age + ' ' + gender + ' '
          + maxSalary));

    }
    
    
    
    
  }

}
