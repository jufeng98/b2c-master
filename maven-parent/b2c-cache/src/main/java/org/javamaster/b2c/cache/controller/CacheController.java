package org.javamaster.b2c.cache.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.javamaster.b2c.cache.model.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yudong
 * @date 2020/7/30
 */
@Slf4j
@RestController
@CacheConfig(cacheNames = "b2c::cache")
public class CacheController {

    @CachePut(key = "#result.id")
    @RequestMapping(value = "save", method = {RequestMethod.GET})
    public Book save(Book book) {
        // 模拟数据库生成的id
        book.setId(RandomUtils.nextLong());
        return book;
    }

    @Cacheable
    @RequestMapping(value = "get", method = {RequestMethod.GET})
    public Book get(Long id) {
        // 模拟从数据库查出的book
        Book book = new Book();
        book.setId(id);
        book.setAuthor("liang yudong");
        book.setTitle("Java Master");
        log.info(book.toString());
        return book;
    }

    @CacheEvict
    @RequestMapping(value = "del", method = {RequestMethod.GET})
    public int del(Long id) {
        // 删除
        log.info("{}", id);
        return 1;
    }

}
