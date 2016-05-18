package com.vikasg.usernamecount.master;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.hive.ql.io.orc.OrcSerde;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;

public class PartitionMapper extends
    Mapper<LongWritable, Text, NullWritable, Writable> {

  private final OrcSerde serde = new OrcSerde();
  private final String typeString =
      "struct<name:string,age:int,gender:string,salary:bigint>";
  private final TypeInfo typeInfo = TypeInfoUtils
      .getTypeInfoFromTypeString(typeString);
  private final ObjectInspector oip = TypeInfoUtils
      .getStandardJavaObjectInspectorFromTypeInfo(typeInfo);

  private final User user = new User();

  protected void map(LongWritable key, Text value,
      org.apache.hadoop.mapreduce.Mapper.Context context) throws IOException,
      InterruptedException {

    StringTokenizer wordList = new StringTokenizer(value.toString(), "|");

    while (wordList.hasMoreTokens()) {

    user.setName(wordList.nextToken());

    user.setAge(Integer.parseInt(wordList.nextToken()));

    user.setGender(wordList.nextToken());

    user.setSalary(Long.parseLong(wordList.nextToken()));


    }

    ArrayList<Object> row = new ArrayList<Object>(4);

    row.add(0, user.getName());

    row.add(1, user.getAge());

    row.add(2, user.getGender());

    row.add(3, user.getSalary());


    Writable w = serde.serialize(row, oip);

    context.write(NullWritable.get(), w);


  }

}
