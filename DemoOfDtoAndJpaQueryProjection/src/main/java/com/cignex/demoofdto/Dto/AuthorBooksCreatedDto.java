package com.cignex.demoofdto.Dto;

import java.util.Date;
import java.util.Set;

import com.cignex.demoofdto.model.Author;
import com.cignex.demoofdto.model.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorBooksCreatedDto {
	
	private String bookTitle;
	private Date bookPublishingDate;
	private Double bookPrice;
	private Set<Author> authorsDetails;
	
	public Book toModel(AuthorBooksCreatedDto authorBooksCreatedDto) {
		Book book=new Book();
		
		book.setTitle(authorBooksCreatedDto.getBookTitle());
		book.setPublishingDate(authorBooksCreatedDto.getBookPublishingDate());
		book.setPrice(authorBooksCreatedDto.getBookPrice());
		book.setAuthors(authorBooksCreatedDto.getAuthorsDetails());
		
		return book;
	}

}
