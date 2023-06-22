package com.mjc.school.service;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.mapper.NewsMapperImpl;
import com.mjc.school.repository.persistance.dao.NewsRepository;
import com.mjc.school.repository.persistance.entity.Author;
import com.mjc.school.repository.persistance.entity.News;
import com.mjc.school.service.exceptions.AuthorNotFoundRuntimeException;
import com.mjc.school.service.exceptions.LengthRuntimeException;
import com.mjc.school.service.exceptions.NewsAlreadyExistsRuntimeException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class NewsController {

    private final NewsRepository REPOSITORY = new NewsRepository();
    private final NewsMapperImpl NEWS_MAPPER = new NewsMapperImpl();

    /**
     * <p>Put NewsDTO type without id, createDate, lastUpdateDate;</p>
     * <p>Saves NewsDTO to REPOSITORY</p>
     *
     * @param newsDTO
     * @return NewsDTO from REPOSITORY
     */
    public NewsDTO createNews(NewsDTO newsDTO) {
        List<News> news = new ArrayList<>(REPOSITORY.getAllNews());

        long newId = getNewId();
        newsDTO.setId(newId);
        newsDTO.setCreateDate(nowIso8601());
        newsDTO.setLastUpdateDate(nowIso8601());

        if (validateNews(newsDTO.getTitle(), newsDTO.getContent(), newsDTO.getAuthorId())) {
            News currentNewNews = NEWS_MAPPER.dtoToSource(newsDTO);
            news.add(currentNewNews);
            REPOSITORY.saveAllNews(news);
            return NEWS_MAPPER.sourceToDTO(REPOSITORY.getNewsById(newId));
        }
        throw new RuntimeException("Something gone wrong");
    }

    public NewsDTO getNews(NewsDTO newsDTO) {
        //get newsDTO id and get it from repository, then map entity to DTO and return.
        return NEWS_MAPPER.sourceToDTO(REPOSITORY.getNewsById(newsDTO.getId()));
    }

    public NewsDTO updateNews(NewsDTO newsDTO) {
        //if updating news is exists
        REPOSITORY.getNewsById(newsDTO.getId());

        if (validateNews(newsDTO.getTitle(), newsDTO.getContent(), newsDTO.getAuthorId())) {
            newsDTO.setLastUpdateDate(nowIso8601());
            News entityNews = NEWS_MAPPER.dtoToSource(newsDTO);
            return NEWS_MAPPER.sourceToDTO(REPOSITORY.saveExistNews(entityNews));
        }
        throw new RuntimeException("Something gone wrong");
    }

    public boolean deleteNews(NewsDTO newsDTO) {
        List<News> allNews = new ArrayList<>(REPOSITORY.getAllNews());

        for (int i = 0; i < allNews.size(); i++) {
            if (allNews.get(i).getId() == newsDTO.getId()) {
                allNews.remove(i);
                REPOSITORY.saveAllNews(allNews);
                return true;
            }
        }
        return false;
    }

    public List<NewsDTO> getAllNews() {
        List<News> allEntityNews = REPOSITORY.getAllNews();
        List<NewsDTO> allNewsDTO = new ArrayList<>();
        for (News allEntityNew : allEntityNews) {
            allNewsDTO.add(NEWS_MAPPER.sourceToDTO(allEntityNew));
        }
        return allNewsDTO;
    }

    private long getNewId() {
        List<News> news = new ArrayList<>(REPOSITORY.getAllNews());
        return news.size() + 1;
    }

    private LocalDateTime nowIso8601() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return LocalDateTime.parse(nowAsISO, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'"));
    }

    private boolean validateNews(String title, String content, long authorId) {
        List<News> news = new ArrayList<>(REPOSITORY.getAllNews());
        List<Author> authors = new ArrayList<>(REPOSITORY.getAllAuthors());

        if (title.length() <= 4) {
            throw new LengthRuntimeException("Title length is too small! [<5]");
        }
        if (title.length() >= 31) {
            throw new LengthRuntimeException("Title length is too big! [>30]");
        }
        if (content.length() <= 4) {
            throw new LengthRuntimeException("Content length is too small! [<5]");
        }
        if (content.length() >= 256) {
            throw new LengthRuntimeException("Content length is too big! [>255]");
        }
        boolean isContainsAuthorId = false;
        for (Author author : authors) {

            if (author.getId() == authorId) {
                isContainsAuthorId = true;
                break;
            }
        }
        for (News currentNews : news) {
            if (title.equals(currentNews.getTitle()) && content.equals(currentNews.getContent())) {
                throw new NewsAlreadyExistsRuntimeException("Current news is already exists");
            }
        }
        if (!isContainsAuthorId) {
            throw new AuthorNotFoundRuntimeException("Author with " + authorId + " id not found");
        }

        return true;
    }
}