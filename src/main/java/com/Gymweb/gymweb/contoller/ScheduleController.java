package com.Gymweb.gymweb.contoller;

import com.Gymweb.gymweb.dto.ScheduleDto;
import com.Gymweb.gymweb.entity.Coach;
import com.Gymweb.gymweb.entity.Schedule;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/add")
    public Schedule addSchedule(@RequestBody ScheduleDto dto) throws ValidationException {
        return scheduleService.add(dto);
    }

    @GetMapping("/list")
    public List<Schedule> fetchList(){
        return scheduleService.fetchList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id")  long id) throws ValidationException {
        scheduleService.deleteById(id);
    }
}
