package com.spandiar.restfulwebservices.model;

import java.util.HashMap;
import java.util.Map;

public class MembershipPlans {
	
	private About about;
	private Map<Duration, Integer> plans = new HashMap<>();
	
	public MembershipPlans() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MembershipPlans(About about, Map<Duration, Integer> plans) {
		super();
		this.about = about;
		this.plans = plans;
	}

	public About getAbout() {
		return about;
	}

	public void setAbout(About about) {
		this.about = about;
	}

	public Map<Duration, Integer> getPlans() {
		return plans;
	}

	public void setPlans(Map<Duration, Integer> plans) {
		this.plans = plans;
	}
	
}
