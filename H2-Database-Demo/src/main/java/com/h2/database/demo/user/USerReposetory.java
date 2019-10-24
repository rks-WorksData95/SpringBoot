package com.h2.database.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface USerReposetory extends JpaRepository<User, Integer>{

}
