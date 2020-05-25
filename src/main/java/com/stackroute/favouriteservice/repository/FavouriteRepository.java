package com.stackroute.favouriteservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.bean.Book;


public interface FavouriteRepository extends CrudRepository<Book, Long>{

}
