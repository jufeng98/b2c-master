package com.javamaster.b2c.cloud.test.boot.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javamaster.b2c.cloud.test.boot.entity.Book;

/**
 * 通过扩展JpaRepository， ReadingListRepository直接继承了18个执行常用持久化操作 的方法。
 *
 * @author yudong
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
