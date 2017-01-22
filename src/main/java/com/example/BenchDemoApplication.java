package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.spring.config.EnableRatpack;

@SpringBootApplication
@EnableRatpack
public class BenchDemoApplication {

	@Bean
	public Action<Chain> home() {
		return chain -> chain
				.prefix("bench",p -> {
					p.get(ctx -> ctx
							.render("hello")
					);
				});
	}

	public static void main(String[] args) {
		SpringApplication.run(BenchDemoApplication.class, args);
	}
}
