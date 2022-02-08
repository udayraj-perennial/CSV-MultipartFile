package com.perennial.spring.csvexport.csvfileexport.controller;


import com.perennial.spring.csvexport.csvfileexport.helper.CSVHelper;
import com.perennial.spring.csvexport.csvfileexport.message.ResponseMessage;
import com.perennial.spring.csvexport.csvfileexport.model.Student;
import com.perennial.spring.csvexport.csvfileexport.repository.StudentRepository;
import com.perennial.spring.csvexport.csvfileexport.service.CSVService;
import com.perennial.spring.csvexport.csvfileexport.utilities.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    CSVService fileService;
    @Autowired
    StudentRepository studentRepository;


    /*@PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
*/

    @PostMapping("/uploadNext")
    public ResponseEntity<?> uploadFile2(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {

                ValidationResult<Student> validationResult = fileService.getMap(file);
                studentRepository.saveAll(validationResult.getStudents());
              /*  ByteArrayInputStream byteArrayInputStream = CSVHelper.errorToCSV(validationResult.getErrors());
                validationResult.setByteArrayInputStream(byteArrayInputStream);*/

                return new ResponseEntity<>(validationResult, HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                System.out.println(message + "---------------");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

        message = "Please upload a csv file!";
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


}