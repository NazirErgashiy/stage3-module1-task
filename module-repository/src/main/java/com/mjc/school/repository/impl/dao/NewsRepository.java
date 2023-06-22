package com.mjc.school.repository.impl.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.exceptions.NewsNotFoundRuntimeException;
import com.mjc.school.repository.impl.model.Author;
import com.mjc.school.repository.impl.model.News;
import com.mjc.school.repository.mapper.NewsMapperImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class NewsRepository {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();//Jackson JSON
    private final NewsMapperImpl mapper = new NewsMapperImpl();

    static {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        OBJECT_MAPPER.setDateFormat(df);
        OBJECT_MAPPER.findAndRegisterModules();
    }

    public NewsRepository() {
    }

    public List<Author> getAllAuthors() {
        File file = getAuthorFile();
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            String str = new String(data, StandardCharsets.UTF_8);
            return Arrays.asList(OBJECT_MAPPER.readValue(str, Author[].class));
        } catch (NullPointerException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Author getAuthorById(long id) {
        List<Author> allAuthors = getAllAuthors();

        for (int i = 0; i < allAuthors.size(); i++) {
            if (allAuthors.get(i).getId() == id) return allAuthors.get(i);
        }
        throw new NewsNotFoundRuntimeException("Author with id: " + id + " not found");
    }

    public void saveAllAuthors(List<Author> arr) {
        try (FileWriter fileWriter = new FileWriter(getAuthorFile());
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String json = OBJECT_MAPPER.writeValueAsString(arr);
            bufferedWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<News> readAllNews() {
        File file = getNewsFile();

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            String str = new String(data, StandardCharsets.UTF_8);
            return Arrays.asList(OBJECT_MAPPER.readValue(str, News[].class));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public News readByIdNews(long id) {
        List<News> allNews = readAllNews();

        for (int i = 0; i < allNews.size(); i++) {
            if (allNews.get(i).getId() == id) return allNews.get(i);
        }
        throw new NewsNotFoundRuntimeException("News with id: " + id + " not found");
    }

    public News saveExistNews(News newsToSave) {
        List<News> allNews = readAllNews();
        for (int i = 0; 0 < allNews.size(); i++) {
            if (allNews.get(i).getId() == newsToSave.getId()) {
                allNews.set(i, newsToSave);
                break;
            }
        }
        saveAllNews(allNews);
        return readByIdNews(newsToSave.getId());
    }

    public void saveAllNews(List<News> arr) {
        try (FileWriter fileWriter = new FileWriter(getNewsFile());
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String json = OBJECT_MAPPER.writeValueAsString(arr);
            bufferedWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNews(){
        //Implement
    }

    public News toDataSource(NewsDTO newsDTO) {
        return mapper.dtoToSource(newsDTO);
    }

    public News updateNews(News news) {
        //if updating news is exists
        readByIdNews(news.getId());
        //news.setLastUpdateDate(()); //UpdateTime
        return saveExistNews(news);
    }

    public boolean deleteNews(News news) {
        List<News> allNews = new ArrayList<>(readAllNews());

        for (int i = 0; i < allNews.size(); i++) {
            if (allNews.get(i).getId() == news.getId()) {
                allNews.remove(i);
                saveAllNews(allNews);
                return true;
            }
        }
        return false;
    }

    public void setDefaultNewsAndAuthors() {

        String correctPath = System.getProperty("user.dir");

        if (correctPath.contains("\\module-repository") || correctPath.contains("\\module-service")) {
            correctPath = correctPath.replace("\\module-repository", "");
            correctPath = correctPath.replace("\\module-service", "");
        }

        File file = new File(correctPath + "/module-repository/src/main/resources/default/news.txt");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            String str = new String(data, StandardCharsets.UTF_8);
            saveAllNews(Arrays.asList(OBJECT_MAPPER.readValue(str, News[].class)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File fileAuthor = new File(correctPath + "/module-repository/src/main/resources/default/author.txt");
        try (FileInputStream fis = new FileInputStream(fileAuthor)) {
            byte[] data = new byte[(int) fileAuthor.length()];
            fis.read(data);
            String str = new String(data, StandardCharsets.UTF_8);
            saveAllAuthors(Arrays.asList(OBJECT_MAPPER.readValue(str, Author[].class)));
        } catch (NullPointerException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private File getNewsFile() {
        String correctPath = System.getProperty("user.dir");

        if (correctPath.contains("\\module-repository") || correctPath.contains("\\module-service")) {
            correctPath = correctPath.replace("\\module-repository", "");
            correctPath = correctPath.replace("\\module-service", "");
        }

        return new File(correctPath + "/module-repository/src/main/resources/news.txt");
    }

    private File getAuthorFile() {
        String correctPath = System.getProperty("user.dir");

        if (correctPath.contains("\\module-repository") || correctPath.contains("\\module-service")) {
            correctPath = correctPath.replace("\\module-repository", "");
            correctPath = correctPath.replace("\\module-service", "");

        }
        return new File(correctPath + "/module-repository/src/main/resources/author.txt");
    }
}
