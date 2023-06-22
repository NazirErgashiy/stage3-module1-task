package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.AuthorDTO;
import com.mjc.school.repository.impl.model.AuthorModel;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-22T18:15:24+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorModel dtoToSource(AuthorDTO author) {
        if ( author == null ) {
            return null;
        }

        AuthorModel authorModel = new AuthorModel();

        authorModel.setId( author.getId() );
        authorModel.setName( author.getName() );

        return authorModel;
    }

    @Override
    public AuthorDTO sourceToDTO(AuthorModel authorModelDTO) {
        if ( authorModelDTO == null ) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setId( authorModelDTO.getId() );
        authorDTO.setName( authorModelDTO.getName() );

        return authorDTO;
    }
}
