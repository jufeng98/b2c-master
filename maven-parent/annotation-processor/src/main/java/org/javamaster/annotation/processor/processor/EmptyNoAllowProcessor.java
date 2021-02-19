package org.javamaster.annotation.processor.processor;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.*;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.*;
import org.javamaster.annotation.processor.annotation.EmptyNoAllow;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Set;

/**
 * 处理EmptyNoAllow注解,加入相关判空逻辑
 * Created on 2019/1/9.<br/>
 *
 * @author yudong
 */
@SupportedAnnotationTypes("org.javamaster.annotation.processor.annotation.EmptyNoAllow")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class EmptyNoAllowProcessor extends AbstractProcessor {
    private Messager messager;
    private JavacTrees trees;
    private TreeMaker treeMaker;
    private Names names;
    private ProcessingEnvironment processingEnv;
    private Elements elementUtils;
    private Types typeUtils;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.processingEnv = processingEnv;
        this.elementUtils = processingEnv.getElementUtils();
        this.typeUtils = processingEnv.getTypeUtils();
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("EmptyNoAllowProcessor processingOver:" + roundEnv.processingOver());
        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(EmptyNoAllow.class);
        set.forEach(element -> {
            if (element.getKind() == ElementKind.PARAMETER) {
                Symbol.VarSymbol varSymbol = ((Symbol.VarSymbol) element);
                Symbol.MethodSymbol methodSymbol = ((Symbol.MethodSymbol) varSymbol.owner);
                JCTree jcTree = trees.getTree(methodSymbol);
                jcTree.accept(new JCTree.Visitor() {
                    @Override
                    public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
                        JCTree.JCBlock jcBlock = jcMethodDecl.body;
                        List<JCTree.JCStatement> list = jcBlock.stats;
                        List<JCTree.JCExpression> typeargs = List.nil();
                        JCTree.JCIdent jcIdent = treeMaker.Ident(names.fromString("AssertUtils"));
                        Name name = names.fromString("isNotEmpty");
                        JCTree.JCFieldAccess meth = new JCTree.JCFieldAccess(jcIdent, name, null) {
                        };
                        List<JCTree.JCExpression> args = List.nil();
                        JCTree.JCIdent jcIdent1 = treeMaker.Ident(varSymbol.getSimpleName());
                        TypeTag typeTag = TypeTag.CLASS;
                        SymbolMetadata symbolMetadata = varSymbol.getMetadata();
                        List<Attribute.Compound> compounds = symbolMetadata.getDeclarationAttributes();
                        Attribute.Compound compound = compounds.get(0);
                        List<Pair<Symbol.MethodSymbol, Attribute>> pairList = compound.getValue().values;
                        String msg = "\u53c2\u6570\u4e0d\u80fd\u4e3a\u7a7a,\u7f16\u8bd1\u6ce8\u89e3\u5904\u7406\u5668\u751f\u6210\u7684\u4ee3\u7801";
                        if (pairList.length() != 0) {
                            msg = String.valueOf(pairList.get(0).snd.getValue());
                        }
                        JCTree.JCLiteral jcLiteral = treeMaker.Literal(typeTag, msg);
                        args = args.append(jcIdent1);
                        args = args.append(jcLiteral);
                        JCTree.JCMethodInvocation jcMethodInvocation = new JCTree.JCMethodInvocation(typeargs, meth, args) {
                        };
                        JCTree.JCExpressionStatement jcExpressionStatement = new JCTree.JCExpressionStatement(jcMethodInvocation) {
                        };
                        jcBlock.stats = list.prepend(jcExpressionStatement);
                    }
                });

            }
        });
        return true;
    }
}
