package com.Gymweb.gymweb.contoller;

import com.Gymweb.gymweb.dto.UserDto;
import com.Gymweb.gymweb.entity.User;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) throws ValidationException {
        return userService.add(user);
    }


    @GetMapping("/list")
    public List<User> viewUserList(){
        return userService.fetchList();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") long id) throws ValidationException {
        return userService.fetchById(id);
    }

    @PatchMapping("/{id}")
    public User patch(@PathVariable(name = "id") long id, @RequestBody User user) throws ValidationException {
        return userService.patch(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(name = "id")  long id) throws ValidationException {
        userService.deleteById(id);
        return "User deleted successfully !";
    }
    @PutMapping("/{id}")
    public User update(@PathVariable(name = "id") long id, @RequestBody User user) throws ValidationException {
        return userService.update(id,user);
    }
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) throws ValidationException {
        return userService.findByEmail(email);
    }

}
