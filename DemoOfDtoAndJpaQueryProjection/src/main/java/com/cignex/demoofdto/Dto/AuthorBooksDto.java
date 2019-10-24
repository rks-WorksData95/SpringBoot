package com.cignex.demoofdto.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorBooksDto {

	private Long bookId;
	private String bookTitle;
	private Double bookPrice;
	private String authorNames;
	
}
