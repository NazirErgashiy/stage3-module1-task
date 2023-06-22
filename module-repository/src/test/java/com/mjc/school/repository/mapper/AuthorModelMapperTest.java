package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.AuthorDTO;
import com.mjc.school.repository.impl.model.AuthorModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorModelMapperTest {
    AuthorMapperImpl authorMapper=new AuthorMapperImpl();

    @Test
    void dtoToSource() {
        AuthorDTO authorDTO=new AuthorDTO();
        authorDTO.setId(1);
        authorDTO.setName("Abobus");

        AuthorModel authorModel =authorMapper.dtoToSource(authorDTO);

        assertEquals(authorDTO.getId(), authorModel.getId());
        assertEquals(authorDTO.getName(), authorModel.getName());
    }

    @Test
    void sourceToDTO() {
        AuthorModel authorModel =new AuthorModel();
        authorModel.setId(1);
        authorModel.setName("Abobus");

        AuthorDTO authorDTO=authorMapper.sourceToDTO(authorModel);

        assertEquals(authorModel.getId(),authorDTO.getId());
        assertEquals(authorModel.getName(),authorDTO.getName());
    }
}