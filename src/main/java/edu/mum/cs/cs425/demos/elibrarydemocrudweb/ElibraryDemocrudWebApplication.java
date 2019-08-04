package edu.mum.cs.cs425.demos.elibrarydemocrudweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ElibraryDemocrudWebApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ElibraryDemocrudWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ElibraryDemocrudWebApplication.class, args);
    }

}
