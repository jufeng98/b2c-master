package org.javamaster.b2c.elasticsearch.controller;

import org.elasticsearch.action.DocWriteResponse;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import org.javamaster.b2c.elasticsearch.model.Blog;
import org.javamaster.b2c.elasticsearch.repository.BlogRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author yudong
 * @date 2020/5/11
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogRepository blogRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    public BlogController(BlogRepository blogRepository, ElasticsearchTemplate elasticsearchTemplate) {
        this.blogRepository = blogRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @PostMapping("/add")
    public DocWriteResponse.Result add(@RequestBody Blog blog) {
        blogRepository.save(blog);
        return DocWriteResponse.Result.CREATED;
    }

    @GetMapping("/search/title")
    public List<Blog> searchTitle(String keyword) {
        return blogRepository.findByTitleLike(keyword);
    }

    @GetMapping("/search/title/custom")
    public List<Blog> searchTitleCustom(String keyword) {
        return blogRepository.findByTitleCustom(keyword);
    }

    @GetMapping("/search/title")
    public List<Blog> searchTitleTemplate(String keyword) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(keyword))
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Blog.class);
    }

    @GetMapping("/get")
    public List<Blog> getAll() {
        Iterable<Blog> iterable = blogRepository.findAll();
        List<Blog> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    @GetMapping("/get/{id}")
    public Blog getById(@PathVariable String id) {
        Optional<Blog> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            return blogModelOptional.get();
        }
        throw new RuntimeException(id + " not exists");
    }

    @PostMapping("/update")
    public DocWriteResponse.Result updateById(@RequestBody Blog blog) {
        String id = blog.getId();
        blogRepository.save(blog);
        return DocWriteResponse.Result.UPDATED;
    }

    @DeleteMapping("/delete/{id}")
    public DocWriteResponse.Result deleteById(@PathVariable String id) {
        blogRepository.deleteById(id);
        return DocWriteResponse.Result.DELETED;
    }

    @DeleteMapping("/delete")
    public DocWriteResponse.Result deleteById() {
        blogRepository.deleteAll();
        return DocWriteResponse.Result.DELETED;
    }

}