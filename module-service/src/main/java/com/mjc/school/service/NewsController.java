package com.mjc.school.service;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.impl.model.NewsModel;
import com.mjc.school.repository.mapper.NewsMapperImpl;
import com.mjc.school.repository.impl.dao.RepositoryDataSourceNews;
import com.mjc.school.repository.impl.model.AuthorModel;
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

    private RepositoryDataSourceNews repository = new RepositoryDataSourceNews();
    private NewsMapperImpl newsMapper = new NewsMapperImpl();

    /**
     * <p>Put NewsDTO type without id, createDate, lastUpdateDate;</p>
     * <p>Saves NewsDTO to REPOSITORY</p>
     *
     * @param newsDTO
     * @return NewsDTO from REPOSITORY
     */
    public NewsDTO createNews(NewsDTO newsDTO) {
        List<NewsModel> newsModels = new ArrayList<>(repository.readAllNews());

        long newId = getNewId();
        newsDTO.setId(newId);
        newsDTO.setCreateDate(nowIso8601());
        newsDTO.setLastUpdateDate(nowIso8601());

        if (validateNews(newsDTO.getTitle(), newsDTO.getContent(), newsDTO.getAuthorId())) {
            NewsModel currentNewNewsModel = newsMapper.dtoToSource(newsDTO);
            newsModels.add(currentNewNewsModel);
            repository.saveAllNews(newsModels);
            return newsMapper.sourceToDTO(repository.readByIdNews(newId));
        }
        throw new RuntimeException("Something gone wrong");
    }

    public NewsDTO getNews(long id) {
        //get newsDTO id and get it from repository, then map entity to DTO and return.
        return newsMapper.sourceToDTO(repository.readByIdNews(id));
    }

    public NewsDTO updateNews(NewsDTO newsDTO) {
        //if updating news is exists
        repository.readByIdNews(newsDTO.getId());

        if (validateNews(newsDTO.getTitle(), newsDTO.getContent(), newsDTO.getAuthorId())) {
            newsDTO.setLastUpdateDate(nowIso8601());
            NewsModel entityNewsModel = newsMapper.dtoToSource(newsDTO);
            return newsMapper.sourceToDTO(repository.saveExistNews(entityNewsModel));
        }
        throw new RuntimeException("Something gone wrong");
    }

    public boolean deleteNews(NewsDTO newsDTO) {
        List<NewsModel> allNews = new ArrayList<>(repository.readAllNews());

        for (int i = 0; i < allNews.size(); i++) {
            if (allNews.get(i).getId() == newsDTO.getId()) {
                allNews.remove(i);
                repository.saveAllNews(allNews);
                return true;
            }
        }
        return false;
    }

    public List<NewsDTO> getAllNews() {
        List<NewsModel> allEntityNews = repository.readAllNews();
        List<NewsDTO> allNewsDTO = new ArrayList<>();
        for (NewsModel allEntityNew : allEntityNews) {
            allNewsDTO.add(newsMapper.sourceToDTO(allEntityNew));
        }
        return allNewsDTO;
    }

    public void setDefaultData(){
        repository.setDefaultNewsAndAuthors();
    }

    private long getNewId() {
        List<NewsModel> newsModels = new ArrayList<>(repository.readAllNews());
        return newsModels.size() + 1;
    }

    private LocalDateTime nowIso8601() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return LocalDateTime.parse(nowAsISO, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'"));
    }

    private boolean validateNews(String title, String content, long authorId) {
        List<NewsModel> newsModels = new ArrayList<>(repository.readAllNews());
        List<AuthorModel> authorModels = new ArrayList<>(repository.getAllAuthors());

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
        for (AuthorModel authorModel : authorModels) {

            if (authorModel.getId() == authorId) {
                isContainsAuthorId = true;
                break;
            }
        }
        for (NewsModel currentNewsModel : newsModels) {
            if (title.equals(currentNewsModel.getTitle()) && content.equals(currentNewsModel.getContent())) {
                throw new NewsAlreadyExistsRuntimeException("Current news is already exists");
            }
        }
        if (!isContainsAuthorId) {
            throw new AuthorNotFoundRuntimeException("Author with " + authorId + " id not found");
        }

        return true;
    }
}