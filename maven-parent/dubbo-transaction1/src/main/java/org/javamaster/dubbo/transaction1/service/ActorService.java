package org.javamaster.dubbo.transaction1.service;

import org.javamaster.dubbo.transaction1.model.ActorReqVo;
import org.mengyun.tcctransaction.api.Compensable;

/**
 * @author yudong
 * @date 2020/6/29
 */
public interface ActorService {
    @Compensable
    void tryInsertActor(ActorReqVo actorReqVo);

    void confirmInsertActor(ActorReqVo actorReqVo);

    void cancelInsertActor(ActorReqVo actorReqVo);

    void dtxInsertActor(ActorReqVo actorReqVo);

    void saveActorTimeOut(ActorReqVo actorReqVo);
}
