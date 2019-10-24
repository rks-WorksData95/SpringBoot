package com.cignex.demoofdto.Dto;

import com.cignex.demoofdto.model.Author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

	private int authorVersion;
	private String authorFirstName;
	private String authorLastName;
	
	public Author toModel(AuthorDto authorDto) {
		Author author=new Author();
		
		author.setVersion(authorDto.getAuthorVersion());
		author.setFirstName(authorDto.getAuthorFirstName());
		author.setLastName(authorDto.getAuthorLastName());
		
		return author;
	}
	
	public AuthorDto toDto(Author author) {
		AuthorDto authorDto=new AuthorDto();
		authorDto.setAuthorVersion(author.getVersion());
		authorDto.setAuthorFirstName(author.getFirstName());
		authorDto.setAuthorLastName(author.getLastName());
		return authorDto;
	}
	
}
