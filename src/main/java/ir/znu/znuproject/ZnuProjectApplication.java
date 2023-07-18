package ir.znu.znuproject;

import ir.znu.znuproject.entity.Log;
import ir.znu.znuproject.repository.LogRepository;
import ir.znu.znuproject.entity.User;
import ir.znu.znuproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ZnuProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ZnuProjectApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogRepository logRepository;

    @Override
    public void run(String... args) throws Exception {
        User ali = new User(1L, "alien@gmail.com", "654321", LocalDate.of(2001, 1, 18));
        User omid = new User(2L, "omid@gmail.com", "654321", LocalDate.of(2007, 3, 25));
        userRepository.saveAll(List.of(ali, omid));
        Log fistLog = new Log(1L, "light turned on by user", LocalDate.of(2023, 03, 20));
        logRepository.saveAll(List.of(fistLog));
    }
}
