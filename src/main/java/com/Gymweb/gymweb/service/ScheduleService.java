package com.Gymweb.gymweb.service;


import com.Gymweb.gymweb.entity.Schedule;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.repository.ScheduleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService extends BaseService<Schedule> {

    private ScheduleRepo scheduleRepo;

    @Override
    protected void validateUnique(Schedule entity) throws ValidationException {

    }
    @Override
    public Schedule add(Schedule entity) throws ValidationException {
        return super.add(entity);
    }

    @Override
    public List<Schedule> fetchList() {
        return super.fetchList();
    }

    @Override
    public void deleteById(long id) throws ValidationException {
        Optional<Schedule> schedule = scheduleRepo.findById(id);
        if(schedule.isEmpty()){
            throw new ValidationException("No schedule with ID " +id+ " is found");
        }
        super.deleteById(id);
    }

    @Override
    public Schedule patch(long id, Schedule newData) throws ValidationException {
        Schedule existingSchedule = scheduleRepo.findById(id)
                .orElseThrow(() -> new ValidationException("Schedule with ID " + id + " not found"));

        if (newData.getCoach() != null) {
            existingSchedule.setCoach(newData.getCoach());
        }

        if (newData.getClasses() != null) {
            existingSchedule.setClasses(newData.getClasses());
        }

        if (newData.getStartTime() != null) {
            existingSchedule.setStartTime(newData.getStartTime());
        }

        if (newData.getEndTime() != null) {
            existingSchedule.setEndTime(newData.getEndTime());
        }

        if (newData.getDays() != null && !newData.getDays().isEmpty()) {
            existingSchedule.setDays(newData.getDays());
        }

        return super.patch(id,newData);
    }
}
