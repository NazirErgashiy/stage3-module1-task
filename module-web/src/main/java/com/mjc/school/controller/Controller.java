package com.mjc.school.controller;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.service.NewsController;

import java.util.List;

public class Controller {

    private NewsController newsController = new NewsController();

    public NewsDTO actionCreate(String title, String content, long authorId) {
        System.out.println("Action: Create news");
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setTitle(title);
        newsDTO.setContent(content);
        newsDTO.setAuthorId(authorId);
        return newsController.createNews(newsDTO);
    }

    public NewsDTO actionUpdate(String title, String content, long authorId) {
        System.out.println("Action: Update news");
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setTitle(title);
        newsDTO.setContent(content);
        newsDTO.setAuthorId(authorId);
        return newsController.updateNews(newsDTO);
    }

    public NewsDTO actionGetById(long id) {
        System.out.println("Action: Get news by id");
        return newsController.getNews(id);
    }

    public List<NewsDTO> actionGetAll() {
        System.out.println("Action: Get all news");
        return newsController.getAllNews();
    }

    public boolean actionDelete(long id) {
        System.out.println("Action: Delete news");
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(id);
        return newsController.deleteNews(newsDTO);
    }

    public void actionSetDefaultNewsAndAuthors() {
        newsController.setDefaultData();
    }
}
