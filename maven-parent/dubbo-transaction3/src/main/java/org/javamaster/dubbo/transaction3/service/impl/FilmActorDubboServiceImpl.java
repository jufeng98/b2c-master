package org.javamaster.dubbo.transaction3.service.impl;

import cn.com.bluemoon.mall.dtc.transaction.DtxTransactional;
import com.alibaba.dubbo.config.annotation.Service;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.dubbo.transaction3.api.FilmActorDubboService;
// import org.mengyun.tcctransaction.api.Compensable;
// import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2020/6/29
 */
@Slf4j
@Service(version = "1.0.0", timeout = 2000)
public class FilmActorDubboServiceImpl implements FilmActorDubboService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    // @Compensable(confirmMethod = "confirmInsertFilmActors", cancelMethod = "cancelInsertFilmActors", transactionContextEditor = DubboTransactionContextEditor.class)
    public List<Integer> tryInsertFilmActors(int actorId, List<Integer> filmIds) {
        log.info("try stage:{},{}", actorId, filmIds);
        return doBusiness(actorId, filmIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmInsertFilmActors(int actorId, List<Integer> filmIds) {
        log.info("confirm stage:{},{}", actorId, filmIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelInsertFilmActors(int actorId, List<Integer> filmIds) {
        log.info("cancel stage:{},{}", actorId, filmIds);
        jdbcTemplate.update("delete from film_actor where actor_id=?", actorId);
    }

    @Override
    // @DtxTransactional(dtxName = "transaction3")
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-transaction3")
    public List<Integer> dtxInsertFilmActors(int actorId, List<Integer> filmIds) {
        return doBusiness(actorId, filmIds);
    }

    @Override
    public List<Integer> timeOutInsertFilmActors(int i, List<Integer> asList) {
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(1, 2);
    }

    private List<Integer> doBusiness(int actorId, List<Integer> filmIds) {
        return filmIds.stream().map(filmId -> {
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con
                        .prepareStatement("insert into film_actor(actor_id, film_id) values (?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, actorId);
                preparedStatement.setInt(2, filmId);
                return preparedStatement;
            });
            if (true) {
                throw new IllegalArgumentException("wrong");
            }
            return -1;
        }).collect(Collectors.toList());
    }
}
