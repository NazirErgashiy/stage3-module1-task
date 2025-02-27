package com.mjc.school.service;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.impl.model.AuthorModel;
import com.mjc.school.repository.mapper.NewsMapperImpl;
import com.mjc.school.repository.impl.dao.RepositoryDataSourceNews;
import com.mjc.school.repository.impl.model.NewsModel;
import com.mjc.school.service.exceptions.AuthorNotFoundRuntimeException;
import com.mjc.school.service.exceptions.LengthRuntimeException;
import com.mjc.school.service.exceptions.NewsAlreadyExistsRuntimeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsModelControllerTest {

    private final NewsController NEWS_CONTROLLER = new NewsController();
    private final RepositoryDataSourceNews REPOSITORY = new RepositoryDataSourceNews();
    private final NewsMapperImpl NEWS_MAPPER = new NewsMapperImpl();

    private void clearRepository() {
        REPOSITORY.saveAllAuthors(new ArrayList<>());
        REPOSITORY.saveAllNews(new ArrayList<>());
    }

    private void setToRepositoryAuthor() {
        ArrayList<AuthorModel> authorModels = new ArrayList<>();
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(1);
        authorModel.setName("Alex");
        authorModels.add(authorModel);
        REPOSITORY.saveAllAuthors(authorModels);
    }

    private NewsDTO addNewsToRepo() {
        ArrayList<NewsModel> allNews = new ArrayList<>();
        NewsModel newsModel1 = new NewsModel();
        newsModel1.setId(1);
        newsModel1.setTitle("Amogus");
        newsModel1.setContent("Amogus is SUS");
        allNews.add(newsModel1);
        REPOSITORY.saveAllNews(allNews);
        return NEWS_MAPPER.sourceToDTO(REPOSITORY.readByIdNews(1L));
    }

    @Test
    void createNews() {
        clearRepository();
        setToRepositoryAuthor();

        NewsDTO originNewsDTO = new NewsDTO();
        originNewsDTO.setId(5);
        originNewsDTO.setTitle("Amogus");
        originNewsDTO.setContent("Amogus is SUS");
        originNewsDTO.setAuthorId(1);
        NewsDTO newsDTO = NEWS_CONTROLLER.createNews(originNewsDTO);

        assertEquals(originNewsDTO.getTitle(), newsDTO.getTitle());
        assertEquals(originNewsDTO.getContent(), newsDTO.getContent());
        assertEquals(1, newsDTO.getAuthorId());
    }

    @Test
    void createSameNewsTwice() {
        clearRepository();
        setToRepositoryAuthor();

        NewsDTO originNewsDTO = new NewsDTO();
        originNewsDTO.setId(5);
        originNewsDTO.setTitle("Amogus");
        originNewsDTO.setContent("Amogus is SUS");
        originNewsDTO.setAuthorId(1);

        assertThrows(NewsAlreadyExistsRuntimeException.class, () -> {
            NewsDTO newsDTO1 = NEWS_CONTROLLER.createNews(originNewsDTO);
            NewsDTO newsDTO2 = NEWS_CONTROLLER.createNews(originNewsDTO);
        });
    }

    @Test
    void createNewsLengthExceptionTitleIsTooSmall() {
        clearRepository();
        setToRepositoryAuthor();

        NewsDTO originNewsDTO = new NewsDTO();
        originNewsDTO.setId(5);
        originNewsDTO.setTitle("Amo");
        originNewsDTO.setContent("Amogus is SUS");
        originNewsDTO.setAuthorId(1);

        assertThrows(LengthRuntimeException.class, () -> {
            NewsDTO newsDTO1 = NEWS_CONTROLLER.createNews(originNewsDTO);
        });
    }

    @Test
    void createNewsLengthExceptionTitleIsTooBig() {
        clearRepository();
        setToRepositoryAuthor();

        NewsDTO originNewsDTO = new NewsDTO();
        originNewsDTO.setId(5);
        String bigTitle = "";
        for (int i = 0; i < 35; i++) {
            bigTitle += "a";
        }

        originNewsDTO.setTitle(bigTitle);
        originNewsDTO.setContent("Amogus is SUS");
        originNewsDTO.setAuthorId(1);

        assertThrows(LengthRuntimeException.class, () -> {
            NewsDTO newsDTO1 = NEWS_CONTROLLER.createNews(originNewsDTO);
        });
    }

    @Test
    void createNewsLengthExceptionContentIsTooSmall() {
        clearRepository();
        setToRepositoryAuthor();

        NewsDTO originNewsDTO = new NewsDTO();
        originNewsDTO.setId(5);
        originNewsDTO.setTitle("Amogus");
        originNewsDTO.setContent("Am");
        originNewsDTO.setAuthorId(1);

        assertThrows(LengthRuntimeException.class, () -> {
            NewsDTO newsDTO1 = NEWS_CONTROLLER.createNews(originNewsDTO);
        });
    }

    @Test
    void createNewsLengthExceptionContentIsTooBig() {
        clearRepository();
        setToRepositoryAuthor();

        NewsDTO originNewsDTO = new NewsDTO();
        originNewsDTO.setId(5);
        String bigContent = "";
        for (int i = 0; i < 260; i++) {
            bigContent += "a";
        }
        originNewsDTO.setTitle("Amogus");
        originNewsDTO.setContent(bigContent);
        originNewsDTO.setAuthorId(1);

        assertThrows(LengthRuntimeException.class, () -> {
            NewsDTO newsDTO1 = NEWS_CONTROLLER.createNews(originNewsDTO);
        });
    }

    @Test
    void createNewsAuthorNotFound() {
        clearRepository();
        NewsDTO originNewsDTO = new NewsDTO();
        originNewsDTO.setId(5);
        originNewsDTO.setTitle("Amogus");
        originNewsDTO.setContent("Amogus is SUS");

        assertThrows(AuthorNotFoundRuntimeException.class, () -> {
            NewsDTO newsDTO1 = NEWS_CONTROLLER.createNews(originNewsDTO);
        });
    }

    //TODO Implement Tests
    @Test
    void getNews() {
        clearRepository();
        setToRepositoryAuthor();
        NewsDTO getNews = addNewsToRepo();
        NewsDTO searchingDTO = new NewsDTO();
        searchingDTO.setId(1);

        assertEquals(getNews, NEWS_CONTROLLER.getNews(searchingDTO.getId()));
    }

    @Test
    void updateNews() {
        clearRepository();
        setToRepositoryAuthor();
        addNewsToRepo();
        NewsDTO changedNews = new NewsDTO();
        changedNews.setId(1);
        changedNews.setTitle("Nazir");
        changedNews.setContent("Ergashiy");
        changedNews.setAuthorId(1);

        assertEquals(changedNews, NEWS_CONTROLLER.updateNews(changedNews));
    }

    @Test
    void deleteNews() {
        clearRepository();
        setToRepositoryAuthor();
        NewsDTO newsDTO = addNewsToRepo();

        assertTrue(NEWS_CONTROLLER.deleteNews(newsDTO));
    }

    @Test
    void getAllNews() {
        clearRepository();
        setToRepositoryAuthor();
        NewsDTO dto = addNewsToRepo();

        List<NewsDTO> allNewsDTO = new ArrayList<>();

        NewsDTO originNewsDTO = new NewsDTO();
        originNewsDTO.setId(0);
        originNewsDTO.setTitle("NazirJhon");
        originNewsDTO.setContent("Nazir is SUS");
        originNewsDTO.setAuthorId(1);
        NewsDTO newsDTO = NEWS_CONTROLLER.createNews(originNewsDTO);

        allNewsDTO.add(dto);
        allNewsDTO.add(newsDTO);

        assertEquals(allNewsDTO, NEWS_CONTROLLER.getAllNews());
    }
}