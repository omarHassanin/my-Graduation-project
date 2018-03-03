package org.spring.users;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository ;

	

	public List<User> getall() {
		List<User> users = new  ArrayList<>();
		 repository.findAll()
		 .forEach(users::add);
		 
		 return users ;
		
	}

	public User getUser(String id) {
		
		User user = new User();
		
		user =  repository.findOne(id);
		
		return user ;
	}

	public  String   regist(String id, String password, String fristName, String lastName,
			String homeCity, String currentCity, String bio) {
		
		User user = new User();
		
		user.setID(id);
		user.setPassword(password);
		user.setFristName( fristName);
        user.setLastName ( lastName);
        user.setHomeCity(homeCity);
        user.setCurrentCity(currentCity);
        user.setBio(bio);
		
		
		
		repository.save(user);
	
		return "registered successfully" ;
	}
	
	

}
