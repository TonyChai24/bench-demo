package com.example;

import com.example.config.EmbeddedServerConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BenchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BenchDemoApplication.class, args);
	}

	@Bean
    public CommandLineRunner registerMbean(EmbeddedServerConfig config){
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                config.registerMxbean();
            }
        };
    }
}
