package org.spring.users;





import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	 UserService service ; 
	
	@RequestMapping( method=RequestMethod.POST , value="/regist" )
	public String regist(
			@RequestParam("ID"   )           String  id ,
			@RequestParam("password"   )     String  password , 
			@RequestParam("frist"  )          String frsitName  ,
			@RequestParam("last"  )           String lastName ,
			@RequestParam("home"  )           String homeCity ,
			@RequestParam("current"  )        String currentCity ,
			@RequestParam("bio"  )            String bio 
			   ) {
		
	String result = 	service.regist(id ,password , frsitName , lastName , homeCity , currentCity , bio);
		
		return  result  ; 
		
	}
	
	
	@RequestMapping( method=RequestMethod.GET , value="/view" )
	public List<User> view (){
		
		List<User> users = new ArrayList<>();
    	users = service.getall();
	
	return users;
		
	}
	
	
	@RequestMapping( method=RequestMethod.POST , value="/login" )
	public String login( 
			
			@RequestParam("ID") String id ,
			@RequestParam("password") String password
			
			) {
		
       User user = new User();
		  user =  service.getUser(id);
		  
		  if( user==null ) {
			  
			  return "the email addresse or phone number you entered is incorrect " ;  
		 
		  
		  }  else  if(user.getPassword().equals(password)==false) {
		
			return  "the  password you entered is incorrect  "; 
			
		} else return "Authenticated Successfully" ;
		
		
		}
		

}
