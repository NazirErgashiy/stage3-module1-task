package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.impl.model.NewsModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NewsModelMapperImplTest {

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

        NewsModel newsModel = newsMapper.dtoToSource(newsDTO);

        assertEquals(newsDTO.getId(), newsModel.getId());
        assertEquals(newsDTO.getTitle(), newsModel.getTitle());
        assertEquals(newsDTO.getContent(), newsModel.getContent());
        assertEquals(newsDTO.getCreateDate(), newsModel.getCreateDate());
        assertEquals(newsDTO.getLastUpdateDate(), newsModel.getLastUpdateDate());
        assertEquals(newsDTO.getAuthorId(), newsModel.getAuthorId());
    }

    @Test
    void sourceToDTO() {
        LocalDateTime now = LocalDateTime.now();
        NewsModel newsModel = new NewsModel();
        newsModel.setId(1);
        newsModel.setTitle("Amogus");
        newsModel.setContent("Amogus is coming");
        newsModel.setCreateDate(now);
        newsModel.setLastUpdateDate(now);
        newsModel.setAuthorId(1);

        NewsDTO newsDTO = newsMapper.sourceToDTO(newsModel);

        assertEquals(newsModel.getId(), newsDTO.getId());
        assertEquals(newsModel.getTitle(), newsDTO.getTitle());
        assertEquals(newsModel.getContent(), newsDTO.getContent());
        assertEquals(newsModel.getCreateDate(), newsDTO.getCreateDate());
        assertEquals(newsModel.getLastUpdateDate(), newsDTO.getLastUpdateDate());
        assertEquals(newsModel.getAuthorId(), newsDTO.getAuthorId());
    }
}