package com.mjc.school.repository.impl.dao;

import com.mjc.school.repository.impl.model.AuthorModel;
import com.mjc.school.repository.impl.model.NewsModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewsModelRepositoryTest {

    DataSource newsRepository = new DataSource();


    @Test
    void getAllAuthors() {
        List<AuthorModel> authorModels = new ArrayList<>();

        AuthorModel authorModel1 = new AuthorModel();
        authorModel1.setId(1);
        authorModel1.setName("Nazir");
        authorModels.add(authorModel1);
        AuthorModel authorModel2 = new AuthorModel();
        authorModel2.setId(2);
        authorModel2.setName("Alex");
        authorModels.add(authorModel2);
        newsRepository.saveAllAuthors(authorModels);

        ArrayList<AuthorModel> arr = new ArrayList<>(newsRepository.getAllAuthors());

        assertEquals(1, arr.get(0).getId());
        assertEquals("Nazir", arr.get(0).getName());

        assertEquals(2, arr.get(1).getId());
        assertEquals("Alex", arr.get(1).getName());
    }

    @Test
    void getAllNews() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<NewsModel> newArr = new ArrayList<>();
        NewsModel newsModel1 = new NewsModel();
        newsModel1.setId(1);
        newsModel1.setTitle("Tekken 8");
        newsModel1.setContent("Tekken 8 coming soon");
        newsModel1.setCreateDate(currentTime);
        newsModel1.setLastUpdateDate(currentTime);
        newsModel1.setAuthorId(2);
        newArr.add(newsModel1);

        NewsModel newsModel2 = new NewsModel();
        newsModel2.setId(2);
        newsModel2.setTitle("Java 2023");
        newsModel2.setContent("Java new version released");
        newsModel2.setCreateDate(currentTime);
        newsModel2.setLastUpdateDate(currentTime);
        newsModel2.setAuthorId(1);
        newArr.add(newsModel2);
        newsRepository.saveAllNews(newArr);
        ArrayList<NewsModel> arr = new ArrayList<>(newsRepository.readAllNews());

        assertEquals(1, arr.get(0).getId());
        assertEquals("Tekken 8", arr.get(0).getTitle());
        assertEquals("Tekken 8 coming soon", arr.get(0).getContent());
        assertEquals(currentTime, arr.get(0).getCreateDate());
        assertEquals(currentTime, arr.get(0).getLastUpdateDate());
        assertEquals(2, arr.get(0).getAuthorId());

        assertEquals(2, arr.get(1).getId());
        assertEquals("Java 2023", arr.get(1).getTitle());
        assertEquals("Java new version released", arr.get(1).getContent());
        assertEquals(currentTime, arr.get(1).getCreateDate());
        assertEquals(currentTime, arr.get(1).getLastUpdateDate());
        assertEquals(1, arr.get(1).getAuthorId());
    }

}