package org.javamaster.b2c.open.feign.service;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.open.feign.model.Pageable;
import org.javamaster.b2c.open.feign.model.Store;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/7/31
 */
@Slf4j
@Service
public class StoreClientServiceFallbackImpl implements StoreClientService {


    @Override
    public List<Store> getStores() {
        log.error("error1");
        return null;
    }

    @Override
    public Map<String, Object> getStores(Pageable pageable) {
        log.error("error2");
        return null;
    }

    @Override
    public Map<String, Object> getStores1(Pageable pageable) {
        return null;
    }
}
