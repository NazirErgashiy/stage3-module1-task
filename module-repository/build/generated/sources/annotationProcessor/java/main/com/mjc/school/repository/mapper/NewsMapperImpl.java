package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.impl.model.NewsModel;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-22T18:15:24+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsModel dtoToSource(NewsDTO news) {
        if ( news == null ) {
            return null;
        }

        NewsModel newsModel = new NewsModel();

        newsModel.setId( news.getId() );
        newsModel.setTitle( news.getTitle() );
        newsModel.setContent( news.getContent() );
        newsModel.setCreateDate( news.getCreateDate() );
        newsModel.setLastUpdateDate( news.getLastUpdateDate() );
        newsModel.setAuthorId( news.getAuthorId() );

        return newsModel;
    }

    @Override
    public NewsDTO sourceToDTO(NewsModel newsModelDTO) {
        if ( newsModelDTO == null ) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setId( newsModelDTO.getId() );
        newsDTO.setTitle( newsModelDTO.getTitle() );
        newsDTO.setContent( newsModelDTO.getContent() );
        newsDTO.setCreateDate( newsModelDTO.getCreateDate() );
        newsDTO.setLastUpdateDate( newsModelDTO.getLastUpdateDate() );
        newsDTO.setAuthorId( newsModelDTO.getAuthorId() );

        return newsDTO;
    }
}
