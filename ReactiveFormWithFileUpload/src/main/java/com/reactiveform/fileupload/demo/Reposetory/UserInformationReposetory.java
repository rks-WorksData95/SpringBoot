package com.reactiveform.fileupload.demo.Reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reactiveform.fileupload.demo.model.UserInformation;

@Repository
public interface UserInformationReposetory extends JpaRepository<UserInformation, Long>{
	
}
