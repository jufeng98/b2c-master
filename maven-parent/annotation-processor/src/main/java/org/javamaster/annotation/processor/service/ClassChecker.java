package org.javamaster.annotation.processor.service;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.ElementScanner8;
import java.util.Set;


/**
 * Created on 2019/1/23.<br/>
 *
 * @author yudong
 */
public class ClassChecker implements RunOnce {

    @Override
    public void run(ProcessingEnvironment processingEnv, Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getRootElements();
        ClassScanner scanner8 = new ClassScanner();
        for (Element element : elements) {
            scanner8.scan(element);
        }
    }
}

class ClassScanner extends ElementScanner8<Set<? extends Element>, Element> {

    @Override
    public Set<? extends Element> visitVariable(VariableElement e, Element element) {
        String name = e.getSimpleName().toString();
        if (e.getConstantValue() != null) {
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') {
                    System.err.println(e.getEnclosingElement() + "\u7684\u9759\u6001\u5e38\u91cf" + name + "\u4e0d\u7b26\u5408\u547d\u540d\u89c4\u8303");
                    break;
                }
            }
        } else {
            if (name.contains("_")) {
                System.err.println(e.getEnclosingElement() + "\u7684\u53d8\u91cf" + name + "\u4e0d\u7b26\u5408\u9a7c\u5cf0\u547d\u540d\u6cd5");
            }
        }
        return super.visitVariable(e, element);
    }

    @Override
    public Set<? extends Element> visitPackage(PackageElement e, Element element) {
        return super.visitPackage(e, element);
    }

    @Override
    public Set<? extends Element> visitType(TypeElement e, Element element) {
        if (!(e.getSimpleName().charAt(0) >= 'A' && e.getSimpleName().charAt(0) <= 'Z')) {
            System.err.println(e.getQualifiedName() + "\u7684\u7c7b\u540d\u4e0d\u7b26\u5408\u9a7c\u5cf0\u547d\u540d\u6cd5");
        }
        return super.visitType(e, element);
    }

    @Override
    public Set<? extends Element> visitExecutable(ExecutableElement e, Element element) {
        if (e.getReturnType().getKind() == TypeKind.BOOLEAN) {
            if (!e.getSimpleName().toString().startsWith("is")) {
                System.err.println(e.getEnclosingElement() + "\u8fd4\u56deboolean\u503c\u7684\u65b9\u6cd5" + e.getSimpleName() + "\u5e94\u4ee5is\u5f00\u5934");
            }
        }
        return super.visitExecutable(e, element);
    }

    @Override
    public Set<? extends Element> visitTypeParameter(TypeParameterElement e, Element element) {
        return super.visitTypeParameter(e, element);
    }
}
