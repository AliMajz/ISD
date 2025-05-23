//package com.Gymweb.gymweb.contoller;
//
//import com.Gymweb.gymweb.entity.Classes;
//import com.Gymweb.gymweb.entity.Coach;
//import com.Gymweb.gymweb.error.ValidationException;
//import com.Gymweb.gymweb.service.ClassesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/classes")
//public class ClassesController {
//
//    @Autowired
//    ClassesService classesService;
//
//    @PostMapping("/add")
//    public Classes addClass(@RequestBody Classes classes) throws ValidationException {
//        return classesService.add(classes);
//    }
//    @GetMapping("/{id}")
//    public Classes getClass(@PathVariable(name = "id") long id) throws ValidationException {
//        return classesService.findById(id);
//    }
//    @GetMapping("/list")
//    public List<Classes> viewClassesList(){
//        return classesService.fetchList();
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteById(@PathVariable(name = "id")  long id) throws ValidationException {
//        classesService.deleteById(id);
//        return "Class deleted successfully !";
//    }
//    @PatchMapping("/classes/{id}")
//    public ResponseEntity<Classes> patchClass(@PathVariable Long id, @RequestBody Classes updatedClass) throws ValidationException {
//        return ResponseEntity.ok(classesService.patch(id, updatedClass));
//    }
//}
