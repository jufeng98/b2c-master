package org.javamaster.b2c.test.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, String>> selectActors(List<Integer> actorIds);

    Map<String, String> selectUserInfo(Integer id);

    String translate(String word);
}
