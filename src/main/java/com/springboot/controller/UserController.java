package com.springboot.controller;

import com.springboot.dao.Dao;
import com.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Dao<User> dao;

    @GetMapping
    public String show(ModelMap model) {
        model.addAttribute("users", dao.getAllEntities());
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping(params = "action=add")
    public String addUser(ModelMap model, @ModelAttribute User user) {
        user.setId(null);
        dao.addEntity(user);
        return show(model);
    }

    @PutMapping(params = "action=update")
    public String updateUser(ModelMap model, @ModelAttribute User user) {
        if (user.getId() != null) {
            dao.updateEntity(user);
        }
        return show(model);
    }

    @DeleteMapping(params = "action=remove")
    public String removeUser(ModelMap model, @ModelAttribute User user) {
        if (user.getId() != null) {
            dao.removeEntityById(user.getId());
        }
        return show(model);
    }

}
