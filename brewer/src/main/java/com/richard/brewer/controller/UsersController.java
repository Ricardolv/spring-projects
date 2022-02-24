package com.richard.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.controller.page.PageWrapper;
import com.richard.brewer.model.User;
import com.richard.brewer.repository.filter.UserFilter;
import com.richard.brewer.service.GroupsService;
import com.richard.brewer.service.StatusUser;
import com.richard.brewer.service.UsersService;
import com.richard.brewer.service.exception.ImpossibleDeleteEntityException;
import com.richard.brewer.service.exception.UserEmailExistsException;
import com.richard.brewer.service.exception.UserPasswordRequiredException;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private GroupsService groupsServie;
	
	@GetMapping("/new")
	public ModelAndView newUser(User user) {
		ModelAndView mv = new ModelAndView("user/register-users");
		mv.addObject("groups", groupsServie.findAll());
		return mv;
	}
	
	@PostMapping(value = { "/new", "{\\d+}" })
	public ModelAndView save(@Valid User user, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newUser(user);
		}
		
		try {
			
			usersService.save(user);
			
		} catch (UserEmailExistsException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return newUser(user);
		} catch (UserPasswordRequiredException e) {
			result.rejectValue("password", e.getMessage(), e.getMessage());
			return newUser(user);
		}
		
		attributes.addFlashAttribute("message", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/users/new");
	}
	
	@GetMapping
	public ModelAndView search(UserFilter userFilter, 
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("user/search-users");
		mv.addObject("groups", groupsServie.findAll());
		
		
		PageWrapper<User> pageWrapper = new PageWrapper<>(usersService.filter(userFilter, pageable), httpServletRequest);
		mv.addObject("page", pageWrapper);
		
		return mv;
	}
	
	@PutMapping("/status")
	@ResponseStatus(code = HttpStatus.OK)
	public void statusUpdate(@RequestParam("codes[]") Long[] codes, @RequestParam("status") StatusUser status) {
		usersService.statusAlter(codes, status);
	}
	
	@DeleteMapping("/{code}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable Long code) {
		User user = usersService.findOfGroups(code);
		try {
			usersService.delete(user);
			
		} catch (ImpossibleDeleteEntityException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{code}")
	public ModelAndView edit(@PathVariable Long code) {
		User user = usersService.findOfGroups(code);
		ModelAndView mv = newUser(user);
		mv.addObject(user);
		return mv;
	}
}
