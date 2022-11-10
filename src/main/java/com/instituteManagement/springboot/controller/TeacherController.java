package com.instituteManagement.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.instituteManagement.springboot.exception.ResourceNotFoundException;
import com.instituteManagement.springboot.model.Teacher;
import com.instituteManagement.springboot.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
public class TeacherController {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    //get all teachers
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    //create teacher REST API
    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher){
        return teacherRepository.save(teacher);
    }

    //get teacher by Id REST API
    @GetMapping("/teachers/{t_id}")
    public ResponseEntity<Teacher> getTeachersById(@PathVariable Long t_id) {
        Teacher teacher = teacherRepository.findById(t_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher does not exist with id :" + t_id));
        return ResponseEntity.ok(teacher);
    }

    //update teacher REST API
    @PutMapping("/teachers/{t_id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long t_id, @RequestBody Teacher teacherDetails){
        Teacher teacher = teacherRepository.findById(t_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher does not exist with id :" + t_id));

        teacher.setT_firstName(teacherDetails.getT_firstName());
        teacher.setT_lastName(teacherDetails.getT_lastName());
        teacher.setT_email(teacherDetails.getT_email());
        teacher.setT_subject(teacherDetails.getT_subject());

        Teacher updatedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    //delete teacher REST API
    @DeleteMapping("/teachers/{t_id}")
    public ResponseEntity<Map<String, Boolean>> deleteTeacher(@PathVariable Long t_id){
        Teacher teacher = teacherRepository.findById(t_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher does not exist with id :" + t_id));

        teacherRepository.delete(teacher);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
