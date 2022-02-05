package com.spandiar.restfulwebservices.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.spandiar.restfulwebservices.model.Duration;

@Component
@ConfigurationProperties("membership-plans")
public class MembershipConfigurationProperties {
	
	private int monthly;
	private int annual;
	private int life;
	
	
	public MembershipConfigurationProperties() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MembershipConfigurationProperties(int monthly, int annual, int life) {
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
