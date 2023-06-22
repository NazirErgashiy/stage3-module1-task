package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.NewsDTO;
import com.mjc.school.repository.impl.model.News;
import org.mapstruct.Mapper;

@Mapper
public interface NewsMapper {
    News dtoToSource(NewsDTO news);
    NewsDTO sourceToDTO(News newsDTO);
}
