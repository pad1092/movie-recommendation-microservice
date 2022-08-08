package com.movierec.userservice;

import com.movierec.userservice.Entity.User;
import com.movierec.userservice.Utils.KeycloakUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {
	@Autowired
	KeycloakUtil keycloakUtil;
	@Test
	void contextLoads() {
	}
	@Test
	void addUserKeycloak(){
		User user = new User();
		user.setUsername("anhduc");
		user.setEmail("phamduc10920@gmail.com");
		user.setFullname("Duc Pham Anh");
		user.setPassword("anhduc");
//		System.out.println(keycloakUtil.addUser(user));
	}
}
