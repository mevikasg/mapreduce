package com.vikasg.usernamecount.master;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartition extends Partitioner<Text, Text> {

  private String[] values;

  @Override
  public int getPartition(Text key, Text value, int numReduceTasks) {

    values = value.toString().split(",");

    int age = Integer.parseInt(values[1]);

    if (numReduceTasks == 0) {

      return 0;
    }

    // numReduceTasks = 3;

    if (age <= 20) {

      return 0;

    } else if (age > 20 && age <= 50) {

      return 1 % numReduceTasks;

    } else {

      return 2 % numReduceTasks;

    }
  }

}
