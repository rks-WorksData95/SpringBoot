package com.reactiveform.fileupload.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactiveform.fileupload.demo.model.AllMovieShowBooking;
import com.reactiveform.fileupload.demo.model.Image;
import com.reactiveform.fileupload.demo.model.Message;
import com.reactiveform.fileupload.demo.model.UserInformation;
import com.reactiveform.fileupload.demo.model.movieShowAllBookingDTO;
import com.reactiveform.fileupload.demo.model.movieShowBooking;
import com.reactiveform.fileupload.demo.model.movieShowDTO;
import com.reactiveform.fileupload.demo.model.uploadImageServer;
import com.reactiveform.fileupload.demo.service.UserInformationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserInformationController {

	@Autowired
	UserInformationService userInformationService;
	
	@Autowired
	ServletContext context;
	
	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Message> uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
		
		userInformationService.saveFile(file);
		
		return new ResponseEntity<Message>(new Message("File Uploaded Successfully."),HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/retriveAllImages")
	public ResponseEntity<List<String>> retriveAllImages() throws IOException{
		List<String> images=new ArrayList<String>();
//		List<Image> imageDb=userInformationService.retriveImages();
		//		String filesPath = context.getRealPath("/ProfileImage");
				File fileFolder=new File("D:\\Ram-CD\\FullStack-Angualr With Spring Boot\\STS-Workspace\\ReactiveFormWithFileUpload\\ProfileImage");
	//			String path = fileFolder.getPath();
				if(fileFolder!=null) {
					for(final File file: fileFolder.listFiles()) {
						if(!file.isDirectory()) {
							String encodeBase64=null;
							String extension = FilenameUtils.getExtension(file.getName());
							FileInputStream fileInputStream=new FileInputStream(file);
							byte[] byteas=new byte[(int)file.length()];
							fileInputStream.read(byteas);
							encodeBase64 = Base64.getEncoder().encodeToString(byteas);
							images.add("data:image/"+extension+";base64,"+encodeBase64);
							fileInputStream.close();
						}
					}
				}
		return new ResponseEntity<List<String>>(images, HttpStatus.OK);
	}
	
	@GetMapping(path = "/retriveImages")
	public ResponseEntity<Map<String, String>> retriveImages() throws IOException{
//		List<String> images=new ArrayList<String>();
		Map<String, String> images=new HashMap<String, String>();
		List<Image> imageDb=userInformationService.retriveImages();
		
		for(Image image: imageDb) {
			File fileFolder=new File(image.getImagePath());
			if(fileFolder!=null) {
				String encodeBase64=null;
				String extension = FilenameUtils.getExtension(image.getImageName());
				FileInputStream fileInputStream=new FileInputStream(fileFolder);
				byte[] byteas=new byte[(int)fileFolder.length()];
				fileInputStream.read(byteas);
				encodeBase64 = Base64.getEncoder().encodeToString(byteas);
				images.put(image.getImageName(), "data:image/"+extension+";base64,"+encodeBase64);
				fileInputStream.close();
			}
		}
		return new ResponseEntity<Map<String, String>>(images, HttpStatus.OK);
	}
	
	@PostMapping(path = "/uploadmultipleFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Message> uploadMultipleFile(@RequestParam("files") MultipartFile[] files) throws IOException{
		FileOutputStream fout=null;
//		System.out.println("Files Size : "+files.length);
		for(MultipartFile multipartFile: files) {
			File converFile=new File("D:\\Ram-CD\\FullStack-Angualr With Spring Boot\\STS-Workspace\\ReactiveFormWithFileUpload\\ProfileImage\\"+multipartFile.getOriginalFilename());
			converFile.createNewFile();
			fout=new FileOutputStream(converFile);
			fout.write(multipartFile.getBytes());
			fout.close();
			
			Image image=new Image();
			image.setImageName(multipartFile.getOriginalFilename());
			image.setImagePath(converFile.getPath());
			userInformationService.saveMultipleFiles(image);
		}
		return new ResponseEntity<Message>(new Message("All Images Uploaded Successfully."),HttpStatus.OK);
	}
	
	@GetMapping(path = "downloadFile/{fileName:.+}")
	public ResponseEntity<Object> downloadFile(@PathVariable String fileName) throws IOException{
		String filePath="D:\\Ram-CD\\FullStack-Angualr With Spring Boot\\STS-Workspace\\ReactiveFormWithFileUpload\\ProfileImage\\"+fileName;
		Path path=Paths.get(filePath);
		String extension = FilenameUtils.getExtension(fileName);
		Resource resource=null;
		
		resource =new UrlResource(path.toUri());
		
		if(resource.exists()) {
			 
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType("image/"+extension))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
					.body(resource);
			
		}else {
			return new ResponseEntity<Object>("File Not Found",HttpStatus.OK);
		}
	}
	
	@PostMapping(path = "/saveNewUser")
	public ResponseEntity<Message> savenewUser(@RequestParam("file") MultipartFile file, @RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException{
		
		UserInformation userInformation=new ObjectMapper().readValue(user, UserInformation.class);
		userInformation.setCreatedDate(new Date());
		userInformation.setUserImageName(file.getOriginalFilename());
		userInformation.setUserImage(file.getBytes());
		UserInformation dbUserInformation = userInformationService.saveNewUser(userInformation);
		if(dbUserInformation!=null) {
			return new ResponseEntity<Message>(new Message("user Register Successfully."), HttpStatus.OK);
		}else {
			return new ResponseEntity<Message>(new Message("user Register Failed."), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping(path = "/saveUser")
	public ResponseEntity<Message> saveUser(@RequestBody uploadImageServer userInfo) throws IOException{
		
		boolean isExist=new File(context.getRealPath("/userProfile/")).exists();
		if(!isExist) {
			new File(context.getRealPath("/userProfile/")).mkdir();
		}
		
//		System.out.println("UserInfo : "+userInfo.getUploadFileName().substring(12));
		String fileName=userInfo.getUploadFileName().substring(12);
		String modifiedFileName=userInfo.getNewFileName()+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
		File serverFile=new File(context.getRealPath("/userProfile/")+File.separator+modifiedFileName);
//		System.out.println("File Path : "+serverFile);
//		System.out.println("Server Path : "+context.getRealPath(File.separator));
		try {
			String base64Image = userInfo.getFile().split(",")[1];
//			byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
			FileUtils.writeByteArrayToFile(serverFile, Base64.getDecoder().decode(base64Image));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
//		UserInformation userInformation=new ObjectMapper().readValue(user, UserInformation.class);
//		userInformation.setCreatedDate(new Date());
//		userInformation.setUserImageName(modifiedFileName);
//		userInformation.setUserImage(file.getBytes());
		
		userInfo.setNewFileName(modifiedFileName);
		userInfo.setUploadFileName(fileName);
		uploadImageServer dbImageServer = userInformationService.saveUserServerImagedata(userInfo);
		if(dbImageServer!=null) {
			return new ResponseEntity<Message>(new Message("Image Uploaded Successfully!"), HttpStatus.OK);
		}else {
			return new ResponseEntity<Message>(new Message("Image Not Uploaded, Something Wrong!"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/retriveImagesFromServer")
	public ResponseEntity<Map<String, String>> retriveImagesFromServer() throws IOException{
//		List<String> images=new ArrayList<String>();
		Map<String, String> images=new HashMap<String, String>();
		List<uploadImageServer> imageServerDb=userInformationService.retriveImagesFromServer();
		
		for(uploadImageServer imageServer: imageServerDb) {
			File fileFolder=new File(context.getRealPath("/userProfile/")+imageServer.getNewFileName());
			if(fileFolder!=null) {
				String encodeBase64=null;
				String extension = FilenameUtils.getExtension(imageServer.getNewFileName());
				FileInputStream fileInputStream=new FileInputStream(fileFolder);
				byte[] byteas=new byte[(int)fileFolder.length()];
				fileInputStream.read(byteas);
				encodeBase64 = Base64.getEncoder().encodeToString(byteas);
				images.put(imageServer.getUploadFileName().split("_")[0], "data:image/"+extension+";base64,"+encodeBase64);
				fileInputStream.close();
			}
		}
		return new ResponseEntity<Map<String, String>>(images, HttpStatus.OK);
	}
	
	@GetMapping(path = "/retriveUser")
	public List<UserInformation> retriveAllUser() {
		return userInformationService.retriveUsers();
	}
	
	@PostMapping(path = "/saveMovieShows")
	public ResponseEntity<List<movieShowBooking>> saveMovieShows(@RequestBody movieShowDTO movieShow) {
		
//		System.out.println("Movie Shows : "+movieShow.getMovieShow().size());
		
		List<movieShowBooking> bookingDetails = userInformationService.saveDynamicForm(movieShow);
		
		if(bookingDetails!=null) {
			return new ResponseEntity<List<movieShowBooking>>(bookingDetails, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<movieShowBooking>>(bookingDetails, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = "/saveAllMovieShows")
	public ResponseEntity<List<AllMovieShowBooking>> saveAllMovieShows(@RequestBody movieShowAllBookingDTO allMovieShow) {
		
//		System.out.println("Movie Shows : "+allMovieShow.getMovieShow().size());
		
		List<AllMovieShowBooking> bookingDetails = userInformationService.saveDynamicAllForm(allMovieShow);
		
		if(bookingDetails!=null) {
			return new ResponseEntity<List<AllMovieShowBooking>>(bookingDetails, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<AllMovieShowBooking>>(bookingDetails, HttpStatus.BAD_REQUEST);
		}
	}
}
