package band.effective.coffieshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoffieshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffieshopApplication.class, args);
	}

}
