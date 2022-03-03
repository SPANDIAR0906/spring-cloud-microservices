package com.spandiar.thelibrarytransactions.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonInclude(value = Include.NON_NULL)
public class UserBase {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@JsonIgnore
	private String id;
	@Size(min = 8, message = "Username cannot be less than 8 characters")
	private String userName;
//	private String password;
	@Size(min = 6, max = 30, message = "FirstName should be between 6 and 30 characters")
	private String firstName;
	@Size(min = 1)
	private String lastName;
	private String email;
	private String phoneNumber;
	@OneToMany(mappedBy = "userBaseUUID")
	@JsonManagedReference
	private List<BookRatings> ratings;
	
	public UserBase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserBase(String id, String userName, String firstName, String lastName, String email, String phoneNumber,
			List<BookRatings> ratings) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.ratings = ratings;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<BookRatings> getRatings() {
		return ratings;
	}

	public void setRatings(List<BookRatings> ratings) {
		this.ratings = ratings;
	}
	
}
