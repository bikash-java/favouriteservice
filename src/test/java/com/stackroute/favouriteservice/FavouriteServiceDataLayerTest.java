package com.stackroute.favouriteservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.bean.Book;
import com.stackroute.favouriteservice.interceptor.AuthenticationInterceptor;
import com.stackroute.favouriteservice.repository.FavouriteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("dev")
public class FavouriteServiceDataLayerTest {
	
	@TestConfiguration
	static class Config {
		  
        @Bean
        public AuthenticationInterceptor interceptor() {
            return new AuthenticationInterceptor();
        }
    }
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private FavouriteRepository repository;
	
	
	@Test
	public void testAddToFavorite() {
		
		Book book = new Book();
		
		book.setId(1);
		book.setAuthor("J K Rowling");
		book.setTitle("Harry Potter");
		
		Book insertedBook = repository.save(book);
		
		assertThat(insertedBook.getId() == book.getId());
		assertThat(insertedBook.getAuthor().equals(book.getAuthor()));
		assertThat(insertedBook.getTitle() == book.getTitle());
	}
	
	@Test 
	public void testGetFavorites() {
		
		Book book = new Book();
		
		book.setAuthor("J K Rowling");
		book.setTitle("Harry Potter");
		
		entityManager.persist(book);
		
		repository.save(book);
		
		Iterable<Book> books = repository.findAll();
		
		books.forEach(item -> {
			assertThat(item.getId() == book.getId());
			assertThat(item.getAuthor().equals(book.getAuthor()));
			assertThat(item.getTitle() == book.getTitle());
		});
	}
	
	@Test
	public void testUpdateFavourite() {
		
		Book book = new Book();
		
		book.setAuthor("J K Rowling");
		book.setTitle("Harry Potter");
		
		entityManager.persist(book);
		
		book.setTitle("Half Blood Prince");
		
		Book insertedBook = repository.save(book);
		
		assertThat(insertedBook.getId() == book.getId());
		assertThat(insertedBook.getAuthor().equals(book.getAuthor()));
		assertThat(insertedBook.getTitle() == book.getTitle());
	}
	
	@Test
	public void testDeleteFavourite() {
		
		Book book = new Book();
		
		book.setAuthor("J K Rowling");
		book.setTitle("Harry Potter");
		
		entityManager.persist(book);
		
		List<Book> books  = (List<Book>)repository.findAll();
		
		repository.deleteById(books.get(0).getId());
		
		Optional<Book> optional  = repository.findById(1L);
		
		assertThat(!optional.isPresent());
	}
	

}
