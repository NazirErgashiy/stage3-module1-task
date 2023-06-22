package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.persistance.entity.News;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NewsMapperImplTest {

    NewsMapperImpl newsMapper = new NewsMapperImpl();

    @Test
    void dtoToSource() {
        LocalDateTime now = LocalDateTime.now();
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(1);
        newsDTO.setTitle("Amogus");
        newsDTO.setContent("Amogus is coming");
        newsDTO.setCreateDate(now);
        newsDTO.setLastUpdateDate(now);
        newsDTO.setAuthorId(1);

        News news = newsMapper.dtoToSource(newsDTO);

        assertEquals(newsDTO.getId(), news.getId());
        assertEquals(newsDTO.getTitle(), news.getTitle());
        assertEquals(newsDTO.getContent(), news.getContent());
        assertEquals(newsDTO.getCreateDate(), news.getCreateDate());
        assertEquals(newsDTO.getLastUpdateDate(), news.getLastUpdateDate());
        assertEquals(newsDTO.getAuthorId(), news.getAuthorId());
    }

    @Test
    void sourceToDTO() {
        LocalDateTime now = LocalDateTime.now();
        News news = new News();
        news.setId(1);
        news.setTitle("Amogus");
        news.setContent("Amogus is coming");
        news.setCreateDate(now);
        news.setLastUpdateDate(now);
        news.setAuthorId(1);

        NewsDTO newsDTO = newsMapper.sourceToDTO(news);

        assertEquals(news.getId(), newsDTO.getId());
        assertEquals(news.getTitle(), newsDTO.getTitle());
        assertEquals(news.getContent(), newsDTO.getContent());
        assertEquals(news.getCreateDate(), newsDTO.getCreateDate());
        assertEquals(news.getLastUpdateDate(), newsDTO.getLastUpdateDate());
        assertEquals(news.getAuthorId(), newsDTO.getAuthorId());
    }
}