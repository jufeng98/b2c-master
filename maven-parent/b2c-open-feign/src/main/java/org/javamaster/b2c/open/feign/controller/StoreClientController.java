package org.javamaster.b2c.open.feign.controller;

import cn.com.bluemoon.washservice.api.dubbo.collect.dto.ReceiveClothesReqsDto;
import cn.com.bluemoon.washservice.api.dubbo.collect.service.CollectOrderFeignService;
import com.google.common.collect.Lists;
import org.javamaster.b2c.open.feign.model.Pageable;
import org.javamaster.b2c.open.feign.model.Store;
import org.javamaster.b2c.open.feign.service.StoreClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private CollectOrderFeignService collectOrderFeignService;

    @RequestMapping(method = RequestMethod.GET, value = "/getStores")
    List<Store> getStores() {
        return storeClientService.getStores();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getStoresPage")
    Map<String, Object> getStoresPage(Pageable pageable) {
        return storeClientService.getStores(pageable);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getStoresPage1")
    Map<String, Object> getStoresPage1(@RequestBody Pageable pageable) {
        ReceiveClothesReqsDto dto = new ReceiveClothesReqsDto();
        ReceiveClothesReqsDto.ClothesInfo clothesInfo = new ReceiveClothesReqsDto.ClothesInfo();
        ReceiveClothesReqsDto.CurtainInfo curtainInfo = new ReceiveClothesReqsDto.CurtainInfo();
        curtainInfo.setCurtainAccessory(12);
        // curtainInfo.setCurtainRemark("hello");
        clothesInfo.setCurtainInfo(curtainInfo);
        dto.setClothesInfo(Lists.newArrayList(
                clothesInfo
        ));
        collectOrderFeignService.receiveClothes(dto);
        return null;
    }

}
