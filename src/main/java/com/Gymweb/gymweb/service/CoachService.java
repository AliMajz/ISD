package com.Gymweb.gymweb.service;

import com.Gymweb.gymweb.entity.Coach;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.repository.CoachRepo;
import com.Gymweb.gymweb.utils.Securityutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CoachService extends BaseService<Coach> {

    @Autowired
    CoachRepo coachRepo;



    @Override
    protected void validateUnique(Coach entity) throws ValidationException {
        List<Coach> coachList = coachRepo.findAll();
        for (Coach existingCoach : coachList) {
            if (existingCoach.getEmail().equalsIgnoreCase(entity.getEmail())) {
                throw new ValidationException("Coach " + entity.getEmail() + " already exists.");
            }
        }
    }
    @Override
    public Coach add(Coach entity) throws ValidationException {
        validateUnique(entity);
        entity.setCreatedBy(Securityutils.getCurrentUsername());
        return super.add(entity);
    }

    public Coach findByEmail(String email) throws ValidationException {
        return coachRepo.findByEmail(email)
                .orElseThrow(() -> new ValidationException("Coach with email " + email + " not found."));
    }

    @Override
    public List<Coach> fetchList() {
        return super.fetchList();
    }


    public Coach patch(String email, Coach entity) throws ValidationException {

        Coach coach1 = coachRepo.findByEmail(email)
                .orElseThrow(() -> new ValidationException("Coach with email " + email + " not found"));


        if (Objects.nonNull(entity.getFullName()) &&
                !"".equalsIgnoreCase(entity.getFullName())) {
            coach1.setFullName(entity.getFullName());
        }

        if (Objects.nonNull(entity.getEmail()) &&
                !"".equalsIgnoreCase(entity.getEmail())) {
            coach1.setEmail(entity.getEmail());
        }

        if (Objects.nonNull(entity.getSpecialty()) &&
                !"".equalsIgnoreCase(entity.getSpecialty())) {
            coach1.setSpecialty(entity.getSpecialty());
        }

        if (Objects.nonNull(entity.getCreatedBy()) &&
                !"".equalsIgnoreCase(entity.getCreatedBy())) {
            coach1.setCreatedBy(entity.getCreatedBy());
        }

        if (Objects.nonNull(entity.getUpdatedBy()) &&
                !"".equalsIgnoreCase(entity.getUpdatedBy())) {
            coach1.setUpdatedBy(entity.getUpdatedBy());
        }

        if (Objects.nonNull(entity.getClasses()) && !entity.getClasses().isEmpty()) {
            coach1.setClasses(entity.getClasses());
        }
        coach1.setUpdatedBy(Securityutils.getCurrentUsername());
        return coachRepo.save(coach1);
    }


    public void deleteByEmail(String email) throws ValidationException {
        Optional<Coach> coach = coachRepo.findByEmail(email);
        if (coach.isEmpty()) {
            throw new ValidationException("No Coach with email " + email + " is found");
        }

        // Delete the coach directly from the repository
        coachRepo.delete(coach.get());
    }


}
