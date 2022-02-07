package com.perennial.spring.csvexport.csvfileexport.service;

import com.perennial.spring.csvexport.csvfileexport.helper.CSVHelper;
import com.perennial.spring.csvexport.csvfileexport.model.Student;
import com.perennial.spring.csvexport.csvfileexport.repository.StudentRepository;
import com.perennial.spring.csvexport.csvfileexport.utilities.ErrorReport;
import com.perennial.spring.csvexport.csvfileexport.utilities.ValidationResult;
import com.perennial.spring.csvexport.csvfileexport.utilities.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CSVService {
    @Autowired
    StudentRepository studentRepository;

    public void save(MultipartFile file) {
        try {
            List<Student> students = CSVHelper.csvToStudents(file.getInputStream());
            studentRepository.saveAll(students);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public ByteArrayInputStream load() {

        List<Student> students = studentRepository.findAll();

        ByteArrayInputStream in = CSVHelper.studentsToCSV(students);
        return in;
    }

    public List<Student> getMap(MultipartFile file) throws IOException {
        //Read data in universal format
        Map<Long, List<Optional<String>>> allRows = CSVHelper.csvToMap(file.getInputStream());
        //Validate the data
        Validator validator = new Validator();
        ValidationResult<Student> validationResult = validator.validate(allRows);
        //Create error report
        new ErrorReport(validationResult.getErrors());
        //Save valid records to database
        return validationResult.getStudents();
    }
}
