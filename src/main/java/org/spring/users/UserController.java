package org.spring.users;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;



@RestController
@Path("/")
public class UserController {
	
	
	@Autowired
	TokenStore tokenStore ; 
	 
  
	 
	
	@RequestMapping( method=RequestMethod.GET  ,  value="/home" )
	public String  test() {
		
		return "welcome" ; 
	}
	
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
	@RequestMapping( method=RequestMethod.POST  ,  value="/profile" )
	@Produces(MediaType.APPLICATION_JSON)
	public User  getUserProfile(@RequestParam("id") String id  ) {
		  
	     User user  = new User ();
		 user =  service.getUser(id);
		  
	      return   user  ;
		
		
		  }
		
	
	
	@RequestMapping(value = "/profile/update", method = RequestMethod.PUT ,consumes=MediaType.APPLICATION_JSON , produces=MediaType.APPLICATION_JSON)
	public User update(@RequestBody User user  ) {
		
		System.out.println(user.getPhoneNumber());
		System.out.println(user.getEmail());
		
	   return   service.update( user );
		
	} 
	
	
	
	
	
	
	
	
	
	 

	    @RequestMapping(value = "/oauth/revoke-token", method = RequestMethod.GET)
	    @ResponseStatus(HttpStatus.OK)
	    public String  logout(HttpServletRequest request) {
	        String authHeader = request.getHeader("Authorization");
	        
	        if (authHeader != null ) {
	        	
	            String tokenValue = authHeader.replace("Bearer", "").trim();
	            System.out.println(tokenValue);
	            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
	            if(  accessToken== null) return "you are  Already logged out before  " ; 
	            tokenStore.removeAccessToken(accessToken);
	            return "logged out Successfully " ; 
	            
	        }else {
	        	
	        	return "empty Token Header " ; 
	        }
	        
	        
	    }
	    
	    
	}




