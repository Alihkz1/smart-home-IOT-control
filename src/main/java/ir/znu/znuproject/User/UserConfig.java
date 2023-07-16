package ir.znu.znuproject.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repo) {
        return args -> {
            User ali = new User(1L, "alien@gmail.com", "654321", LocalDate.of(2001, 1, 18));
            User omid = new User(2L, "omid@gmail.com", "654321", LocalDate.of(2007, 3, 25));
            repo.saveAll(List.of(ali,omid));
        };
    }

}
