package guru.springframework;

import guru.springframework.util.SpringBootStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWebApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringBootWebApplication.class, args);
        } catch (Exception e){
            new SpringBootStarter().StartWebApplication();
        }
    }
}
