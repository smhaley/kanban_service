package com.home.kanban_service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public void createUser(@Validated @RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping(path="{user}")
    public void deleteUser(@PathVariable("user") Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping(path="{user}")
    public void updateUser(@PathVariable("user") Long userId, @Validated @RequestBody User user){
        userService.updateUser(userId, user);
    }
}
