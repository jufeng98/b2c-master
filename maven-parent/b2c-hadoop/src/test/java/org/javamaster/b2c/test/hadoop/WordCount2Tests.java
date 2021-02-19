package org.javamaster.b2c.test.hadoop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.javamaster.b2c.hadoop.mapreduce.IntSumReducer;
import org.javamaster.b2c.hadoop.mapreduce.TokenizerMapper;
import static org.javamaster.b2c.test.hadoop.HadoopApplicationTests.PROJECT_PATH;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author yudong
 * @date 2021/2/15
 */
@Slf4j
public class WordCount2Tests {

    @Test
    public void test() throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");

        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        File hadoopPath = new File(PROJECT_PATH, "src/test/resources/hadoop");
        FileInputFormat.addInputPath(job, new Path(hadoopPath.getAbsolutePath()));

        File targetPath = new File(PROJECT_PATH, "target/output");
        FileUtils.deleteDirectory(targetPath);
        Path path = new Path(targetPath.getAbsolutePath());
        FileOutputFormat.setOutputPath(job, path);

        boolean b = job.waitForCompletion(true);
        log.info("{}", b);
    }

}
