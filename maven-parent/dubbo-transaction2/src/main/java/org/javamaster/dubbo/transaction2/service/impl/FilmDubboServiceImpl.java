package org.javamaster.dubbo.transaction2.service.impl;

import cn.com.bluemoon.mall.dtc.transaction.DtxTransactional;
import com.alibaba.dubbo.config.annotation.Service;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.dubbo.transaction2.api.FilmDubboService;
import org.javamaster.dubbo.transaction2.dto.FilmDto;
// import org.mengyun.tcctransaction.api.Compensable;
// import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2020/6/29
 */
@Slf4j
@Service(version = "1.0.0")
public class FilmDubboServiceImpl implements FilmDubboService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    // @Compensable(confirmMethod = "confirmInsertFilms", cancelMethod = "cancelInsertFilms", transactionContextEditor = DubboTransactionContextEditor.class)
    public List<Integer> tryInsertFilms(List<FilmDto> filmDtos) {
        log.info("try stage:{}", filmDtos);
        return doBusiness(filmDtos);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmInsertFilms(List<FilmDto> filmDtos) {
        log.info("confirm stage:{}", filmDtos);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelInsertFilms(List<FilmDto> filmDtos) {
        log.info("cancel stage:{}", filmDtos);
        filmDtos.forEach(filmDto -> {
            jdbcTemplate.update("delete from film where title=?", filmDto.getTitle());
        });
    }

    @Override
    // @DtxTransactional(dtxName = "transaction2")
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-transaction2")
    public List<Integer> dtxInsertFilms(List<FilmDto> filmDtos) {
        return doBusiness(filmDtos);
    }

    private List<Integer> doBusiness(List<FilmDto> filmDtos) {
        return filmDtos.stream().map(filmDto -> {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con
                        .prepareStatement("insert into film(title, language_id) values (?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, filmDto.getTitle());
                preparedStatement.setInt(2, filmDto.getLanguageId());
                return preparedStatement;
            }, keyHolder);
            return Objects.requireNonNull(keyHolder.getKey()).intValue();
        }).collect(Collectors.toList());
    }
}
