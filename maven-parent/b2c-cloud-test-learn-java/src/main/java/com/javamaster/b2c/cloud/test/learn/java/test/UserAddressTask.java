package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.MallUserAddress;
import com.javamaster.b2c.cloud.test.learn.java.utils.AmapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2020/3/2
 */
@Slf4j
public class UserAddressTask implements Runnable {
    List<MallUserAddress> list;
    JdbcTemplate jdbcTemplate;

    public UserAddressTask(List<MallUserAddress> list, JdbcTemplate jdbcTemplate) {
        this.list = list;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run() {
        handler();
    }

    private void handler() {
        List<String> addresses = list.stream().map(mallUserAddress -> {
            String address = mallUserAddress.getProvinceName() + mallUserAddress.getCityName()
                    + mallUserAddress.getCountyName() + mallUserAddress.getStreetName()
                    + mallUserAddress.getVillageName() + mallUserAddress.getAddress();
            address = address.replaceAll("\\s", "");
            return address;
        }).collect(Collectors.toList());
        try {
            List<String> locations = AmapUtils.getLngLat(addresses);
            for (int i = 0; i < locations.size(); i++) {
                String location = locations.get(i);
                if (!"[]".equals(location)) {
                    String[] strs = location.split(",");
                    double lng = Double.parseDouble(strs[0]);
                    double lat = Double.parseDouble(strs[1]);
                    updateAddress(true, lng, lat, list.get(i).getAddressId());
                } else {
                    updateAddress(false, 0, 0, list.get(i).getAddressId());
                }
            }
        } catch (Exception e) {
            log.error("error req:{}", list.stream().map(MallUserAddress::getAddressId).collect(Collectors.toList()), e);
        }
    }


    private void updateAddress(boolean match, double lng, double lat, String addressId) {
        if (match) {
            jdbcTemplate.update("update mall_user_address set longitude = ?, latitude = ?,exception_flag = 0 where address_id = ?", lng, lat, addressId);
        } else {
            jdbcTemplate.update("update mall_user_address set exception_flag = 1 where address_id = ?", addressId);
        }
    }

}
