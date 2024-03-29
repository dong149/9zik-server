package com.goozik;

import com.goozik.security.model.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class GoozikApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoozikApiApplication.class, args);
    }

}
