package com.spring.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	Optional<User> findById(String id);

	List<User> findByFirstnameAndLastname(String firstname, String lastname, Pageable pageable);

	List<User> findByAgeGreaterThan(Integer age, Pageable pageable);

	List<User> findByEmail(String email, Pageable pageable);

	/*********************************************************************************************************************
	 * 
	 * See how to retrieve data using nested object.
	 *
	 **/
	List<User> findByAddressCity(String city, Pageable pageable);

	/*********************************************************************************************************************
	 * 
	 * See how to retrieve data using nested object.
	 *
	 **/
	List<User> findByAddressZip(String zip, Pageable pageable);
}