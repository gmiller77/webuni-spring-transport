package hu.webuni.transport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransportApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		TODO initDB
//		initDbService.initDb();
	}

}
