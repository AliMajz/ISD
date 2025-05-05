package com.Gymweb.gymweb.contoller;

import com.Gymweb.gymweb.entity.Coach;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/coach")
public class CoachController {

    @Autowired
    CoachService coachService;

    @PostMapping("/add")
    public Coach addCoach(@RequestBody Coach coach) throws ValidationException {
        return coachService.add(coach);
    }

    @GetMapping("/{email}")
    public Coach getUser(@PathVariable(name = "email") String email) throws ValidationException {
        return coachService.findByEmail(email);
    }

    @GetMapping("/list")
    public List<Coach> viewCoachList(){
        return coachService.fetchList();
    }

    @DeleteMapping("/{email}")
    public String deleteByEmail(@PathVariable(name = "email")  String email) throws ValidationException {
        coachService.deleteByEmail(email);
        return "Coach deleted successfully !";
    }

    @PatchMapping("/{email}")
    public Coach patch(@PathVariable(name = "email") String email, @RequestBody Coach coach) throws ValidationException {
        return coachService.patch(email, coach);
    }
}
