package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.impl.model.News;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-22T17:45:04+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public News dtoToSource(NewsDTO news) {
        if ( news == null ) {
            return null;
        }

        News news1 = new News();

        news1.setId( news.getId() );
        news1.setTitle( news.getTitle() );
        news1.setContent( news.getContent() );
        news1.setCreateDate( news.getCreateDate() );
        news1.setLastUpdateDate( news.getLastUpdateDate() );
        news1.setAuthorId( news.getAuthorId() );

        return news1;
    }

    @Override
    public NewsDTO sourceToDTO(News newsDTO) {
        if ( newsDTO == null ) {
            return null;
        }

        NewsDTO newsDTO1 = new NewsDTO();

        newsDTO1.setId( newsDTO.getId() );
        newsDTO1.setTitle( newsDTO.getTitle() );
        newsDTO1.setContent( newsDTO.getContent() );
        newsDTO1.setCreateDate( newsDTO.getCreateDate() );
        newsDTO1.setLastUpdateDate( newsDTO.getLastUpdateDate() );
        newsDTO1.setAuthorId( newsDTO.getAuthorId() );

        return newsDTO1;
    }
}
