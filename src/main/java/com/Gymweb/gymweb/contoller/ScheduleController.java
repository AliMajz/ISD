package com.Gymweb.gymweb.contoller;

import com.Gymweb.gymweb.entity.Member;
import com.Gymweb.gymweb.entity.Schedule;
import com.Gymweb.gymweb.entity.User;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")

public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/add")
    public Schedule addSchedule(@RequestBody Schedule schedule) throws ValidationException {
        return scheduleService.add(schedule);
    }

    @GetMapping("/list")
    public List<Schedule> viewScheduleList(){
        return scheduleService.fetchList();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(name = "id")  long id) throws ValidationException {
        scheduleService.deleteById(id);
        return "User deleted successfully !";
    }

    @PatchMapping("/{id}")
    public Schedule patch(@PathVariable(name = "id") long id, @RequestBody Schedule schedule) throws ValidationException {
        return scheduleService.patch(id, schedule);
    }
}
