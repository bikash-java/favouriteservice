package com.stackroute.favouriteservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.controller.FavouriteServiceController;
import com.stackroute.favouriteservice.repository.FavouriteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class FavouriteServiceApplicationTests {
	
	@Autowired
	private FavouriteServiceController controller;
	
	@Autowired
	private FavouriteRepository repository;

	@Test
	public void contextLoads() {
		
		assertThat(repository).isNotNull();

		assertThat(controller).isNotNull();
	}

}

