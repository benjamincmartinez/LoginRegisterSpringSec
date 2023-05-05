package com.springsecurity.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import com.springsecurity.dto.UserDto;
import com.springsecurity.entity.User;
import com.springsecurity.service.UserService;

@Controller
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
        this.userService = userService;
    }
	
	//Handles homepage requests.
	@GetMapping("/index")
	public String home() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		//Create model object to store data
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
	}
	
	//User registration form submission
	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto,
			BindingResult result,
			Model model) {
		User existingUser = userService.findUserByEmail(userDto.getEmail());
		
		if(existingUser != null &&  existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null, "There is already an account with this email");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("user",userDto);
			return "/register";
		}
		
		userService.saveUser(userDto);
		return "redirect:/register?success";
	}
	
	// Mapping for login requests
    @GetMapping("/login")
    public String login(){
        return "login";
    }
	
	//Mapping for the user to get a list of users
	@GetMapping("/users")
	public String users(Model model) {
		List<UserDto> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}
}
