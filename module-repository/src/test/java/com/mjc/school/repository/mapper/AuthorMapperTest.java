package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.AuthorDTO;
import com.mjc.school.repository.persistance.entity.Author;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorMapperTest {
    AuthorMapperImpl authorMapper=new AuthorMapperImpl();

    @Test
    void dtoToSource() {
        AuthorDTO authorDTO=new AuthorDTO();
        authorDTO.setId(1);
        authorDTO.setName("Abobus");

        Author author=authorMapper.dtoToSource(authorDTO);

        assertEquals(authorDTO.getId(),author.getId());
        assertEquals(authorDTO.getName(),author.getName());
    }

    @Test
    void sourceToDTO() {
        Author author=new Author();
        author.setId(1);
        author.setName("Abobus");

        AuthorDTO authorDTO=authorMapper.sourceToDTO(author);

        assertEquals(author.getId(),authorDTO.getId());
        assertEquals(author.getName(),authorDTO.getName());
    }
}