package com.project.user_crud.controller;

import com.project.user_crud.entity.User;
import com.project.user_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    // Home page - show all users
    @GetMapping("/")
    public String home(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User()); // For the form
        return "index";
    }
    
    // Show add user form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isEdit", false);
        return "user-form";
    }
    
    // Show edit user form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("isEdit", true);
        return "user-form";
    }
    
    // Create new user
    @PostMapping("/users")
    public String createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }
    
    // Update user
    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUser(id, user);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }
    
    // Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }
    
    // Search users
    @GetMapping("/search")
    public String searchUsers(@RequestParam String searchTerm, Model model) {
        List<User> users = userService.searchUsersByName(searchTerm);
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        model.addAttribute("searchTerm", searchTerm);
        return "index";
    }
    
    // REST API endpoints
    @GetMapping("/api/users")
    @ResponseBody
    public List<User> getAllUsersApi() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/api/users/{id}")
    @ResponseBody
    public User getUserByIdApi(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    @PostMapping("/api/users")
    @ResponseBody
    public User createUserApi(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    @PutMapping("/api/users/{id}")
    @ResponseBody
    public User updateUserApi(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    
    @DeleteMapping("/api/users/{id}")
    @ResponseBody
    public String deleteUserApi(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
} 