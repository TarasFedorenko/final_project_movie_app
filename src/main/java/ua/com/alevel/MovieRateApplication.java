package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.user.AdminRepository;


@EnableScheduling
@SpringBootApplication
public class MovieRateApplication {

    private BCryptPasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    public MovieRateApplication(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieRateApplication.class, args);
    }

//     @EventListener(ApplicationReadyEvent.class)
//    public void testListener() {
//        String pass = "12345";
//        String hash = passwordEncoder.encode(pass);
//        Admin admin = new Admin();
//        admin.setEmail("admin@mail.com");
//        admin.setPassword(hash);
//        adminRepository.save(admin);
//    }
}