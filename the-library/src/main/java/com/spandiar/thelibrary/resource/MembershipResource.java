package com.spandiar.thelibrary.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spandiar.thelibrary.configuration.LibraryBackendServices;
import com.spandiar.thelibrary.configuration.MembershipConfiguration;
import com.spandiar.thelibrary.model.Membership;

@RestController
@RequestMapping(path = "/library/membership")
public class MembershipResource {
	
	@Autowired
	LibraryBackendServices libraryProxy;
	
	@GetMapping(path = "/details")
	public Membership membershipDetails() {
		
		return libraryProxy.getMembershipDetails();
		
	}

}
