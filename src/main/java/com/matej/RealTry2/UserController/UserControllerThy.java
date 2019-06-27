//package com.matej.RealTry2.UserController;
//
//import com.matej.RealTry2.UserEntity.User;
//import com.matej.RealTry2.UserRepository.UserRepositoryImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class UserControllerThy {
//    @Autowired
//    UserRepositoryImpl userRepositoryImpl;
//
//    @GetMapping("/thy")
//    public String showUserOnThy(Model model) {
//        model.addAttribute("ass", userRepositoryImpl.getAllUsers());
//        return "administration";
//    }
//
//
//    @RequestMapping(value = "/thy", method = RequestMethod.POST)
//    public ModelAndView addUserOnThy(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//        userRepositoryImpl.addUser(user);
//        return new ModelAndView("redirect:/thy");
//    }
//
//    @RequestMapping(value = "/thy", method = RequestMethod.POST)
//    public ModelAndView deleteUserOnThy(@RequestParam("id") int id) {
//        userRepositoryImpl.deleteUser(id);
//        return new ModelAndView(("redirect:/thy"));
//    }
//}
//
