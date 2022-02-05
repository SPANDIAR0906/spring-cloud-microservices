package com.spandiar.restfulwebservices.resource;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spandiar.restfulwebservices.configuration.MembershipConfigurationProperties;
import com.spandiar.restfulwebservices.model.About;
import com.spandiar.restfulwebservices.model.Duration;
import com.spandiar.restfulwebservices.model.MembershipPlans;

@RestController
@RequestMapping("/library/membership")
public class Membership {
	
	@Autowired
	private LibraryResource libraryController;
	
	@Autowired
	private MembershipConfigurationProperties mcp;
	
	@GetMapping("/details")
	public MembershipPlans membershipDetails() {
		
		return membershipDetailsBuilder();
		
	}

	private MembershipPlans membershipDetailsBuilder() {
		
		About about = libraryController.aboutTheLibraryBuilder();
		Map<Duration, Integer> membershipDetails = new LinkedHashMap<>();
		
		membershipDetails.put(Duration.monthly, mcp.getMonthly());
		membershipDetails.put(Duration.annual, mcp.getAnnual());
		membershipDetails.put(Duration.life, mcp.getLife());
		
		return new MembershipPlans(about, membershipDetails);
	}

}
