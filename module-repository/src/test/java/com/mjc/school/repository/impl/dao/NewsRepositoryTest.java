package com.mjc.school.repository.impl.dao;

import com.mjc.school.repository.impl.model.Author;
import com.mjc.school.repository.impl.model.News;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewsRepositoryTest {

    NewsRepository newsRepository = new NewsRepository();


    @Test
    void getAllAuthors() {
        List<Author> authors = new ArrayList<>();

        Author author1 = new Author();
        author1.setId(1);
        author1.setName("Nazir");
        authors.add(author1);
        Author author2 = new Author();
        author2.setId(2);
        author2.setName("Alex");
        authors.add(author2);
        newsRepository.saveAllAuthors(authors);

        ArrayList<Author> arr = new ArrayList<>(newsRepository.getAllAuthors());

        assertEquals(1, arr.get(0).getId());
        assertEquals("Nazir", arr.get(0).getName());

        assertEquals(2, arr.get(1).getId());
        assertEquals("Alex", arr.get(1).getName());
    }

    @Test
    void getAllNews() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<News> newArr = new ArrayList<>();
        News news1 = new News();
        news1.setId(1);
        news1.setTitle("Tekken 8");
        news1.setContent("Tekken 8 coming soon");
        news1.setCreateDate(currentTime);
        news1.setLastUpdateDate(currentTime);
        news1.setAuthorId(2);
        newArr.add(news1);

        News news2 = new News();
        news2.setId(2);
        news2.setTitle("Java 2023");
        news2.setContent("Java new version released");
        news2.setCreateDate(currentTime);
        news2.setLastUpdateDate(currentTime);
        news2.setAuthorId(1);
        newArr.add(news2);
        newsRepository.saveAllNews(newArr);
        ArrayList<News> arr = new ArrayList<>(newsRepository.getAllNews());

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