package ru.igor.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.igor.system.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String pageOne(){
        return "page_one";
    }
    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "User_list";
    }
    @GetMapping("user/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.getById(id));
                return "show_user";
    }
}
