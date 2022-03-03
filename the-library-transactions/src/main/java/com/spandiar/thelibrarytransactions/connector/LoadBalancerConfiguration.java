package com.spandiar.thelibrarytransactions.connector;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class LoadBalancerConfiguration {

	@Bean
	@Primary
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
		System.out.println("Configuring Load balancer to prefer same instance");
		return ServiceInstanceListSupplier.builder()
					.withSameInstancePreference()
					.build(context);
	}

}
