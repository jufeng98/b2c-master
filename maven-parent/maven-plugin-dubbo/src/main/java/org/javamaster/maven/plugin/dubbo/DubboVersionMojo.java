package org.javamaster.maven.plugin.dubbo;

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
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.javamaster.maven.plugin.dubbo.model.Dubbo;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

/*
要使用此目标需在pom文件中这样配置,并且maven命令行加上此参数-Ddubbo.profile=dev,即clean install -Dmaven.test.skip=true
    <build>
        <plugins>
            <plugin>
                <groupId>org.javamaster.maven</groupId>
                <artifactId>maven-plugin-dubbo</artifactId>
                <version>1.0-SNAPSHOT</version>
                <configuration>
                    <dubboXmlFileName>dubbo-config.xml</dubboXmlFileName>
                    <references>
                        <reference>
                            <interfaceName>org.javamaster.b2c.dubbo.server.api.service.UserDubboService</interfaceName>
                            <newVersion>1.0.0-yudong</newVersion>
                        </reference>
                    </references>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>change-dubbo-version</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
 */

/**
 * 修改dubbo配置文件的service或reference的服务的version
 *
 * @author yudong
 */
@Mojo(name = "change-dubbo-version", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class DubboVersionMojo extends AbstractMojo {
    /**
     * pom文件所在的目录
     */
    @Parameter(defaultValue = "${project.basedir}")
    private File basedir;
    /**
     * target目录
     */
    @Parameter(defaultValue = "${project.build.directory}")
    private File directory;
    /**
     * classes目录
     */
    @Parameter(defaultValue = "${project.build.outputDirectory}")
    private File outputDirectory;
    /**
     * src/main/java目录
     */
    @Parameter(defaultValue = "${project.build.sourceDirectory}")
    private File sourceDirectory;
    /**
     * dubbo配置文件名称(默认位于resources目录)
     */
    @Parameter(required = true)
    private String dubboXmlFileName;
    /**
     * dubbo配置文件编码,默认UTF-8
     */
    @Parameter(defaultValue = "UTF-8")
    private String dubboXmlFileEncode;
    /**
     * 服务接口完整名称及要修改的version的值,
     */
    @Parameter
    private Dubbo[] services;
    /**
     * 服务接口完整名称及要修改的version的值
     */
    @Parameter
    private Dubbo[] references;

    @Parameter(property = "dubbo.profile")
    private String dubboProfile;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("The maven-plugin-dubbo was made by yudong");
        getLog().info("dubbo.profile:" + dubboProfile);
        if (!"dev".equals(dubboProfile)) {
            getLog().info("dubbo.profile is not dev, the plugin target dubbo will not execute");
            getLog().info("add -Ddubbo.profile=dev to common line to active the plugin target");
            return;
        }
        getLog().info("dubboXmlFileName:" + dubboXmlFileName);
        getLog().info("services:" + Arrays.toString(services));
        getLog().info("references:" + Arrays.toString(references));
        File dubboXmlFile = new File(outputDirectory, dubboXmlFileName);
        if (!dubboXmlFile.exists()) {
            getLog().info("no " + dubboXmlFileName + " file found:" + dubboXmlFile.getAbsolutePath());
            return;
        }

        try {
            Document document = DocumentHelper.parseText(new String(Files.readAllBytes(dubboXmlFile.toPath()), dubboXmlFileEncode));
            if (services != null) {
                for (Dubbo service : services) {
                    findAndSetNewDubboVersion(document, "service", service.getInterfaceName(), service.getNewVersion());
                }
            }
            if (references != null) {
                for (Dubbo reference : references) {
                    findAndSetNewDubboVersion(document, "reference", reference.getInterfaceName(), reference.getNewVersion());
                }
            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(dubboXmlFileEncode);
            File dubboOutputXmlFile = new File(outputDirectory, dubboXmlFileName);
            XMLWriter writer = new XMLWriter(new FileWriter(dubboOutputXmlFile), format);
            writer.write(document);
            writer.close();
        } catch (Exception e) {
            throw new MojoExecutionException("", e);
        }
    }

    private static void findAndSetNewDubboVersion(Document document, String type, String serviceName, String newVersion) {
        DefaultElement rootElement = (DefaultElement) document.getRootElement();
        List nodes = rootElement.elements(type, new Namespace("dubbo", "http://code.alibabatech.com/schema/dubbo"));
        for (Object obj : nodes) {
            Element node = (Element) obj;
            if (node.attribute("interface").getValue().trim().equals(serviceName)) {
                node.attribute("version").setValue(newVersion);
                break;
            }
        }
    }

}
