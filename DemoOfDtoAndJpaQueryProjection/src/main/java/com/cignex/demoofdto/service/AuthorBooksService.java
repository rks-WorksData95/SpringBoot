package com.cignex.demoofdto.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cignex.demoofdto.Dto.AuthorBooksCreatedDto;
import com.cignex.demoofdto.Dto.AuthorBooksDto;
import com.cignex.demoofdto.Dto.AuthorDto;
import com.cignex.demoofdto.model.Author;
import com.cignex.demoofdto.model.Book;
import com.cignex.demoofdto.reposetory.AuthorReposetory;
import com.cignex.demoofdto.reposetory.BookReposetory;

@Service
public class AuthorBooksService {
	
	@Autowired
	private AuthorReposetory authorReposetory;
	
	@Autowired
	private BookReposetory bookReposetory;
	
	public URI createAuthor(AuthorDto authorDto) {
		System.out.println("=======> : "+authorDto);
		Author authorDB = authorReposetory.save(authorDto.toModel(authorDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(authorDB.getId()).toUri();
		return uri;
	}
	
	public URI createAuthorWithPublisedhBooks(AuthorBooksCreatedDto authorBooksCreatedDto) {
		System.out.println("=======> : "+authorBooksCreatedDto);
		Book bookDB = bookReposetory.saveAndFlush(authorBooksCreatedDto.toModel(authorBooksCreatedDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bookDB.getId()).toUri();
		return uri;
	}
	
	// optional method
	public List<AuthorDto> getAllAuthorDetail(){
		List<Author> authors=authorReposetory.findAll();
		List<AuthorDto> listAuthorDtos=new ArrayList<>();
		for(Author author: authors) {
			AuthorDto authorDto=new AuthorDto();
			authorDto.setAuthorVersion(author.getVersion());
			authorDto.setAuthorFirstName(author.getFirstName());
			authorDto.setAuthorLastName(author.getLastName());
			listAuthorDtos.add(authorDto);
		}
		return listAuthorDtos;
	}
	
	public List<AuthorDto> getAllAuthorDetails(){
		List<Author> authors=authorReposetory.findAll();
		return authors.stream().map(author->new AuthorDto().toDto(author)).collect(Collectors.toList());
	}
	
	public AuthorDto getAuthorById(Long authorId){
		return new AuthorDto().toDto(authorReposetory.findById(authorId).get());
	}
	
	public List<AuthorBooksDto> getAllSearchBooks(String bookTitle) {
		return bookReposetory.getAllSerachBooks(bookTitle);
	}
	
}
