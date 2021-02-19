package org.summerframework.summer.web.initial;

import org.summerframework.summer.common.utils.AnnoUtils;
import org.summerframework.summer.common.utils.ClassUtils;
import org.summerframework.summer.common.utils.ResUtils;
import org.summerframework.summer.core.ioc.AnnoConfigSummerApplicationContext;
import org.summerframework.summer.core.ioc.SummerApplicationContext;
import org.summerframework.summer.web.SummerApplicationInitializer;
import org.summerframework.summer.web.anno.SummerServletComponentScan;
import org.summerframework.summer.web.config.WebConfig;
import org.summerframework.summer.web.mvc.HandlerMapping;
import org.summerframework.summer.web.servlet.SummerDispatchServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2019/5/18
 */
public class SummerWebApplication implements SummerApplicationInitializer {
    private static Class<?> runClass;
    private ServletContext servletContext;
    private SummerApplicationContext applicationContext;
    private Set<Class<?>> loadedClasses = new HashSet<>();
    private Logger logger = Logger.getLogger(getClass().getName());


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        start(servletContext);
    }

    public void start(ServletContext context) throws ServletException {
        this.servletContext = context;
        runClass = loadStartClass();
        loadedClasses.addAll(loadStartPackageClasses());
        loadedClasses.addAll(loadClass());
        loadedClasses.addAll(loadFrameClass());
        loadedClasses = filterAnnoClass(loadedClasses);
        applicationContext = new AnnoConfigSummerApplicationContext(loadedClasses);
        initDispatchServlet();
        logger.info("application start success");
    }

    private Class loadStartClass() throws ServletException {
        File file = ResUtils.getFile(ResUtils.PackageType.META_INF, "application.class.start");
        logger.info("file:" + file.getAbsolutePath());
        if (file == null || !file.exists()) {
            throw new ServletException("META-INF/application.start.class doesn't exists");
        }
        try {
            return Class.forName(Files.readAllLines(file.toPath()).get(0));
        } catch (Exception e) {
            throw new ServletException("META-INF/load application.start.class file occurred error", e);
        }
    }

    private Set<Class<?>> loadStartPackageClasses() {
        return ClassUtils.getClassesFromPackage(runClass.getPackage().getName());
    }

    private Set<Class<?>> loadClass() {
        Set<Class<?>> set = new LinkedHashSet<>();
        List<String> packageNames = findSummerServletComponentScanBasePackages();
        for (String packageName : packageNames) {
            set.addAll(ClassUtils.getClassesFromPackage(packageName));
        }
        return set;
    }

    private Set<Class<?>> loadFrameClass() {
        return ClassUtils.getClassesFromPackage("org.summerframework.summer.web.processor");
    }

    private Set<Class<?>> filterAnnoClass(Set<Class<?>> classes) {
        return classes.stream().filter(clazz -> {
            if (clazz.isAnnotation()) {
                return false;
            } else {
                return true;
            }
        }).collect(Collectors.toSet());
    }

    private List<String> findSummerServletComponentScanBasePackages() {
        List<String> basePackages = new ArrayList<>(20);
        for (Class<?> aClass : loadedClasses) {
            SummerServletComponentScan scan = AnnoUtils.findAnno(aClass, SummerServletComponentScan.class);
            if (scan == null) {
                continue;
            }
            String[] packages = scan.basePackages();
            if (packages.length == 0) {
                continue;
            }
            basePackages.addAll(Arrays.asList(packages));
        }
        if (basePackages.isEmpty()) {
            basePackages.add(runClass.getPackage().getName());
        }
        return basePackages;
    }


    private void initDispatchServlet() {
        Map<String, HandlerMapping> mappingMap = (Map<String, HandlerMapping>) applicationContext.getObject(SummerDispatchServlet.HANDLER_MAPPING_MAP_KEY);
        SummerDispatchServlet summerDispatchServlet = new SummerDispatchServlet(mappingMap);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet(ClassUtils.beanName(SummerDispatchServlet.class),
                summerDispatchServlet);
        List<WebConfig> webConfigs = applicationContext.getBeanOfType(WebConfig.class);
        if (webConfigs.size() < 1) {
            WebConfig webConfig = new WebConfig() {
            };
            webConfig.configDispatchServlet(dynamic);
        } else if (webConfigs.size() == 1) {
            WebConfig webConfig = webConfigs.get(0);
            webConfig.configDispatchServlet(dynamic);
        } else {
            throw new IllegalStateException("found multi WebConfig beans,only allow one");
        }
        logger.info("init summer dispatchServlet finish, the prefixes are:" + dynamic.getMappings());

    }

}
