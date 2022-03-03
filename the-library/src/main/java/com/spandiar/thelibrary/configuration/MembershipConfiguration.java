package com.spandiar.thelibrary.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MembershipConfiguration {
	
	@Value("${the-library.monthly}")
	private int monthly;
	@Value("${the-library.annual}")
	private int annual;
	@Value("${the-library.life}")
	private int life;
	
	
	public MembershipConfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MembershipConfiguration(int monthly, int annual, int life) {
		super();
		this.monthly = monthly;
		this.annual = annual;
		this.life = life;
	}


	public int getMonthly() {
		return monthly;
	}


	public void setMonthly(int monthly) {
		this.monthly = monthly;
	}


	public int getAnnual() {
		return annual;
	}


	public void setAnnual(int annual) {
		this.annual = annual;
	}


	public int getLife() {
		return life;
	}


	public void setLife(int life) {
		this.life = life;
	}
	

}
