package com.cignex.demoofdto.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cignex.demoofdto.model.Author;

@Repository
public interface AuthorReposetory extends JpaRepository<Author, Long>{

}
