package song.sj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@EnableJpaAuditing // Auditing 활성화
@SpringBootApplication(/*exclude = SecurityAutoConfiguration.class*/)
public class SjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SjApplication.class, args);
	}
}
