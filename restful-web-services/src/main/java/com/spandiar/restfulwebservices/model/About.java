package com.spandiar.restfulwebservices.model;

public class About {
	
	private String heading;
	private String body;
	
	public About() {
		super();
		// TODO Auto-generated constructor stub
	}

	public About(String heading, String body) {
		super();
		this.heading = heading;
		this.body = body;
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
