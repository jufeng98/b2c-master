package com.javamaster.b2c.cloud.test.scheduled.controller;

import com.javamaster.b2c.cloud.test.scheduled.config.SchuduledCronProperties;
import com.javamaster.b2c.cloud.test.scheduled.entity.SpringSchuduledCron;
import com.javamaster.b2c.cloud.test.scheduled.respsitory.SpringSchuduledCronRepository;
import com.javamaster.b2c.cloud.test.scheduled.util.CronUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yudong
 * @date 2019/5/10
 */
@Controller
@RequestMapping("/scheduled/task")
public class TaskController {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private SchuduledCronProperties schuduledCronProperties;
    @Autowired
    private SpringSchuduledCronRepository cronRepository;

    @RequestMapping("/taskList")
    public String taskList(Model model) {
        model.addAttribute("cronList", schuduledCronProperties.getCronList());
        return "task-list";
    }

    @ResponseBody
    @RequestMapping("/editTaskCron")
    public String editTaskCron(String cronKey, String newCron) {
        if (!CronUtils.isValidExpression(newCron)) {
            throw new RuntimeException("失败,非法表达式:" + newCron);
        }
        SpringSchuduledCron springSchuduledCron = schuduledCronProperties.findByCronKey(cronKey);
        int res = cronRepository.updateCronExpressionByCronKey(newCron, cronKey);
        springSchuduledCron.setCronExpression(newCron);
        return "成功:" + res;
    }

    @ResponseBody
    @RequestMapping("/runTaskCron")
    public String runTaskCron(String cronKey) {
        ((Runnable) context.getBean(cronKey)).run();
        return "成功";
    }

    @ResponseBody
    @RequestMapping("/changeStatusTaskCron")
    public String changeStatusTaskCron(Integer status, String cronKey) {
        SpringSchuduledCron springSchuduledCron = schuduledCronProperties.findByCronKey(cronKey);
        int res = cronRepository.updateStatusByCronKey(status, cronKey);
        springSchuduledCron.setStatus(status);
        return "成功:" + res;
    }

}
