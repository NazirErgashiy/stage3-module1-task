package com.mjc.school.repository.mapper;

import com.mjc.school.repository.dto.AuthorDTO;
import com.mjc.school.repository.impl.model.Author;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-22T17:06:32+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author dtoToSource(AuthorDTO author) {
        if ( author == null ) {
            return null;
        }

        Author author1 = new Author();

        author1.setId( author.getId() );
        author1.setName( author.getName() );

        return author1;
    }

    @Override
    public AuthorDTO sourceToDTO(Author authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        AuthorDTO authorDTO1 = new AuthorDTO();

        authorDTO1.setId( authorDTO.getId() );
        authorDTO1.setName( authorDTO.getName() );

        return authorDTO1;
    }
}
