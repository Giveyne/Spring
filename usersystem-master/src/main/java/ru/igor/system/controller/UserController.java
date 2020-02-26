package ru.igor.system.controller;

import ru.igor.HibernateDAO.HibernateConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.igor.system.model.User;
import ru.igor.system.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HibernateConnector hibernateConnector;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public ModelAndView validateUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userFromServer", new User());
        modelAndView.setViewName("users_check_page");
        return modelAndView;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public @ResponseBody String checkUser(@ModelAttribute("userFromServer")User user) {
    return hibernateConnector.connectionDatabase(user);
    }
}
