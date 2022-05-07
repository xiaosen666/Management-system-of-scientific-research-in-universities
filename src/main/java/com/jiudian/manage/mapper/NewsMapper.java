package com.jiudian.manage.mapper;

import com.jiudian.manage.model.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper {
    List<News> Get_news_list();

    void put_news(News news);
}
