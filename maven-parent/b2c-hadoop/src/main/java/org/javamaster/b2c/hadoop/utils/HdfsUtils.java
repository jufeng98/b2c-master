package org.javamaster.b2c.hadoop.utils;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @date 2021/2/14
 */
@Slf4j
@Component
public class HdfsUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 创建文件目录
     *
     * @param path
     */
    @SneakyThrows
    public static void mkdir(String path) {
        FileSystem fs = context.getBean(FileSystem.class);
        // 创建目录
        fs.mkdirs(new Path(path));
        //释放资源
        fs.close();
    }

    /**
     * 删除文件或者文件目录
     *
     * @param path
     */
    @SneakyThrows
    public static void rmdir(String path) {
        // 返回FileSystem对象
        FileSystem fs = context.getBean(FileSystem.class);
        // 删除文件或者文件目录  delete(Path f) 此方法已经弃用
        fs.delete(new Path(path), true);
        // 释放资源
        fs.close();
    }

    /**
     * 根据filter获取目录下的文件
     *
     * @param path
     * @param pathFilter
     * @return String[]
     */
    @SneakyThrows
    public static String[] listFile(String path, PathFilter pathFilter) {
        String[] files = new String[0];
        // 返回FileSystem对象
        FileSystem fs = context.getBean(FileSystem.class);

        FileStatus[] status;
        if (pathFilter != null) {
            // 根据filter列出目录内容
            status = fs.listStatus(new Path(path), pathFilter);
        } else {
            // 列出目录内容
            status = fs.listStatus(new Path(path));
        }
        // 获取目录下的所有文件路径
        Path[] listedPaths = FileUtil.stat2Paths(status);
        // 转换String[]
        if (listedPaths != null && listedPaths.length > 0) {
            files = new String[listedPaths.length];
            for (int i = 0; i < files.length; i++) {
                files[i] = listedPaths[i].toString();
            }
        }
        // 释放资源
        fs.close();
        return files;
    }

    /**
     * 文件上传至 HDFS
     *
     * @param delSrc    指是否删除源文件，true为删除，默认为false
     * @param overwrite
     * @param srcFile   源文件，上传文件路径
     * @param destPath  hdfs的目的路径
     */
    @SneakyThrows
    public static void copyFileToHDFS(boolean delSrc, boolean overwrite, String srcFile, String destPath) {
        // 源文件路径是Linux下的路径，如果在 windows 下测试，需要改写为Windows下的路径，比如D://hadoop/djt/weibo.txt
        Path srcPath = new Path(srcFile);
        Path dstPath = new Path(destPath);
        // 获取FileSystem对象
        FileSystem fs = context.getBean(FileSystem.class);
        fs.copyFromLocalFile(srcPath, dstPath);
        fs.copyFromLocalFile(delSrc, overwrite, srcPath, dstPath);
        //释放资源
        fs.close();
    }

    /**
     * 从 HDFS 下载文件
     *
     * @param srcFile
     * @param destPath 文件下载后,存放地址
     */
    @SneakyThrows
    public static void getFile(String srcFile, String destPath) {
        Path srcPath = new Path(srcFile);
        Path dstPath = new Path(destPath);
        // 获取FileSystem对象
        FileSystem fs = context.getBean(FileSystem.class);
        // 下载hdfs上的文件
        fs.copyToLocalFile(srcPath, dstPath);
        // 释放资源
        fs.close();
    }

    /**
     * 获取 HDFS 集群节点信息
     *
     * @return DatanodeInfo[]
     */
    @SneakyThrows
    public static DatanodeInfo[] getHDFSNodes() {
        // 获取所有节点
        DatanodeInfo[] dataNodeStats;
        // 返回FileSystem对象
        FileSystem fs = context.getBean(FileSystem.class);
        // 获取分布式文件系统
        DistributedFileSystem hdfs = (DistributedFileSystem) fs;
        dataNodeStats = hdfs.getDataNodeStats();
        return dataNodeStats;
    }

    /**
     * 查找某个文件在 HDFS集群的位置
     *
     * @param filePath
     * @return BlockLocation[]
     */
    @SneakyThrows
    public static BlockLocation[] getFileBlockLocations(String filePath) {
        Path path = new Path(filePath);
        // 文件块位置列表
        BlockLocation[] blkLocations;
        // 返回FileSystem对象
        FileSystem fs = context.getBean(FileSystem.class);
        // 获取文件目录
        FileStatus filestatus = fs.getFileStatus(path);
        //获取文件块位置列表
        blkLocations = fs.getFileBlockLocations(filestatus, 0, filestatus.getLen());
        return blkLocations;
    }


    /**
     * 判断目录是否存在
     *
     * @param filePath
     * @return
     */
    @SneakyThrows
    public static boolean existDir(String filePath) {
        Path path = new Path(filePath);
        // FileSystem对象
        FileSystem fs = context.getBean(FileSystem.class);
        return fs.exists(path);
    }


}
