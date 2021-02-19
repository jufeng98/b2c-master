package com.javamaster.b2c.cloud.test.scheduled.config;

import com.javamaster.b2c.cloud.test.scheduled.entity.SpringSchuduledCron;
import com.javamaster.b2c.cloud.test.scheduled.respsitory.SpringSchuduledCronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2019/5/10
 */
@Component
public class SchuduledCronProperties {

    @Autowired
    private SpringSchuduledCronRepository cronRepository;

    private List<SpringSchuduledCron> cronList;

    @PostConstruct
    private void init() {
        List<SpringSchuduledCron> springSchuduledCrons = cronRepository.findAll().stream()
                .filter(cron -> !"ignoreTask".equals(cron.getCronKey())).collect(Collectors.toList());
        this.cronList = springSchuduledCrons;
    }

    public SpringSchuduledCron findByCronKey(String cronKey) {
        List<SpringSchuduledCron> list = cronList.stream()
                .filter(cron -> cronKey.equals(cron.getCronKey())).collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new RuntimeException("失败,cronKey有误:" + cronKey);
        }
        return list.get(0);
    }

    public List<SpringSchuduledCron> getCronList() {
        return cronList;
    }

    public void setCronList(List<SpringSchuduledCron> cronList) {
        this.cronList = cronList;
    }
}
