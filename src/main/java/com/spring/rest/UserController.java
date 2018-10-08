package com.spring.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public ResponseEntity<Iterable<com.spring.rest.User>> User(Pageable pageable) {
		Iterable<User> list = userRepository.findAll(pageable);
		return new ResponseEntity<Iterable<User>>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/usersByFirstnameLastname")
	public ResponseEntity<Iterable<com.spring.rest.User>> findUsersByFirstnameAndLastName(Pageable pageable,
			@RequestParam String firstname, @RequestParam String lastname) {
		Iterable<User> list = userRepository.findByFirstnameAndLastname(firstname, lastname, pageable);
		return new ResponseEntity<Iterable<User>>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/usersMoreThanAge")
	public ResponseEntity<Iterable<com.spring.rest.User>> findUsersBfindByAgeGreaterThanyFirstnameAndLastName(
			Pageable pageable, @RequestParam int age) {
		Iterable<User> list = userRepository.findByAgeGreaterThan(age, pageable);
		return new ResponseEntity<Iterable<User>>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/usersByEmail")
	public ResponseEntity<Iterable<com.spring.rest.User>> findUsersByEmail(Pageable pageable,
			@RequestParam String email) {
		Iterable<User> list = userRepository.findByEmail(email, pageable);
		return new ResponseEntity<Iterable<User>>(list, HttpStatus.OK);
	}

	/*********************************************************************************************************************
	 * 
	 * See how to retrieve data using nested object.
	 * 
	 * @param pageable
	 * @param city
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/usersByCity")
	public ResponseEntity<Iterable<com.spring.rest.User>> findByAddressCity(Pageable pageable,
			@RequestParam String city) {
		Iterable<User> list = userRepository.findByAddressCity(city, pageable);
		return new ResponseEntity<Iterable<User>>(list, HttpStatus.OK);
	}

	/*********************************************************************************************************************
	 * 
	 * See how to retrieve data using nested object.
	 * 
	 * @param pageable
	 * @param zip
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/usersByZip")
	public ResponseEntity<Iterable<com.spring.rest.User>> findByAddressZip(Pageable pageable,
			@RequestParam String zip) {
		Iterable<User> list = userRepository.findByAddressZip(zip, pageable);
		return new ResponseEntity<Iterable<User>>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public ResponseEntity<com.spring.rest.User> save(@RequestBody User user) {
		userRepository.save(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
	public ResponseEntity<com.spring.rest.User> show(@PathVariable String id) {
		Optional<com.spring.rest.User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			System.out.println("User with id " + id + " not found");
			throw new ResourceNotFoundException();
		}
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
	public ResponseEntity<com.spring.rest.User> update(@PathVariable String id, @RequestBody User User) {
		User existingUser = userRepository.findById(id).get();
		if (existingUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		if (User.getFirstname() != null)
			existingUser.setFirstname(User.getFirstname());
		if (User.getLastname() != null)
			existingUser.setLastname(User.getLastname());
		if (User.getGender() != null)
			existingUser.setGender(User.getGender());
		if (User.getEmail() != null)
			existingUser.setEmail(User.getEmail());
		if (User.getAge() != null)
			existingUser.setAge(User.getAge());

		if (User.getUsername() != null)
			existingUser.setUsername(User.getUsername());
		if (User.getPassword() != null)
			existingUser.setPassword(User.getPassword());

		if (User.getAddress() != null) {
			Address address = new Address();
			address.setAddress1(User.getAddress().getAddress1());
			address.setAddress2(User.getAddress().getAddress2());
			address.setCity(User.getAddress().getCity());
			address.setState(User.getAddress().getState());
			address.setZip(User.getAddress().getZip());
			address.setCountry(User.getAddress().getCountry());
			existingUser.setAddress(address);
		}

		if (User.getContact() != null) {
			Contact contact = new Contact();
			contact.setType(User.getContact().getType());
			contact.setValue(User.getContact().getValue());
			existingUser.setContact(contact);

		}
		userRepository.save(existingUser);
		return new ResponseEntity<User>(existingUser, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	public ResponseEntity<com.spring.rest.User> delete(@PathVariable String id) {
		Optional<com.spring.rest.User> User = userRepository.findById(id);
		if (User == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		if (User.isPresent())
			userRepository.delete(User.get());

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
