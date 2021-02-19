package org.javamaster.maven.plugin.spi;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.*;
import org.objectweb.asm.ClassReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成编译时注解处理器的描述文件javax.annotation.processing.Processor
 *
 * @author yudong
 */
@Execute(phase = LifecyclePhase.PREPARE_PACKAGE, goal = "generate-services-file")
@Mojo(name = "generate-services-file", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
public class GenerateServicesFileMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project.build.directory}")
    private File directory;
    @Parameter(defaultValue = "${project.build.outputDirectory}")
    private File outputDirectory;
    @Parameter(defaultValue = "${project.build.sourceDirectory}")
    private File sourceDirectory;

    @Override
    public void execute() throws MojoExecutionException {
        List<File> javaClassFiles = new ArrayList<>();
        getAndFillJavaClassFiles(outputDirectory.getAbsolutePath(), javaClassFiles);
        File dir = new File(outputDirectory + "/META-INF/services");
        dir.mkdirs();
        File processorFile = new File(dir, "javax.annotation.processing.Processor");
        try {
            assert processorFile.exists() || processorFile.createNewFile();
        } catch (Exception e) {
            throw new MojoExecutionException("error", e);
        }
        List<String> classNames = getProcessorClassNames(javaClassFiles);
        try (FileWriter w = new FileWriter(processorFile, true)) {
            for (String className : classNames) {
                w.write(className + "\r\n");
            }
        } catch (IOException e) {
            throw new MojoExecutionException("error", e);
        }
    }

    public static void getAndFillJavaClassFiles(String dir, List<File> javaFiles) {
        File file = new File(dir);
        File[] files = file.listFiles();
        assert files != null;
        for (File tempFile : files) {
            if (tempFile.isDirectory()) {
                getAndFillJavaClassFiles(tempFile.getAbsolutePath(), javaFiles);
            } else {
                if (tempFile.getName().endsWith(".class")) {
                    javaFiles.add(tempFile);
                }
            }
        }
    }

    public List<String> getProcessorClassNames(List<File> javaClassFiles) {
        try {
            List<String> classNames = new ArrayList<>();
            for (File file : javaClassFiles) {
                ClassReader classFileReader = new ClassReader(new FileInputStream(file));
                String superClassName = classFileReader.getSuperName().replace("/", ".");
                boolean isAnnotationProcessor = "javax.annotation.processing.AbstractProcessor".equals(superClassName);
                if (!isAnnotationProcessor) {
                    continue;
                }
                String className = classFileReader.getClassName().replace("/", ".");
                classNames.add(className);
            }
            return classNames;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
