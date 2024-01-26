package com.main.userauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.userauth.entity.User;
import com.main.userauth.repo.UserRepo;

@Controller
public class UserAuthController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	
	 @GetMapping("/login")
	    public String showLoginForm() {
	        return "login"; // Assuming you have a login.html (or .jsp, .thymeleaf, etc.) template
	    }
	 @GetMapping("/dashboard")
	    public String showDashboard() {
	        return "dashboard";
	    }
	  @GetMapping("/registration")
	    public String showRegistrationForm() {
		  System.out.println("rih==============================================");
	        return "registration";
	    }
	  
	  
	  @PostMapping("/register")
	    public String registerUser(@RequestParam String username, @RequestParam String password) {
		  System.out.println("****************************************************************************************");
	        
	        if (userRepo.findByUsername(username) != null) {
	            
	            return "redirect:/registration?error";
	        }

	        
	        String encodedPassword = passwordEncoder.encode(password);

	        // Create a new user
	        User user = new User();
	        user.setUsername(username);
	        user.setPassword(encodedPassword);

	        // Save the user to the database
	        userRepo.save(user);

	        return "redirect:/login"; // Redirect to login page after registration
	    }

	 
//	 @GetMapping("/custom-login")
//	    public String showCustomLoginForm() {
//	        return "custom-login"; // Assuming you have a custom-login.html (or .jsp, .thymeleaf, etc.) template
//	    }
//	 @GetMapping("/access-denied")
//	    public String showAccessDeniedPage() {
//	        return "access-denied"; // Assuming you have an access-denied.html (or .jsp, .thymeleaf, etc.) template
//	    }
//
//	    @GetMapping("/session-expired")
//	    public String showSessionExpiredPage() {
//	        return "session-expired"; // Assuming you have a session-expired.html (or .jsp, .thymeleaf, etc.) template
//	    }

}
