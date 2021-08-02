package dev.paulmatthews.hashingsalting;

import dev.paulmatthews.hashingsalting.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HashingSaltingApplication {

	public static void main(String[] args) {
//		IncomingUserDTO newUser = new IncomingUserDTO("pdmxdd", "password");
//		User user = User.createUserFromIncomingUserDTO(newUser);
		SpringApplication.run(HashingSaltingApplication.class, args);
	}

}
