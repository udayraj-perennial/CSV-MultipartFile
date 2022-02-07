package com.perennial.spring.csvexport.csvfileexport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="students")
public class Student {

    @Id                             //this annotation is used for primary key
    @Column(name="id")              //used to define the column in database
    private long id;

    @Column(name="name")
    private String name;

    @Column(name = "address")
    private String address;


}
