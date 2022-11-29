package com.springboot.CRUDApp.controller;

import com.springboot.CRUDApp.models.User;
import com.springboot.CRUDApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
//	@RequestMapping("/users")
public class UsersController {

	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("users", userService.index());
		return "users/index";
	}

	@GetMapping("/users/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.show(id));
		return "users/show";
	}

	@GetMapping("/users/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "users/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") @Valid User user,
						 BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "users/new";

		userService.save(user);
		return "redirect:/";
	}

	@GetMapping("/users/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.show(id));
		return "users/edit";
	}

	@PatchMapping("/users/{id}")
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @PathVariable("id") int id) {
		if (bindingResult.hasErrors())
			return "users/edit";

		userService.update(id, user);
		return "redirect:/";
	}

	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/";
	}
}