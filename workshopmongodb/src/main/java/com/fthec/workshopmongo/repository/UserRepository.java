package com.fthec.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fthec.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
