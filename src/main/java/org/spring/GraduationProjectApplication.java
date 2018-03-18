package org.spring;


import org.spring.users.CustomDetailedServer;
import org.spring.users.UserRepository;
import org.spring.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@SpringBootApplication
public class GraduationProjectApplication extends SpringBootServletInitializer  {
   
	@Autowired
	UserService service ; 
	
	public static void main(String[] args) {
		SpringApplication.run(GraduationProjectApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(GraduationProjectApplication.class);
	}
	
	
	@Autowired
	public void  authenticationManager( AuthenticationManagerBuilder builder, UserRepository repository  ) throws Exception {
		
		builder.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
				
				return new CustomDetailedServer(service.getUser(id));
			}
		});
		
		
	}
}
