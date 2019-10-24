package com.reactiveform.fileupload.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AllMovieShowBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String screenName;
	private String movieName;
	private Date showDate;
	private String startTime;
	private String endTime;
	private Integer goldPrice;
	private Integer silverPrice;
	private Integer platinumPrice;
	
	public AllMovieShowBooking() {
		super();
	}

	public AllMovieShowBooking(Long id, String screenName, String movieName, Date showDate, String startTime,
			String endTime, Integer goldPrice, Integer silverPrice, Integer platinumPrice) {
		super();
		this.id = id;
		this.screenName = screenName;
		this.movieName = movieName;
		this.showDate = showDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.goldPrice = goldPrice;
		this.silverPrice = silverPrice;
		this.platinumPrice = platinumPrice;
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

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getGoldPrice() {
		return goldPrice;
	}

	public void setGoldPrice(Integer goldPrice) {
		this.goldPrice = goldPrice;
	}

	public Integer getSilverPrice() {
		return silverPrice;
	}

	public void setSilverPrice(Integer silverPrice) {
		this.silverPrice = silverPrice;
	}

	public Integer getPlatinumPrice() {
		return platinumPrice;
	}

	public void setPlatinumPrice(Integer platinumPrice) {
		this.platinumPrice = platinumPrice;
	}

	@Override
	public String toString() {
		return "AllMovieShowBooking [id=" + id + ", screenName=" + screenName + ", movieName=" + movieName
				+ ", showDate=" + showDate + ", startTime=" + startTime + ", endTime=" + endTime + ", goldPrice="
				+ goldPrice + ", silverPrice=" + silverPrice + ", platinumPrice=" + platinumPrice + "]";
	}
	
}
