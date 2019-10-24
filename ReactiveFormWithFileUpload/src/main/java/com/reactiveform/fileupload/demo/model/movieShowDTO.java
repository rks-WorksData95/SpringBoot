package com.reactiveform.fileupload.demo.model;

import java.util.List;

public class movieShowDTO {
	
	private String screenName;
	private String movieName;
	private List<ShowBooking> movieShow;
	
	public movieShowDTO() {
		super();
	}
	
	public movieShowDTO(String screenName, String movieName, List<ShowBooking> movieShow) {
		super();
		this.screenName = screenName;
		this.movieName = movieName;
		this.movieShow = movieShow;
	}
	
	public String getScreenName() {
		return screenName;
	}
	
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public List<ShowBooking> getMovieShow() {
		return movieShow;
	}
	
	public void setMovieShow(List<ShowBooking> movieShow) {
		this.movieShow = movieShow;
	}
	
	@Override
	public String toString() {
		return "movieShowDTO [screenName=" + screenName + ", movieName=" + movieName + ", movieShow=" + movieShow + "]";
	}
	
}
