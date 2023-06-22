package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.AuthorDTO;
import com.mjc.school.repository.impl.model.AuthorModel;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {
    AuthorModel dtoToSource(AuthorDTO author);
    AuthorDTO sourceToDTO(AuthorModel authorModelDTO);
}