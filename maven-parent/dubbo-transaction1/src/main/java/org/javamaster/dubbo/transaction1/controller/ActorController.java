package org.javamaster.dubbo.transaction1.controller;

import org.javamaster.dubbo.transaction1.model.ActorReqVo;
import org.javamaster.dubbo.transaction1.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yudong
 * @date 2020/6/29
 */
@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/saveActor")
    public String saveActor() {
        ActorReqVo actorReqVo = new ActorReqVo();
        actorReqVo.setFirstName("Liang");
        actorReqVo.setLastName("Yudong");
        actorService.tryInsertActor(actorReqVo);
        return "保存成功";
    }

    @GetMapping("/saveActorDtx")
    public String saveActorDtx() {
        ActorReqVo actorReqVo = new ActorReqVo();
        actorReqVo.setFirstName("Liang");
        actorReqVo.setLastName("Yudong");
        actorService.dtxInsertActor(actorReqVo);
        return "保存成功";
    }

    @GetMapping("/saveActorTimeOut")
    public String saveActorTimeOut() {
        ActorReqVo actorReqVo = new ActorReqVo();
        actorReqVo.setFirstName("Liang");
        actorReqVo.setLastName("Yudong");
        actorService.saveActorTimeOut(actorReqVo);
        return "保存成功";
    }

}
