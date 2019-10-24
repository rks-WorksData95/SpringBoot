package com.reactiveform.fileupload.demo.Reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reactiveform.fileupload.demo.model.Image;

@Repository
public interface ImageUploadReposetory extends JpaRepository<Image, Long>{
	
}
