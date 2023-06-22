package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.impl.model.NewsModel;
import org.mapstruct.Mapper;

@Mapper
public interface NewsMapper {
    NewsModel dtoToSource(NewsDTO news);
    NewsDTO sourceToDTO(NewsModel newsModelDTO);
}
