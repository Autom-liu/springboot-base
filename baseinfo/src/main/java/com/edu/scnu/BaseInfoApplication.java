package com.edu.scnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.edu.scnu.baseinfo.mapper")
public class BaseInfoApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BaseInfoApplication.class, args);
	}
}
