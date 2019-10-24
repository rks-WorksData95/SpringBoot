package com.cignex.demoofdto.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cignex.demoofdto.Dto.AuthorBooksDto;
import com.cignex.demoofdto.model.Book;

@Repository
public interface BookReposetory extends JpaRepository<Book, Long>{
	
	@Query("SELECT new com.cignex.demoofdto.Dto.AuthorBooksDto(b.id, b.title, b.price, concat(a.firstName, ' ', a.lastName)) FROM Book b JOIN b.authors a WHERE b.title LIKE %:title%")
	public List<AuthorBooksDto> getAllSerachBooks(@Param("title") String bookTitle);
	
	
}
