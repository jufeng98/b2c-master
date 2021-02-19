package org.javamaster.dubbo.transaction3.api;

import org.mengyun.tcctransaction.api.Compensable;

import java.util.List;

/**
 * @author yudong
 * @date 2020/6/29
 */
public interface FilmActorDubboService {
    @Compensable
    List<Integer> tryInsertFilmActors(int actorId, List<Integer> filmIds);

    void confirmInsertFilmActors(int actorId, List<Integer> filmIds);

    void cancelInsertFilmActors(int actorId, List<Integer> filmIds);

    List<Integer> dtxInsertFilmActors(int actorId, List<Integer> filmIds);

    List<Integer> timeOutInsertFilmActors(int i, List<Integer> asList);
}
