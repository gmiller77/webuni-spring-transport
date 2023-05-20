package hu.webuni.transport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.transport.service.InitDBService;


@SpringBootApplication
public class TransportApplication implements CommandLineRunner {

	@Autowired
	InitDBService initDBService;
	
	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDBService.initDB();
		
		//TODO set JWT token to 1 month 
	}

}
