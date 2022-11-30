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
public class UsersController {

	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("users", userService.index());
		return "index";
	}

	@GetMapping(value = "/index")
	public String indexIndex(Model model) {
		model.addAttribute("users", userService.index());
		return "index";
	}

	@GetMapping("/templates/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.show(id));
		return "show";
	}

	@GetMapping(value = "/templates/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "new";
	}

	@PostMapping(value = "/static")
	public String create(@ModelAttribute("user") @Valid User user,
						 BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "new";

		userService.save(user);
		return "redirect:/index";
	}

	@GetMapping("/templates/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.show(id));
		return "edit";
	}

	@PatchMapping(value ="/index/{id}")
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @PathVariable("id") int id) {
		if (bindingResult.hasErrors())
			return "edit";

		userService.update(id, user);
		return "redirect:/index";
	}

	@DeleteMapping(value ="/templates/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/index";
	}
}