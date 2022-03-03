package com.spandiar.thelibrary.model;

import java.util.Map;

public class Membership {
	
	private About about;
	private Map<String, String> plans;

	public Membership() {
		super();
		// TODO Auto-generated constructor stub
	}

	public About getAbout() {
		return about;
	}

	public void setAbout(About about) {
		this.about = about;
	}

	public Map<String, String> getPlans() {
		return plans;
	}

	public void setPlans(Map<String, String> plans) {
		this.plans = plans;
	}
	
}

class About {
	
	private String heading;
	private String body;
	
	public About() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}