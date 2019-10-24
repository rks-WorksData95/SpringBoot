package com.reactiveform.fileupload.demo.model;

import java.sql.Date;

import javax.persistence.Embeddable;

@Embeddable
public class ShowBooking {
	
	private Date showDate;
	private String startTime;
	private String endTime;
	private Integer goldPrice;
	private Integer silverPrice;
	private Integer platinumPrice;
	
	public ShowBooking() {
		super();
	}

	public ShowBooking(Date showDate, String startTime, String endTime, Integer goldPrice, Integer silverPrice,
			Integer platinumPrice) {
		super();
		this.showDate = showDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.goldPrice = goldPrice;
		this.silverPrice = silverPrice;
		this.platinumPrice = platinumPrice;
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
		return "ShowBooking [showDate=" + showDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", goldPrice=" + goldPrice + ", silverPrice=" + silverPrice + ", platinumPrice=" + platinumPrice
				+ "]";
	}
	
}
