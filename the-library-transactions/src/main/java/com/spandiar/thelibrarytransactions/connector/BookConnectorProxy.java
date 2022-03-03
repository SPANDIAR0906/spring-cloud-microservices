package com.spandiar.thelibrarytransactions.connector;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spandiar.thelibrarytransactions.model.Book;

@FeignClient(name = "restful-web-services", url = "restful-web-services")
//@FeignClient(name = "restful-web-services")
//@LoadBalancerClient(name = "restful-web-services")
//@LoadBalancerClient(name = "restful-web-services", configuration = LoadBalancerConfiguration.class)
public interface BookConnectorProxy {
	
	@GetMapping("/library/book/{bookId}")
	Book getBookInfo(@PathVariable("bookId") String bookId);

}
