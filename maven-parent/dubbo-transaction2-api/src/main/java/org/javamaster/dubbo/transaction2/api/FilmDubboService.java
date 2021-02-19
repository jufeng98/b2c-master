package org.javamaster.dubbo.transaction2.api;

import org.javamaster.dubbo.transaction2.dto.FilmDto;
import org.mengyun.tcctransaction.api.Compensable;

import java.util.List;

/**
 * @author yudong
 * @date 2020/6/29
 */
public interface FilmDubboService {
    @Compensable
    List<Integer> tryInsertFilms(List<FilmDto> filmDtos);

    void confirmInsertFilms(List<FilmDto> filmDtos);

    void cancelInsertFilms(List<FilmDto> filmDtos);

    List<Integer> dtxInsertFilms(List<FilmDto> filmDtos);
}
