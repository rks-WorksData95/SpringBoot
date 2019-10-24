package com.reactiveform.fileupload.demo.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class UserInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String address;
	private Date dateOfBirth;
	private String gender;
	private String hobby;
	private Date createdDate;
	private Date updatedDate;
	
	@Lob
	private byte[] userImage;
	
	private String userImageName;
	
	public UserInformation() {
		super();
	}

	public UserInformation(Long userid, String firstName, String lastName, String emailId, String address,
			Date dateOfBirth, String gender, String hobby, Date createdDate, Date updatedDate, byte[] userImage,
			String userImageName) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.hobby = hobby;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.userImage = userImage;
		this.userImageName = userImageName;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

	public String getUserImageName() {
		return userImageName;
	}

	public void setUserImageName(String userImageName) {
		this.userImageName = userImageName;
	}

	@Override
	public String toString() {
		return "UserInformation [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", gender="
				+ gender + ", hobby=" + hobby + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", userImage=" + Arrays.toString(userImage) + ", userImageName=" + userImageName + "]";
	}
	
}
