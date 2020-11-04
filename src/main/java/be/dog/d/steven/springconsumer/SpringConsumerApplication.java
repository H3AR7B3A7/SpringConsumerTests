package be.dog.d.steven.springconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringConsumerApplication {

    @Bean
    WebClient client (WebClient.Builder builder){
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringConsumerApplication.class, args);
    }

}
