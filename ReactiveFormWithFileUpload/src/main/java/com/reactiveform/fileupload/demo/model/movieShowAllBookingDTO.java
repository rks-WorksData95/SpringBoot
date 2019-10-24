package com.reactiveform.fileupload.demo.model;

import java.util.List;

public class movieShowAllBookingDTO {
	
	private List<AllMovieShowBooking> movieShow;

	public movieShowAllBookingDTO() {
		super();
	}

	public movieShowAllBookingDTO(List<AllMovieShowBooking> movieShow) {
		super();
		this.movieShow = movieShow;
	}
	
	public List<AllMovieShowBooking> getMovieShow() {
		return movieShow;
	}

	public void setMovieShow(List<AllMovieShowBooking> movieShow) {
		this.movieShow = movieShow;
	}

	@Override
	public String toString() {
		return "movieShowAllBookingDTO [movieShow=" + movieShow + "]";
	}

}
