package com.vmware.talentboost.ics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class IcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcsApplication.class, args);
	}

}
