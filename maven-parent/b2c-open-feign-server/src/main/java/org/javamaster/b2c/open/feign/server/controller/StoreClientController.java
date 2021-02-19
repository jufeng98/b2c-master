package org.javamaster.b2c.open.feign.server.controller;

import org.javamaster.b2c.open.feign.server.model.Pageable;
import org.javamaster.b2c.open.feign.server.model.Store;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2020/7/31
 */
@RestController
public class StoreClientController {

    private static List<Store> stores = new ArrayList<>();

    static {
        for (int i = 0; i < 15; i++) {
            Store store = new Store();
            store.setId((long) i);
            store.setName("name" + i);
            stores.add(store);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getStores")
    List<Store> getStores() {
        return stores;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getStoresPage", produces = "application/json")
    Map<String, Object> getStoresPage(@RequestBody Pageable pageable) {
        Map<String, Object> map = new HashMap<>(5);
        map.put("total", stores.size());
        map.put("data",
                stores.stream()
                        .skip((pageable.getPageNum() - 1) * pageable.getPageSize())
                        .limit(pageable.getPageSize())
                        .collect(Collectors.toList())
        );
        return map;
    }

}
