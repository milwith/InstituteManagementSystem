package com.instituteManagement.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long t_id;

    @Column(name = "first_name")
    private String t_firstName;

    @Column(name = "last_name")
    private String t_lastName;

    @Column(name = "email")
    private String t_email;

    @Column(name = "subject")
    private String t_subject;

}
