package ir.znu.znuproject;

import ir.znu.znuproject.repository.LogRepository;
import ir.znu.znuproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ZnuProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ZnuProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
