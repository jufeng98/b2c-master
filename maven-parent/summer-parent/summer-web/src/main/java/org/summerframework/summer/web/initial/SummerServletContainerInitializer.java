package org.summerframework.summer.web.initial;

import org.summerframework.summer.web.SummerApplicationInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author yudong
 * @date 2019/5/14
 */
@HandlesTypes(SummerApplicationInitializer.class)
public class SummerServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> summerAppInitializerClasses, ServletContext servletContext) throws ServletException {
        if (summerAppInitializerClasses == null || summerAppInitializerClasses.isEmpty()) {
            servletContext.log("No SummerApplicationInitializer types detected on classpath");
            return;
        }
        List<SummerApplicationInitializer> initializers = new LinkedList<>();
        for (Class<?> waiClass : summerAppInitializerClasses) {
            if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
                    SummerApplicationInitializer.class.isAssignableFrom(waiClass)) {
                try {
                    initializers.add((SummerApplicationInitializer) waiClass.newInstance());
                } catch (Exception e) {
                    throw new ServletException("Failed to instantiate SummerApplicationInitializer class", e);
                }
            }
        }
        if (initializers.isEmpty()) {
            servletContext.log("No SummerApplicationInitializer types detected on classpath");
            return;
        }
        for (SummerApplicationInitializer initializer : initializers) {
            initializer.onStartup(servletContext);
        }
    }

}
