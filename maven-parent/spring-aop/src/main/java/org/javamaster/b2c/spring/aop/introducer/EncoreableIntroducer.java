package org.javamaster.b2c.spring.aop.introducer;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @date 2020/7/31
 */
@Aspect
@Component
public class EncoreableIntroducer {
    /**
     * SpringService后面的+号表示其所有的子类型,而不是SpringService本身
     */
    @DeclareParents(value = "org.javamaster.b2c.spring.aop.service.SpringService+", defaultImpl = EncoreableServiceImpl.class)
    public static EncoreableService encoreableService;
}
