package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.AuthorDTO;
import com.mjc.school.repository.persistance.entity.Author;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {
    Author dtoToSource(AuthorDTO author);
    AuthorDTO sourceToDTO(Author authorDTO);
}