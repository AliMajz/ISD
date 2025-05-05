package com.Gymweb.gymweb.service;


import com.Gymweb.gymweb.entity.Role;
import com.Gymweb.gymweb.entity.User;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.repository.UserRepo;

import com.Gymweb.gymweb.utils.Securityutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserRepo userRepo;

    @Override
    protected void validateUnique(User entity) throws ValidationException {
        List<User> userList = userRepo.findAll();
        for (User existingUser : userList) {
            if (existingUser.getUsername().equalsIgnoreCase(entity.getUsername())) {
                throw new ValidationException("User " + entity.getUsername() + " already exists.");
            }
        }
        //return false;
    }

    @Override
    public User add(User entity) throws ValidationException {
//        if(!validateUnique(entity)){
//            throw new ValidationException("User with email" + entity.getUsername()+ " Already Exist");
//        }
        validateUnique(entity);
        if(entity.getRole() == Role.MEMBER) {
            throw new ValidationException("You can't add a Member");
        }


        return super.add(entity);
    }

    @Override
    public User fetchById(long id) throws ValidationException {
        Optional<User> user = userRepo.findById(id);
            if(user.isEmpty()){
                throw new ValidationException("User of ID "+id+ " not found");
            }
        return super.fetchById(id);
    }

    @Override
    public List<User> fetchList() {
        return super.fetchList();
    }

    @Override
    public User update(long Id, User entity) throws ValidationException {
        Optional<User> optionalUser = userRepo.findById(Id);

        if (optionalUser.isEmpty()) {
            validateUnique(entity);
        }
        User existingUser = optionalUser.get();
        existingUser.setFname(entity.getFname());
        existingUser.setLname(entity.getLname());
        existingUser.setEmail(entity.getEmail());

        existingUser.setUpdatedBy(Securityutils.getCurrentUsername());

        return super.update(Id, existingUser);
    }

    @Override
    public void deleteById(long id) throws ValidationException {
        Optional<User> user = userRepo.findById(id);
            if(user.isEmpty()){
                throw new ValidationException("No User with ID " +id+ " is found");
            }
        super.deleteById(id);
    }

    @Override
    public User patch(long Id, User entity) throws ValidationException {

        User existingUser = userRepo.findById(Id)
                .orElseThrow(() -> new ValidationException("User with ID " + Id + " not found"));

        if (entity.getFname() != null && !entity.getFname().isBlank()) {
            existingUser.setFname(entity.getFname());
        }

        if (entity.getLname() != null && !entity.getLname().isBlank()) {
            existingUser.setLname(entity.getLname());
        }

        if (entity.getBirthdayDate() != null) {
            existingUser.setBirthdayDate(entity.getBirthdayDate());
        }

        if (entity.getGender() != null && !entity.getGender().isBlank()) {
            existingUser.setGender(entity.getGender());
        }

        if (entity.getEmail() != null && !entity.getEmail().isBlank() &&
                !entity.getEmail().equalsIgnoreCase(existingUser.getEmail())) {
            existingUser.setEmail(entity.getEmail());
        }

        if (entity.getPhoneNb() != null && !entity.getPhoneNb().isBlank()) {
            existingUser.setPhoneNb(entity.getPhoneNb());
        }

        if (entity.getPassword() != null && !entity.getPassword().isBlank()) {
            existingUser.setPassword(entity.getPassword()); // Consider encoding if needed
        }

        if (entity.getRole() != null) {
            existingUser.setRole(entity.getRole());
        }

        return super.patch(Id,entity);
    }

    public User findByEmail(String email) throws ValidationException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ValidationException("User with email " + email + " not found."));
    }
}
