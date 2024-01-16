package com.fthec.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.fthec.workshopmongo.domain.Post;


@Service
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' }}")
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [{date: {$gte: ?1 }, {date: {$lte: ?2} }, {$or:[{ 'title': { $regex: ?0, $options: 'i' }},{ 'bady': { $regex: ?0, $options: 'i' }},{ 'comments.text': { $regex: ?0, $options: 'i' }} ] } ")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
	

}
