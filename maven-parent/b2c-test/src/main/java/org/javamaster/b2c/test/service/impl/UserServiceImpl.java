package org.javamaster.b2c.test.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RemoteService remoteService;
    @Autowired
    private TestService testService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Map<String, String>> selectActors(List<Integer> actorIds) {
        HashMap<String, String> map = new HashMap<>(actorIds.size(), 1);
        for (Integer actorId : actorIds) {
            map.put("actorId", actorId + "");
            String phone = remoteService.getUserMobileById(actorId);
            map.put("phone", phone);
            log.info("actorId:{},phone:{}", actorId, phone);
        }
        return Collections.singletonList(map);
    }

    @Override
    public Map<String, String> selectUserInfo(Integer id) {
        HashMap<String, String> map = new HashMap<>(8, 1);
        map.put("name", testService.getName(id));
        map.put("age", testService.getAge(id) + "");
        map.put("company", testService.getCompany(id));
        return map;
    }

    @Override
    public String translate(String word) {
        String url = "https://www.baidu.com/translate?word={0}";
        return restTemplate.getForObject(url, String.class, word);
    }

}
