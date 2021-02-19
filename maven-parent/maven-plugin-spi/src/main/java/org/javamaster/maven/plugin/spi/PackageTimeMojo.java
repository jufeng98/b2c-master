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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Goal which touches a timestamp file.
 *
 * @author yudong
 */
@Mojo(name = "package-time", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class PackageTimeMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project.build.directory}", required = true)
    private File directory;
    @Parameter(defaultValue = "${project.build.outputDirectory}", required = true)
    private File outputDirectory;
    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true)
    private File sourceDirectory;
    @Parameter
    private String[] includes;

    @Override
    public void execute() throws MojoExecutionException {
        File f = directory;

        assert f.exists() || f.mkdirs();

        getLog().info("this is a special plugin by yudong");
        getLog().info("includes:" + Arrays.toString(includes));

        File touch = new File(f, "touch.txt");
        File timeFile = new File(outputDirectory, "application.yml");


        FileWriter w = null;
        FileWriter w1 = null;
        try {
            w = new FileWriter(touch);
            w.write("how cool this is!" + Arrays.toString(includes));
            getLog().info("path:" + timeFile.getPath());
            byte[] bytes = Files.readAllBytes(timeFile.toPath());
            String s = new String(bytes, StandardCharsets.UTF_8);
            s = s.replace("${plugin-package-time}", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            getLog().info("replace package time success");

            w1 = new FileWriter(timeFile);
            w1.write(s);

        } catch (IOException e) {
            throw new MojoExecutionException("Error creating file " + touch, e);
        } finally {
            if (w1 != null) {
                try {
                    w.close();
                    w1.close();
                } catch (IOException ignored) {
                }
            }
        }

    }
}
