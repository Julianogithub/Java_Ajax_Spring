package com.JMRocha.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.JMRocha.demo.model.SocialMetaTag;
import com.JMRocha.demo.service.SocialMetaTagService;

@SpringBootApplication
public class DemoAjaxApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoAjaxApplication.class, args);
	}
	
	@Autowired
	SocialMetaTagService service;
	
	@Override
	public void run(String... args) throws Exception {
		
		SocialMetaTag og = service.getSocialMetaTagByUrl("https://naturalmed.com.br/produto/curcuma-plus-60-caps-vitafor/");
		System.out.println("\n\t"+og.toString()+"\n");
		
		SocialMetaTag twitter = service.getTwitterCardByUrl("https://naturalmed.com.br/produto/curcuma-plus-60-caps-vitafor/");
		System.out.println("\n\t" + twitter.toString()+"\n");
		
	}

}
