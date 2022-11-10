package com.instituteManagement.springboot.controller;

import com.instituteManagement.springboot.exception.ResourceNotFoundException;
import com.instituteManagement.springboot.model.Subject;
import com.instituteManagement.springboot.model.Teacher;
import com.instituteManagement.springboot.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    //get all subjects
    @GetMapping("/subjects")
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    //create subject REST API
    @PostMapping("/subjects")
    public Subject createSubject(@RequestBody Subject subject){
        return subjectRepository.save(subject);
    }

    //get subject by Id REST API
    @GetMapping("/subjects/{sub_id}")
    public ResponseEntity<Subject> getSubjectsById(@PathVariable Long sub_id) {
        Subject subject = subjectRepository.findById(sub_id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject does not exist with id :" + sub_id));
        return ResponseEntity.ok(subject);
    }

    //update subject REST API
    @PutMapping("/subjects/{sub_id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long sub_id, @RequestBody Subject subjectDetails){
        Subject subject = subjectRepository.findById(sub_id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject does not exist with id :" + sub_id));

        subject.setSub_name(subjectDetails.getSub_name());
        subject.setSub_stream(subjectDetails.getSub_stream());

        Subject updatedSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(updatedSubject);
    }
    //delete Subject REST API
    @DeleteMapping("/subjects/{sub_id}")
    public ResponseEntity<Map<String, Boolean>> deleteSubject(@PathVariable Long  sub_id){
        Subject subject = subjectRepository.findById(sub_id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject does not exist with id :" +  sub_id));

        subjectRepository.delete(subject);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
