package com.spandiar.thelibrary.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.spandiar.thelibrary.model.Membership;

@FeignClient(name = "restful-web-services", url = "http://localhost:8350")
public interface LibraryBackendServices {
	
	@GetMapping("/library/membership/details")
	Membership getMembershipDetails();

}
