package org.javamaster.b2c.test.hadoop;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.javamaster.b2c.hadoop.mapreduce.WordCountMap;
import org.javamaster.b2c.hadoop.mapreduce.WordCountReduce;
import static org.javamaster.b2c.test.hadoop.HadoopApplicationTests.PROJECT_PATH;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author yudong
 * @date 2021/2/15
 */
public class WordCountTests {

    @Test
    @SneakyThrows
    public void wordCount() {
        JobConf conf = new JobConf();
        conf.setJobName("wordCount");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(WordCountMap.class);
        conf.setCombinerClass(WordCountReduce.class);
        conf.setReducerClass(WordCountReduce.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        File hadoopPath = new File(PROJECT_PATH, "src/test/resources/hadoop");
        FileInputFormat.setInputPaths(conf, new Path(hadoopPath.getAbsolutePath()));

        File targetPath = new File(PROJECT_PATH, "target/output");
        FileUtils.deleteDirectory(targetPath);
        Path path = new Path(targetPath.getAbsolutePath());
        FileOutputFormat.setOutputPath(conf, path);

        JobClient.runJob(conf);
    }

}
