package org.javamaster.annotation.processor.client;

// Assert类的导入勿删,否则这个类编译会失败

import com.sun.tools.javac.util.Assert;
import org.javamaster.annotation.processor.annotation.EmptyNoAllow;


/**
 * Created on 2019/1/9.<br/>
 *
 * @author yudong
 */
public class EmptyNoAllowUtils {

    public static int getLength(@EmptyNoAllow(value = "name不能为空") String name, @EmptyNoAllow String desc) {
        System.out.println(Assert.class);
        return name.length() + desc.length();
    }
}
