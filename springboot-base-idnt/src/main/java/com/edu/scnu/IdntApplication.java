package com.edu.scnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableSpringHttpSession
@MapperScan("com.edu.scnu.idnt.mapper")
public class IdntApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(IdntApplication.class, args);
	}

	@Bean
	public MapSessionRepository sessionRepository() {
		return new MapSessionRepository(new ConcurrentHashMap<>());
	}
}
