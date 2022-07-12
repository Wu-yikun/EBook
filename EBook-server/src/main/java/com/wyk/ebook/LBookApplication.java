package com.wyk.ebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wyk.ebook.mapper")
@SpringBootApplication
public class LBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(LBookApplication.class, args);
	}

}
