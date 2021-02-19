package com.javamaster.b2c.cloud.test.learn.java.mustache;

import com.javamaster.b2c.cloud.test.learn.java.mustache.model.CommonExample;
import com.javamaster.b2c.cloud.test.learn.java.mustache.model.ControllerExample;
import com.javamaster.b2c.cloud.test.learn.java.mustache.model.ControllerMethodExample;
import com.javamaster.b2c.cloud.test.learn.java.mustache.model.MapperExample;
import com.javamaster.b2c.cloud.test.learn.java.mustache.model.MapperXmlExample;
import com.javamaster.b2c.cloud.test.learn.java.mustache.model.ModelExample;
import com.javamaster.b2c.cloud.test.learn.java.mustache.model.ServiceExample;
import com.javamaster.b2c.cloud.test.learn.java.mustache.model.ServiceImplExample;
import com.javamaster.b2c.cloud.test.learn.java.mustache.model.ServiceMethodExample;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2019/7/19
 */
public class MustacheTest {
    static MustacheFactory mf;

    static ControllerExample controllerExample;
    static ServiceExample serviceExample;
    static ServiceImplExample serviceImplExample;
    static ModelExample modelExample;
    static ControllerMethodExample controllerMethodExample;
    static ServiceMethodExample serviceMethodExample;

    static MapperExample mapperExample;
    static MapperXmlExample mapperXmlExample;

    static File conFile;
    static File serFile;
    static File serImplFile;
    static File modelReqFile;
    static File modelResFile;

    static File mapperFile;
    static File mapperXmlFile;

    @Test
    public void clean() {
        conFile.delete();
        serFile.delete();
        serImplFile.delete();
        modelReqFile.delete();
        modelResFile.delete();
        mapperFile.delete();
        mapperXmlFile.delete();
    }

    @BeforeClass
    public static void init() {
        mf = new DefaultMustacheFactory();

        String author = "yudong";
        String projectPath = "D:\\b2c\\online-exam\\b2c-parent\\b2c-core\\src\\main\\java";
        String resourcePath = "D:\\b2c\\online-exam\\b2c-parent\\b2c-core\\src\\main\\resources";

        String packageName = "org.javamaster.b2c.core";
        String controllerPackageName = "";
        String servicePackageName = "";
        String modelPackageName = "";

        String controllerClassName = "ExamsController".replace("Controller", "");
        String serviceClassName = "".replace("Service", "");
        String serviceImplClassName = "".replace("ServiceImpl", "");

        String classComment = "考试管理";
        String classMapping = "/core/certs";
        String methodName = "delExams";
        String methodComment = "删除考试";
        String serviceVariableName = controllerClassName.substring(0, 1).toLowerCase() + controllerClassName.substring(1);
        String modelName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        CommonExample commonExample = new CommonExample();
        commonExample.setPackageName(packageName);
        commonExample.setAuthor(author);
        commonExample.setClassName(controllerClassName);
        commonExample.setClassComment(classComment);

        controllerExample = new ControllerExample();
        BeanUtils.copyProperties(commonExample, controllerExample);
        String controllerPrefix = "\\controller";
        if (StringUtils.isNotBlank(controllerPackageName)) {
            controllerExample.setPackageName(controllerPackageName);
            controllerPrefix = "";
        }
        controllerExample.setServiceVariableName(serviceVariableName);
        controllerExample.setClassMapping(classMapping);

        serviceExample = new ServiceExample();
        BeanUtils.copyProperties(commonExample, serviceExample);
        String servicePrefix = "\\service";
        if (StringUtils.isNotBlank(servicePackageName)) {
            serviceExample.setPackageName(servicePackageName);
            servicePrefix = "";
        }
        if (StringUtils.isNotBlank(serviceClassName)) {
            serviceExample.setClassName(serviceClassName);
        }

        serviceImplExample = new ServiceImplExample();
        BeanUtils.copyProperties(commonExample, serviceImplExample);
        if (StringUtils.isNotBlank(servicePackageName)) {
            serviceImplExample.setPackageName(servicePackageName);
        }
        if (StringUtils.isNotBlank(serviceImplClassName)) {
            serviceImplExample.setClassName(serviceImplClassName);
        }

        controllerMethodExample = new ControllerMethodExample();
        controllerMethodExample.setMethodMapping(methodName);
        controllerMethodExample.setMethodName(methodName);
        controllerMethodExample.setMethodBeanName(modelName);
        controllerMethodExample.setServiceVariableName(serviceVariableName);

        serviceMethodExample = new ServiceMethodExample();
        serviceMethodExample.setMethodComment(methodComment);
        serviceMethodExample.setMethodName(methodName);
        serviceMethodExample.setMethodBeanName(modelName);

        modelExample = new ModelExample();
        BeanUtils.copyProperties(commonExample, modelExample);
        String modelPrefix = "\\model\\vo";
        if (StringUtils.isNotBlank(modelPackageName)) {
            modelExample.setPackageName(modelPackageName);
            modelPrefix = "";
        }
        modelExample.setModelName(modelName);

        String mapperName = "MenusMapperExt";
        mapperExample = new MapperExample();
        mapperExample.setPackageName(packageName);
        mapperExample.setMapperName(mapperName);
        mapperXmlExample = new MapperXmlExample();
        mapperXmlExample.setNamespace(packageName + ".mapper." + mapperName);
        mapperXmlExample.setMapperLocation("mapper");
        mapperXmlExample.setMapperName(mapperName);

        String controllerPath = projectPath + "\\" + controllerExample.getPackageName().replace(".", "\\") + controllerPrefix;
        System.out.println(controllerPath);
        File controllerFile = createDirs(controllerPath);
        conFile = new File(controllerFile, controllerExample.getClassName() + "Controller.java");

        String servicePath = projectPath + "\\" + serviceExample.getPackageName().replace(".", "\\") + servicePrefix;
        System.out.println(servicePath);
        File serviceFile = createDirs(servicePath);
        serFile = new File(serviceFile, serviceExample.getClassName() + "Service.java");

        String serviceImplPath = projectPath + "\\" + serviceImplExample.getPackageName().replace(".", "\\") + servicePrefix + "\\impl";
        System.out.println(serviceImplPath);
        File serviceImplFile = createDirs(serviceImplPath);
        serImplFile = new File(serviceImplFile, serviceImplExample.getClassName() + "ServiceImpl.java");

        String serviceModelPath = projectPath + "\\" + modelExample.getPackageName().replace(".", "\\") + modelPrefix;
        System.out.println(serviceModelPath);
        File serviceModelFile = createDirs(serviceModelPath);
        modelReqFile = new File(serviceModelFile, modelName + "ReqVo.java");
        modelResFile = new File(serviceModelFile, modelName + "ResVo.java");

        String mapperPath = projectPath + "\\" + mapperExample.getPackageName().replace(".", "\\")+"\\mapper";
        mapperFile = new File(mapperPath, mapperExample.getMapperName() + ".java");

        String mapperXmlPath = resourcePath + "\\" + mapperXmlExample.getMapperLocation().replace(".", "\\");
        mapperXmlFile = new File(mapperXmlPath, mapperXmlExample.getMapperName() + ".xml");


        System.out.println("------");
        System.out.println();
    }

    @Test
    public void testCreateJavaFiles() throws Exception {
        Mustache mustache = mf.compile("template/Controller.mustache");
        mustache.execute(new PrintWriter(conFile), controllerExample).flush();

        mustache = mf.compile("template/Service.mustache");
        mustache.execute(new PrintWriter(serFile), serviceExample).flush();

        mustache = mf.compile("template/ServiceImpl.mustache");
        mustache.execute(new PrintWriter(serImplFile), serviceImplExample).flush();
    }

    @Test
    public void testCreateJavaFilesMethod() throws Exception {
        String reqModelName = modelReqFile.getName().replace(".java", "");
        String resModelName = modelResFile.getName().replace(".java", "");
        Mustache mustache = mf.compile("template/Model.mustache");
        modelExample.setModelFileName(reqModelName);
        mustache.execute(new PrintWriter(modelReqFile), modelExample).flush();
        modelExample.setModelFileName(resModelName);
        mustache.execute(new PrintWriter(modelResFile), modelExample).flush();

        append(conFile, controllerMethodExample, "template/ControllerMethod.mustache");

        append(serFile, serviceMethodExample, "template/ServiceMethod.mustache");

        append(serImplFile, serviceMethodExample, "template/ServiceImplMethod.mustache");

    }

    @Test
    public void testCreateMybatisMapper() throws Exception {
        Mustache mustache = mf.compile("template/Mapper.mustache");
        mustache.execute(new PrintWriter(mapperFile), mapperExample).flush();
        mustache = mf.compile("template/MapperXml.mustache");
        mustache.execute(new PrintWriter(mapperXmlFile), mapperXmlExample).flush();
    }

    private static void append(File file, Object example, String templateName) throws Exception {
        List<String> conFileContentList = Files.readAllLines(file.toPath());
        conFileContentList = conFileContentList.subList(0, conFileContentList.size() - 2);
        String classContent = conFileContentList.stream().collect(Collectors.joining("\r\n"));

        Mustache mustache = mf.compile(templateName);
        StringWriter stringWriter = new StringWriter();
        mustache.execute(new PrintWriter(stringWriter), example).flush();

        classContent = classContent + "\r\n\r\n" + stringWriter.toString() + "\r\n\r\n" + "}";
        System.out.println(classContent);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(classContent);
        printWriter.flush();
        printWriter.close();
    }

    private static File createDirs(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }
}
