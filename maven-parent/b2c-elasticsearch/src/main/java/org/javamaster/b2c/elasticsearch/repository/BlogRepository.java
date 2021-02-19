package org.javamaster.b2c.elasticsearch.repository;

import org.javamaster.b2c.elasticsearch.model.Blog;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * @author yudong
 * @date 2020/5/11
 */
public interface BlogRepository extends ElasticsearchCrudRepository<Blog, String> {

    List<Blog> findByTitleLike(String keyword);

    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    List<Blog> findByTitleCustom(String keyword);

}
