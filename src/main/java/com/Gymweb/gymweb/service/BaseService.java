package com.Gymweb.gymweb.service;

import com.Gymweb.gymweb.entity.User;
import com.Gymweb.gymweb.error.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<T> {


    @Autowired
    private JpaRepository<T, Long> repository;

    protected abstract void validateUnique(T entity  ) throws ValidationException;

    public  T add(T entity) throws ValidationException {
        validateUnique(entity);
        return  repository.save(entity);
    }

    public  void deleteById(long Id) throws ValidationException {
        repository.deleteById(Id);
    }

    public  T fetchById(long Id) throws ValidationException {
        return repository.getById(Id);
    }

    public  List<T> fetchList(){

        return repository.findAll();
    }

    public  T update(long Id, T entity) throws ValidationException {
        return repository.save(entity);
    }

    public  T patch(long Id, T entity) throws ValidationException {
        return repository.save(entity);
    }

//    public abstract Coach patch(String email, Coach entity) throws ValidationException;
}