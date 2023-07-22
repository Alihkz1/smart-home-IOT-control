package ir.znu.znuproject;

import ir.znu.znuproject.entity.Log;
import ir.znu.znuproject.enums.Role;
import ir.znu.znuproject.repository.LogRepository;
import ir.znu.znuproject.entity.User;
import ir.znu.znuproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class ZnuProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ZnuProjectApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        User ali = new User("alien@gmail.com", passwordEncoder.encode("12345"), LocalDate.of(2001, 1, 18), Role.ADMIN);
//        User omid = new User("omid@gmail.com", passwordEncoder.encode("12345"), LocalDate.of(2007, 3, 25), Role.ADMIN);
//        userRepository.saveAll(List.of(ali, omid));
//        Log fistLog = new Log("light turned on by user", LocalDate.of(2023, 03, 20));
//        logRepository.saveAll(List.of(fistLog));
    }
}
