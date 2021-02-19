package com.javamaster.b2c.cloud.test.redis.model;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Article {
    public static final int ONE_WEEK_IN_SECONDS = 86400,
            VOTE_SCORE = 432,
            ARTICLES_PEP_PAGE = 25;

    @BeforeClass
    public static void init() {
        NoSpringRedisTest.init();
    }

    public void articleVote(String voteUser, String articleId) {
        long currentTimeStamp = new Date().getTime();
        if (currentTimeStamp - jedis.zscore("times:", articleId) > ONE_WEEK_IN_SECONDS) {
            return;
        }
        if (jedis.sadd("votes:" + articleId, voteUser) != 0) {
            jedis.zincrby("scores:", VOTE_SCORE, articleId);
            jedis.hincrBy(articleId, "votes", 1);
        }
    }

    public Long postArticle(StringRedisTemplate template, String user,
                            String title, String link) {
        Long articleId = template.opsForValue().increment("article:", 1);
        String voted = "voted:" + articleId;
        template.opsForSet().add(voted, user);
        template.expire(voted, ONE_WEEK_IN_SECONDS, TimeUnit.SECONDS);
        String article = "article:" + articleId;
        long now = new Date().getTime();
        Map<String, String> articleMap = new HashMap<String, String>();
        articleMap.put("title", title);
        articleMap.put("link", link);
        articleMap.put("poster", user);
        articleMap.put("time", String.valueOf(now));
        articleMap.put("votes", String.valueOf(1));
        template.opsForHash().putAll(article, articleMap);
        template.opsForZSet().add("score:", article, VOTE_SCORE);
        template.opsForZSet().add("time:", article, now);
        return articleId;
    }

    public Map<String, Map<Object, Object>> getArticles(
            StringRedisTemplate template, int page, String order) {
        int start = (page - 1) * ARTICLES_PEP_PAGE;
        int end = start + ARTICLES_PEP_PAGE - 1;
        Set<String> idsSet = template.opsForZSet().range(order, start, end);
        Map<String, Map<Object, Object>> articles = new HashMap<String, Map<Object, Object>>();
        for (String id : idsSet) {
            Map<Object, Object> article = template.opsForHash().entries(id);
            articles.put(id, article);
        }
        return articles;
    }

}
