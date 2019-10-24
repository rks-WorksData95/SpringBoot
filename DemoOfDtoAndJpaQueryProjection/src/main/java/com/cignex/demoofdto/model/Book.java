package com.cignex.demoofdto.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private Date publishingDate;
	private Double price;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			joinColumns = {@JoinColumn(name = "fk_books")},
			inverseJoinColumns = {@JoinColumn(name = "fk_author")})
	private Set<Author> authors;
	
}
