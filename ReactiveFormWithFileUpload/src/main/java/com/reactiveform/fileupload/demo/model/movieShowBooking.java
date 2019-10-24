package com.reactiveform.fileupload.demo.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class movieShowBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String screenName;
	private String movieName;
	
	@Embedded
	private ShowBooking movieShow;
	
	public movieShowBooking() {
		super();
	}

	public movieShowBooking(Long id, String screenName, String movieName, ShowBooking movieShow) {
		super();
		this.id = id;
		this.screenName = screenName;
		this.movieName = movieName;
		this.movieShow = movieShow;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ShowBooking getMovieShow() {
		return movieShow;
	}

	public void setMovieShow(ShowBooking movieShow) {
		this.movieShow = movieShow;
	}

	@Override
	public String toString() {
		return "movieShowBooking [id=" + id + ", screenName=" + screenName + ", movieName=" + movieName + ", movieShow="
				+ movieShow + "]";
	}
	
}
