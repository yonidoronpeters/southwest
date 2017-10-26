/*
 * @author ydp
 */
package com.example.southwest.checkin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SouthwestConfiguration
{
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
