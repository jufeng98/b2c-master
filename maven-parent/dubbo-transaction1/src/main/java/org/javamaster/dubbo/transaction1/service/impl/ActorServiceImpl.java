package org.javamaster.dubbo.transaction1.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.dubbo.transaction1.model.ActorReqVo;
import org.javamaster.dubbo.transaction1.service.ActorService;
import org.javamaster.dubbo.transaction2.api.FilmDubboService;
import org.javamaster.dubbo.transaction2.dto.FilmDto;
import org.javamaster.dubbo.transaction3.api.FilmActorDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

/**
 * @author yudong
 * @date 2020/6/29
 */
@Slf4j
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Reference(version = "1.0.0", check = false)
    private FilmDubboService filmDubboService;
    @Reference(version = "1.0.0", check = false, timeout = 6000)
    private FilmActorDubboService filmActorDubboService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    // @Compensable(propagation = Propagation.SUPPORTS, confirmMethod = "confirmInsertActor", cancelMethod = "cancelInsertActor")
    public void tryInsertActor(ActorReqVo actorReqVo) {
        log.info("try stage:{}", actorReqVo);
        doBusiness(actorReqVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmInsertActor(ActorReqVo actorReqVo) {
        log.info("confirm stage:{}", actorReqVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelInsertActor(ActorReqVo actorReqVo) {
        log.info("cancel stage:{}", actorReqVo);
        jdbcTemplate.update("delete from actor where first_name=? and last_name=?", actorReqVo.getFirstName(), actorReqVo.getLastName());
    }

    @Override
    // @DtxTransactional(dtxName = "transaction1")
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-transaction1")
    public void dtxInsertActor(ActorReqVo actorReqVo) {
        doBusiness(actorReqVo);
    }

    @Override
    public void saveActorTimeOut(ActorReqVo actorReqVo) {
        filmActorDubboService.timeOutInsertFilmActors(1, Arrays.asList(1, 2));
    }

    private void doBusiness(ActorReqVo actorReqVo) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con
                    .prepareStatement("insert into actor(first_name, last_name) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, actorReqVo.getFirstName());
            preparedStatement.setString(2, actorReqVo.getLastName());
            return preparedStatement;
        }, keyHolder);
        int actorId = Objects.requireNonNull(keyHolder.getKey()).intValue();

        List<FilmDto> filmDtos = new ArrayList<>();
        FilmDto filmDto = new FilmDto();
        filmDto.setTitle("Java Master");
        filmDto.setLanguageId(5);
        filmDtos.add(filmDto);
        List<Integer> filmIds = filmDubboService.dtxInsertFilms(filmDtos);

        List<Integer> ids = filmActorDubboService.dtxInsertFilmActors(actorId, filmIds);
    }
}
