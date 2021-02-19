package com.javamaster.b2c.cloud.test.pattern.effectivejava.overload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 2018/8/8.<br/>
 *
 * @author yudong
 */
public class CollectionClassifier {
    public static String classifier(Set<?> set) {
        return "set";
    }

    public static String classifier(List<?> list) {
        return "list";
    }

    public static String classifier(Collection<?> collection) {
        return "unknown Collection";
    }

    public static String goodClassifier(Collection<?> collection) {
        if (collection instanceof Set) {
            return "set";
        } else if (collection instanceof List) {
            return "list";
        } else {
            return "unknown Collection";
        }
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new ArrayList<String>(),
                new HashSet<String>(),
                new HashMap<String, Integer>().values()
        };
        for (Collection<?> collection : collections) {
            System.out.println(collection.getClass().getName() + " " + classifier(collection));
        }
        for (Collection<?> collection : collections) {
            System.out.println(collection.getClass().getName() + " " + goodClassifier(collection));
        }
    }
}
