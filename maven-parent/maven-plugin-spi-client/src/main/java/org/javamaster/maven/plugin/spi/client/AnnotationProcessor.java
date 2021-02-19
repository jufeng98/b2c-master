package org.javamaster.maven.plugin.spi.client;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * <p>\u53ea\u80fd\u5b58\u5728\u4e00\u4e2a\u652f\u6301\u6240\u6709\u6ce8\u89e3\u7684\u5904\u7406\u7c7b</p>
 * Created on 2019/1/18.<br/>
 *
 * @author yudong
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.processingEnv = processingEnv;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return true;
    }

}
