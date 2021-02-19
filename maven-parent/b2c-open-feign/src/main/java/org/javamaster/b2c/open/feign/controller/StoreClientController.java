package org.javamaster.b2c.open.feign.controller;

import org.javamaster.b2c.open.feign.model.Pageable;
import org.javamaster.b2c.open.feign.model.Store;
import org.javamaster.b2c.open.feign.service.StoreClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/7/31
 */
@RestController
public class StoreClientController {

    @Autowired
    private StoreClientService storeClientService;


    @RequestMapping(method = RequestMethod.GET, value = "/getStores")
    List<Store> getStores() {
        return storeClientService.getStores();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getStoresPage")
    Map<String, Object> getStoresPage(Pageable pageable) {
        return storeClientService.getStores(pageable);
    }

}
