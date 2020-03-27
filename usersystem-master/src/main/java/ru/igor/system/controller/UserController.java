package ru.igor.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.igor.system.model.User;
import ru.igor.system.service.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
  /*  @GetMapping("/")
    public String pageOne(){
        return "page_one";
    }*/
    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "user_list";
    }
    @GetMapping("/user/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.getById(id));
                return "show_user";
    }
    @GetMapping("/adduser")
    public String createUserPage(){
        return  "create_user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "create_user";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteId(@PathVariable("id") Long id) {
         userService.delete(id);
        return "redirect:/users";
    }
    @GetMapping("/update/{id}")
    public String updateId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
         return "update_user";
    }
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "update_user";
        }
        userService.update(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "page_one";
    }
}
