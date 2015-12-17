package buddy.TA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public final class TeachingAssistanceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeachingAssistanceApplication.class, args);
    }
}
