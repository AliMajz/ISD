//package com.Gymweb.gymweb.service;
//
//import com.Gymweb.gymweb.entity.Classes;
//
//import com.Gymweb.gymweb.entity.Coach;
//import com.Gymweb.gymweb.error.ValidationException;
//import com.Gymweb.gymweb.repository.ClassesRepo;
//import com.Gymweb.gymweb.utils.Securityutils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ClassesService extends BaseService<Classes> {
//
//    @Autowired
//    ClassesRepo classesRepo;
//
//    @Override
//    protected void validateUnique(Classes entity) throws ValidationException {
//
//    }
//    @Override
//    public Classes add(Classes entity) throws ValidationException {
//
//        return super.add(entity);
//    }
//    public Classes findById(long id) throws ValidationException {
//        return classesRepo.findById(id)
//                .orElseThrow(() -> new ValidationException("Coach with id " + id + " not found."));
//    }
//    @Override
//    public List<Classes> fetchList() {
//        return super.fetchList();
//    }
//
//    public void deleteById(long id) throws ValidationException {
//        Optional<Classes> classes = classesRepo.findById(id);
//        if (classes.isEmpty()) {
//            throw new ValidationException("No Class with id " + id + " is found");
//        }
//        classesRepo.delete(classes.get());
//    }
//
//    public Classes patch(Long id, Classes updatedClass) throws ValidationException {
//        Classes existingClass = classesRepo.findById(id)
//                .orElseThrow(() -> new ValidationException("Class with ID " + id + " not found"));
//
//        if (updatedClass.getName() != null && !updatedClass.getName().isBlank()) {
//            existingClass.setName(updatedClass.getName());
//        }
//
//        if (updatedClass.getStartTime() != null) {
//            existingClass.setStartTime(updatedClass.getStartTime());
//        }
//
//        if (updatedClass.getDay() != null) {
//            existingClass.setDay(updatedClass.getDay());
//        }
//
//        if (updatedClass.getEndTime() != null) {
//            existingClass.setEndTime(updatedClass.getEndTime());
//        }
//
//        if (updatedClass.getCoach() != null) {
//            existingClass.setCoach(updatedClass.getCoach());
//        }
//
//        return classesRepo.save(existingClass);
//    }
//}
