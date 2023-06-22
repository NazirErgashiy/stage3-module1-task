package com.mjc.school.repository.impl.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjc.school.repository.exceptions.NewsNotFoundRuntimeException;
import com.mjc.school.repository.impl.model.AuthorModel;
import com.mjc.school.repository.impl.model.NewsModel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class RepositoryDataSourceNews {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();//Jackson JSON

    static {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        OBJECT_MAPPER.setDateFormat(df);
        OBJECT_MAPPER.findAndRegisterModules();
    }

    public RepositoryDataSourceNews() {
    }

    public List<AuthorModel> getAllAuthors() {
        File file = getAuthorFile();
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            String str = new String(data, StandardCharsets.UTF_8);
            return Arrays.asList(OBJECT_MAPPER.readValue(str, AuthorModel[].class));
        } catch (NullPointerException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public AuthorModel getAuthorById(long id) {
        List<AuthorModel> allAuthorModels = getAllAuthors();

        for (int i = 0; i < allAuthorModels.size(); i++) {
            if (allAuthorModels.get(i).getId() == id) return allAuthorModels.get(i);
        }
        throw new NewsNotFoundRuntimeException("Author with id: " + id + " not found");
    }

    public void saveAllAuthors(List<AuthorModel> arr) {
        try (FileWriter fileWriter = new FileWriter(getAuthorFile());
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String json = OBJECT_MAPPER.writeValueAsString(arr);
            bufferedWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NewsModel> readAllNews() {
        File file = getNewsFile();

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            String str = new String(data, StandardCharsets.UTF_8);
            return Arrays.asList(OBJECT_MAPPER.readValue(str, NewsModel[].class));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public NewsModel readByIdNews(Long id) {
        List<NewsModel> allNews = readAllNews();

        for (int i = 0; i < allNews.size(); i++) {
            if (allNews.get(i).getId() == id) return allNews.get(i);
        }
        throw new NewsNotFoundRuntimeException("News with id: " + id + " not found");
    }

    public NewsModel saveExistNews(NewsModel newsModelToSave) {
        List<NewsModel> allNews = readAllNews();
        for (int i = 0; 0 < allNews.size(); i++) {
            if (allNews.get(i).getId() == newsModelToSave.getId()) {
                allNews.set(i, newsModelToSave);
                break;
            }
        }
        saveAllNews(allNews);
        return readByIdNews(newsModelToSave.getId());
    }

    public void saveAllNews(List<NewsModel> arr) {
        try (FileWriter fileWriter = new FileWriter(getNewsFile());
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String json = OBJECT_MAPPER.writeValueAsString(arr);
            bufferedWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public NewsModel createNews(NewsModel newsModel){
        //Implement
        return newsModel;
    }

    public NewsModel updateNews(NewsModel newsModel) {
        //if updating news is exists
        readByIdNews(newsModel.getId());
        //news.setLastUpdateDate(()); //UpdateTime
        return new NewsModel();
    }

    public Boolean deleteNews(Long id) {
        List<NewsModel> allNews = new ArrayList<>(readAllNews());

        for (int i = 0; i < allNews.size(); i++) {
            if (allNews.get(i).getId() == id) {
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
            saveAllNews(Arrays.asList(OBJECT_MAPPER.readValue(str, NewsModel[].class)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File fileAuthor = new File(correctPath + "/module-repository/src/main/resources/default/author.txt");
        try (FileInputStream fis = new FileInputStream(fileAuthor)) {
            byte[] data = new byte[(int) fileAuthor.length()];
            fis.read(data);
            String str = new String(data, StandardCharsets.UTF_8);
            saveAllAuthors(Arrays.asList(OBJECT_MAPPER.readValue(str, AuthorModel[].class)));
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
