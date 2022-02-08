package com.perennial.spring.csvexport.csvfileexport.utilities;

import com.perennial.spring.csvexport.csvfileexport.model.ImportError;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResult<T> {
    List<T> students;
    List<ImportError> errors;



    public ValidationResult() {
        students = new ArrayList<>();
        errors = new ArrayList<>();

    }

    public void addStudent(T student) {
        students.add(student);
    }

    public void addError(ImportError error) {
        errors.add(error);
    }


}
