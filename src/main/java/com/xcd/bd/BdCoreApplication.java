package com.xcd.bd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xcd.bd.dao")
public class BdCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdCoreApplication.class, args);
	}

}
