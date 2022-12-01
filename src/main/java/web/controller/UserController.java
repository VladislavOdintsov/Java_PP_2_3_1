package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}



	@GetMapping( "/")
	public String start(Model model){
		model.addAttribute("start");
		return "start";
	}

	@GetMapping(value = "/users")
	public String tableUsers(Model model) {
		model.addAttribute("users", userService.allUsers());
		return "users";
	}

	@GetMapping("/addUser")
	public String newUser(@ModelAttribute("user") User user) {
		return "/addUser";
	}

	@PostMapping
	public String addUser(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "redirect:/users";
	}

	@GetMapping("/{id}/changeUser")
	public String changeUser(Model model, @PathVariable("id") long id) {
		model.addAttribute("user", userService.getUserById(id));
		return "/changeUser";
	}

	@PostMapping("/{id}")
	public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
		userService.changeUser(id, user);
		return "redirect:/users";
	}

//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView tableUsers() {
//		List<User> users = userService.allUsers();
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("users");
//		modelAndView.addObject("users", users);
//		return modelAndView;
//	}

//	@GetMapping(value = "/users")
//	public String tableUsers(ModelMap model){
//		model.addAttribute("users", userService.allUsers());
//		return "users";
//	}

//	@GetMapping(value = "/changeUser/{id}")
//	public String changeUser(@PathVariable long id, ModelMap model) {
//		model.addAttribute("user", userService.getUserById(id));
//		return "changeUser";
//	}
//
//	@RequestMapping(value = "/changeUser/{id}", method = RequestMethod.GET)
//	public ModelAndView changeUser(@PathVariable long id) {
//		User user = userService.getUserById(id);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("changeUser");
//		modelAndView.addObject("user", user);
//		return modelAndView;
//	}
//	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
//	public ModelAndView changeUser(@ModelAttribute User user) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("redirect:/users");
//		userService.changeUser(user);
//		return modelAndView;
//	}
//	@GetMapping(value = "/addUser")
//	public String addUser(ModelMap model){
//		model.addAttribute("addUser", new User());
//		return "addUser";
//	}
//
//	@PostMapping
//	public String create(@ModelAttribute("user") User user){
//		userService.addUser(user);
//		return "redirect:/users";
//	}



//	@RequestMapping(value = "/changeUser/{id}", method = RequestMethod.GET)
//	public ModelAndView changeUser(@PathVariable("id") long id) {
//		User user = userService.getUserById(id);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("changeUser");
//		modelAndView.addObject("user", user);
//		return modelAndView;
//	}
//	@RequestMapping (value = "/changeUser", method = RequestMethod.POST)
//	public ModelAndView changeUser(@ModelAttribute("user") User user) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("redirect:/");
//		userService.changeUser(user);
//		return modelAndView;
//	}

	@GetMapping(value = "/deleteUser")
	public String deleteUser(ModelMap model){

		return "deleteUser";
	}
}