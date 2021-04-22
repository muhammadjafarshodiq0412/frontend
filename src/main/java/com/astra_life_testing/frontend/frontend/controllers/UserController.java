package com.astra_life_testing.frontend.frontend.controllers;

import com.astra_life_testing.frontend.frontend.models.UserModel;
import com.astra_life_testing.frontend.frontend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String getAll(Model model){
        List<UserModel> userModels = userService.allUser();
        UserModel userDto = new UserModel();
        model.addAttribute("users", userModels);
        model.addAttribute("userDto", userDto);
        return "index_template";
    }

    @PostMapping("/add")
    public String save(@Validated UserModel model) {
        userService.save(model);
        return "redirect:/user";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable UserModel model) {
        userService.update(model);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete/{id}" , method = RequestMethod.GET)
    public String delete(@PathVariable String id) {
        userService.delete(id);
        return "redirect:/user";
    }

    @PostMapping("view/{id}")
    public String getById(Model model, @PathVariable String id) {
        model.addAttribute("userDto", userService.getById(id));
        return "user_edit";
    }

}
