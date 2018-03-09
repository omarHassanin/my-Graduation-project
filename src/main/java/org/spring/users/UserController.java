package org.spring.users;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	 UserService service ; 
	
	// register Users 
	@RequestMapping( method=RequestMethod.POST , value="/regist" )
	public String regist(
			@RequestParam("phoneNumber")   String phoneNumber ,
			@RequestParam("email"      )   String email ,
			@RequestParam("password"   )   String password , 
			@RequestParam("frist"      )   String frsitName  ,
			@RequestParam("last"       )   String lastName ,
			@RequestParam("home"       )   String homeCity ,
			@RequestParam("current"    )   String currentCity ,
			@RequestParam("bio"        )   String bio ,
			@RequestParam("balance"    )   String balance
			   ) {String result = 	service.regist(phoneNumber , email ,password , frsitName , lastName , homeCity , currentCity , bio , balance);
		
		 return  result  ; 
		
	}
	

	//search for users
	@RequestMapping( method=RequestMethod.POST  ,  value="/search" )
	@Produces(MediaType.APPLICATION_JSON)
	public User  search (@RequestParam("key") String key  ){
		
		User user ; 
	    user = 	service.searchMethod(key);
	
    	return user;
		
	}
	
	// Authentication user
	@RequestMapping( method=RequestMethod.POST , value="/login" )
	public String login( 
		     
			@RequestParam("id")        String id , 
			@RequestParam("password")  String password
			           ) {
		  
	
          User user = new User();
		  user =  service.getUser(id);
		  
		  if( user==null ) {
			  
			  return "invalid email Addresse or  phone number please  try again" ;  
		 
		  
		  }  else  if(user.getPassword().equals(password)==false) {
		
			return  "the  password you entered is incorrect  "; 
			
		} else return "Authenticated Successfully" ;
		
		
		}
		

}
