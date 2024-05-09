package com.example.SpringBootMVCJSP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMvcjspApplication {
	// Tomcat (included with spring) does not convert jsp to servlet
	// we need tomcat-jasper for that.
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcjspApplication.class, args);
	}

}
