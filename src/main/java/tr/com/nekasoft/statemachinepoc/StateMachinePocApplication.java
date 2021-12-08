package tr.com.nekasoft.statemachinepoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "tr.com.nekasoft")
@EnableJpaRepositories(basePackages = "tr.com.nekasoft")
@SpringBootApplication
public class StateMachinePocApplication {

    public static void main(String[] args) {
        SpringApplication.run(StateMachinePocApplication.class, args);
    }

}
