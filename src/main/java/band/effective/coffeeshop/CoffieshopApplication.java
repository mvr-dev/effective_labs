package band.effective.coffeeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
@EnableRetry
public class CoffieshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffieshopApplication.class, args);
	}

}
