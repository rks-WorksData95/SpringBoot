package com.reactiveform.fileupload.demo.model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class uploadImageServer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String newFileName;
	private String uploadFileName;
	
	@Transient
	private String file;
		
	public uploadImageServer() {
		super();
	}
	
	public uploadImageServer(Long id, String newFileName, String uploadFileName, String file) {
		super();
		this.id = id;
		this.newFileName = newFileName;
		this.uploadFileName = uploadFileName;
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "uploadImageServer [id=" + id + ", newFileName=" + newFileName + ", uploadFileName=" + uploadFileName
				+ ", file=" + file + "]";
	}

}
