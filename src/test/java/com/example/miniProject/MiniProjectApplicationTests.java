package com.example.miniProject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.example.miniProject.Model.User;
import com.example.miniProject.Repo.UserRepo;
import com.example.miniProject.Service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class MiniProjectApplicationTests {

	@Autowired
	private UserService uService;

	@Autowired
	private UserRepo uRepo;

	@Test
	void contextLoads() {
	}

	private User testUserInfo() {
		User user = new User();
		user.setEmail("test@aol.com");
		user.setPassword("testing123");
		// user.setId(UUID.randomUUID().toString().substring(0, 8));
		return user;
	}

	@Test
	public void testExistingUser() {
		assertTrue(uService.auth("test@aol.com", "testing123"));
	}

	@BeforeEach
	public void testCreateUser() {
		try {
			uService.insertUser(testUserInfo());
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void searchUser() {
		assertTrue(uRepo.getUserByEmail("test@aol.com").isPresent());
	}

}
