package com.york.etl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.york.etl.common.mapper")
@SpringBootApplication
public class ETLApplication {

	public static void main(String[] args) {
		SpringApplication.run(ETLApplication.class, args);
	}

}
