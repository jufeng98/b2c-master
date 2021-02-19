package org.javamaster.b2c.test.service;

import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/10/23
 */
public interface TestService {

    List<Map<String, String>> selectActors(List<Integer> actorIds);

}
