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
	
	
   public User searchMethod(String  key ){
	   
	    User user ;  
	   
	    if(key.contains("@") ) {
	    	
	    user =	repository.findByEmail(key);
	    return user ; 
	    	
	    	
	    }else if (!key.contains("@") && key.contains("0") ) {
	    	
	    user = 	repository.findOne(key);
	    
	    return user ; 
	    } else   return null ; 
	   
	   
   }
	

	public User getUser(String id) {
		
		User user = new User();
		
		if(id.contains("@") ) {
			
			user = repository.findByEmail(id);
			return user ; 
		}else {
			
			user =  repository.findOne(id);
			return user ;
		}
		
	}

	public  String   regist( String phoneNumber ,  String  email   ,  String password, 
			                 String fristName   ,  String lastName ,  String homeCity,  
			                 String currentCity ,  String bio      ,  String balance ) {
		
		
		
		User testuser  ;
		User user = new User()   ;
		
		testuser =  repository.findOne(phoneNumber);
		
		if(testuser!=null) {
			
			return "this phoneNumber is Already exist" ;
		}
		
		testuser = repository.findByEmail(email);
		
		if (testuser !=null ) {
			
			return "this email is registered once  before please choose Another email Addresse  " ; 
		}
		else {
		
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setPassword(password);
		user.setFristName( fristName);
        user.setLastName ( lastName);
        user.setHomeCity(homeCity);
        user.setCurrentCity(currentCity);
        user.setBio(bio);
        user.setBalance(balance);
		
		
		
		repository.save(user);
	
		return "registered successfully" ;
		
		}
	}


	public User update(User user) {
		return  repository.save(user); 
	}
	
	

}
