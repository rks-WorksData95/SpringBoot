package com.reactiveform.fileupload.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.reactiveform.fileupload.demo.Reposetory.AllMovieShowBookingReposetory;
import com.reactiveform.fileupload.demo.Reposetory.ImageUploadReposetory;
import com.reactiveform.fileupload.demo.Reposetory.UploadImageServer;
import com.reactiveform.fileupload.demo.Reposetory.UserInformationReposetory;
import com.reactiveform.fileupload.demo.Reposetory.movieShowBookingReposetory;
import com.reactiveform.fileupload.demo.model.AllMovieShowBooking;
import com.reactiveform.fileupload.demo.model.Image;
import com.reactiveform.fileupload.demo.model.UserInformation;
import com.reactiveform.fileupload.demo.model.movieShowAllBookingDTO;
import com.reactiveform.fileupload.demo.model.movieShowBooking;
import com.reactiveform.fileupload.demo.model.movieShowDTO;
import com.reactiveform.fileupload.demo.model.uploadImageServer;

@Service
public class UserInformationService {
	
	@Autowired
	UserInformationReposetory userInformationReposetory;
	
	@Autowired
	ImageUploadReposetory imageUploadReposetory;
	
	@Autowired
	movieShowBookingReposetory movieShowBooking;
	
	@Autowired
	AllMovieShowBookingReposetory allMovieShowBookingReposetory;
	
	@Autowired
	UploadImageServer uploadImageServer;
	
	public void saveFile(MultipartFile file) throws IOException {
		File converFile=new File("D:\\Ram-CD\\FullStack-Angualr With Spring Boot\\STS-Workspace\\ReactiveFormWithFileUpload\\ProfileImage\\"+file.getOriginalFilename());
		converFile.createNewFile();
		FileOutputStream fout=new FileOutputStream(converFile);
		fout.write(file.getBytes());
		fout.close();
		
		Image image=new Image();
		image.setImageName(file.getOriginalFilename());
		image.setImagePath(converFile.getPath());
		
		imageUploadReposetory.save(image);
	}
	
	public List<Image> retriveImages() {
		return imageUploadReposetory.findAll();
	}
	
	public List<uploadImageServer> retriveImagesFromServer() {
		return uploadImageServer.findAll();
	}

	public UserInformation saveNewUser(UserInformation userInformation) {
		return userInformationReposetory.save(userInformation);
	}
	
	public uploadImageServer saveUserServerImagedata(uploadImageServer userInfo) {
		return uploadImageServer.save(userInfo);
	}
	
	public List<UserInformation> retriveUsers() {
		return userInformationReposetory.findAll();
	}
	
	public void saveMultipleFiles(Image image) {
		imageUploadReposetory.save(image);
	}
	
	public List<movieShowBooking> saveDynamicForm(movieShowDTO movieShow) {
		
		List<movieShowBooking> bookingDetails=new ArrayList<movieShowBooking>();
		
		for(int i=0;i<movieShow.getMovieShow().size();i++) {
			
			movieShowBooking msBooking=new movieShowBooking();
			
			msBooking.setScreenName(movieShow.getScreenName());
			msBooking.setMovieName(movieShow.getMovieName());
			msBooking.setMovieShow(movieShow.getMovieShow().get(i));
			bookingDetails.add(movieShowBooking.save(msBooking));
		}
		return bookingDetails;
	}
	
	public List<AllMovieShowBooking> saveDynamicAllForm(movieShowAllBookingDTO movieShow) {
		
		List<AllMovieShowBooking> bookingDetails=new ArrayList<AllMovieShowBooking>();
		
		for(int i=0;i<movieShow.getMovieShow().size();i++) {
			AllMovieShowBooking msAllBooking=new AllMovieShowBooking();
			msAllBooking = movieShow.getMovieShow().get(i);
//			System.out.println("msAllBooking : "+msAllBooking.toString());
			bookingDetails.add(allMovieShowBookingReposetory.save(msAllBooking));
		}
		return bookingDetails;
	}
}
