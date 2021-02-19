package org.javamaster.b2c.open.feign.service;

import org.javamaster.b2c.open.feign.model.Pageable;
import org.javamaster.b2c.open.feign.model.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/7/31
 */
@FeignClient(value = "b2c-open-feign-server", fallback = StoreClientServiceFallbackImpl.class)
// @FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface StoreClientService {

    @RequestMapping(method = RequestMethod.GET, value = "/getStores")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.POST, value = "/getStoresPage")
    Map<String, Object> getStores(Pageable pageable);

}
