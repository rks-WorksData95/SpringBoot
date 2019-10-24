package com.cignex.demoofdto.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cignex.demoofdto.Dto.AuthorBooksCreatedDto;
import com.cignex.demoofdto.Dto.AuthorBooksDto;
import com.cignex.demoofdto.Dto.AuthorDto;
import com.cignex.demoofdto.service.AuthorBooksService;

@RestController
public class AuthorBooksController {
	
	@Autowired
	private AuthorBooksService authorBookService;
	
	@PostMapping(path = "/create-Author")
	public ResponseEntity<Object> createAuthorAndBookDetails(@RequestBody AuthorDto authorDto) {
		URI uri = authorBookService.createAuthor(authorDto);
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(path = "/create-Book")
	public ResponseEntity<Object> createAuthorAndBookDetails(@RequestBody AuthorBooksCreatedDto authorBooksCreatedDto) {
		URI uri = authorBookService.createAuthorWithPublisedhBooks(authorBooksCreatedDto);
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(path = "/get-all-authors")
	public List<AuthorDto> retriveAllAuthors() {
		return authorBookService.getAllAuthorDetails();
	}
	
	@GetMapping(path = "/get-author/{authorId}")
	public AuthorDto retriveAuthorById(@PathVariable Long authorId) {
		return authorBookService.getAuthorById(authorId);
	}
	
	@GetMapping(path = "/search-books/{bookTitle}")
	public List<AuthorBooksDto> searchBooks(@PathVariable String bookTitle) {
		return authorBookService.getAllSearchBooks(bookTitle);
	}

}
