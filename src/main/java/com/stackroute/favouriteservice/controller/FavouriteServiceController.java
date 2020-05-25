package com.stackroute.favouriteservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.bean.Book;
import com.stackroute.favouriteservice.exception.NoDataFoundException;
import com.stackroute.favouriteservice.repository.FavouriteRepository;


@RestController
@RequestMapping("/favourite")
@CrossOrigin(origins = "http://localhost:4200")
public class FavouriteServiceController {
	
	@Autowired
	private FavouriteRepository repository;
	
	@PostMapping
	public Book addToFavorite(@RequestBody Book book) {
		
		return repository.save(book);
	}
	
	@GetMapping
	public Iterable<Book> getFavorites() {
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Book getFavorite(@PathVariable Long id) throws NoDataFoundException {
		
		Optional<Book> book = repository.findById(id);
		
		if(book.isPresent()) {
			return book.get();
		}else {
			throw new NoDataFoundException();
		}

	}
	
	@PutMapping
	public Book updateFavorite(@RequestBody Book book) {
		
		return repository.save(book);
		
	}
	
	@DeleteMapping("/{id}")
	public void removeFromFavorite(@PathVariable Long id) {
		
		repository.deleteById(id);
	}

}
