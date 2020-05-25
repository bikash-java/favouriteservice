package com.stackroute.favouriteservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.stackroute.favouriteservice.bean.Book;
import com.stackroute.favouriteservice.interceptor.AuthenticationInterceptor;
import com.stackroute.favouriteservice.repository.FavouriteRepository;


@RunWith(SpringRunner.class)
@WebMvcTest
@ActiveProfiles("dev")
public class FavouriteServiceWebLayerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FavouriteRepository repository;
	
	@MockBean
	private AuthenticationInterceptor interceptor;

	@Before
	public void setUp() {
		
		Book book = new Book();
		
		book.setId(1);
		book.setAuthor("J K Rowling");
		book.setTitle("Harry Potter");
		
		List<Book> bookList = new ArrayList<>();
		
		bookList.add(book);

		Mockito.when(repository.findAll()).thenReturn(bookList);
		Mockito.when(repository.save(book)).thenReturn(book);
	}
	
	@Test
	public void testAddFavourite() throws Exception {
		
		String book = "\"id\":1,\"title\":\"Concept Of Physics2\", \"author\":\"bkkk\"";
		
		MvcResult result = mockMvc.perform(post("/favourite/").content(book).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		assertThat(content.equals(book));
	}
	
	@Test
	public void testGetFavourites() throws Exception {
		
		String book = "\"id\":1,\"title\":\"Harry Potter\", \"author\":\"J K Rowling\"";
		
		MvcResult result = mockMvc.perform(get("/favourite/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		String content = result.getResponse().getContentAsString();
		assertThat(content.equals(book));
		
	}
	
	@Test
	public void testUpdateFavourite() throws Exception {
		
		String book = "\"id\":1,\"title\":\"Concept Of Physics2\", \"author\":\"bkkk\"";
		
		MvcResult result = mockMvc.perform(put("/favourite/").content(book).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		assertThat(content.equals(book));
	}
	
	@Test
	public void testDeleteFavourite() throws Exception {
		
		String book = "\"id\":1,\"title\":\"Concept Of Physics2\", \"author\":\"bkkk\"";
		
		MvcResult result = mockMvc.perform(delete("/favourite/1").content(book).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		assertThat(content.equals(book));
	}
}
