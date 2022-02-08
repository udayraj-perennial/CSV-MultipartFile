package com.perennial.spring.csvexport.csvfileexport.utilities;

import com.perennial.spring.csvexport.csvfileexport.exception.InvalidAddressFormatException;
import com.perennial.spring.csvexport.csvfileexport.exception.InvalidNameFormatException;
import com.perennial.spring.csvexport.csvfileexport.exception.NullAddressException;
import com.perennial.spring.csvexport.csvfileexport.exception.NullNameException;
import com.perennial.spring.csvexport.csvfileexport.model.ImportError;
import com.perennial.spring.csvexport.csvfileexport.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Validator {
    private ValidationResult<Student> validationResult;

    public Validator() {
        validationResult = new ValidationResult();
    }

    private boolean isAlpha(String tocheck) {
        return tocheck.matches("^[a-zA-Z]+\\ [a-zA-Z]+$");
    }

    private boolean isAlphaNumeric(String tocheck) {
        return tocheck.matches("^[a-zA-Z0-9]+\\ [a-zA-Z0-9]+$");
    }

    public ValidationResult validate(Map<Long, List<Optional<String>>> allRows) {
        for (Map.Entry<Long, List<Optional<String>>> entry : allRows.entrySet()) {
            long rowNumber = entry.getKey();
            List<Optional<String>> row =  entry.getValue();

           // System.out.println(rowNumber + ":" + entry.getValue());
            Student student = new Student();
            try {
                long id = Long.parseLong(String.valueOf(row.get(0).get()));
                student.setId(id);

                String name = row.get(1).get();
                if(name.isEmpty()) {
                    throw new NullNameException("Name should not be null");
                }
                if(!isAlpha(String.valueOf(name))) {
                    throw new InvalidNameFormatException("Invalid name format");
                }
                student.setName(String.valueOf(name));

                String address = row.get(2).get();
                if(address.isEmpty()) {
                    throw new NullAddressException("Address should not be null");
                }
                if(!isAlphaNumeric(String.valueOf(address))) {
                    throw new InvalidAddressFormatException("Invalid address format");
                }
                student.setAddress(String.valueOf(address));
                validationResult.addStudent(student);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                ImportError error = new ImportError( String.valueOf(rowNumber), "id", "Invalid Number format");
                validationResult.addError (error);
            } catch (NullNameException ne) {
                ImportError error = new ImportError( String.valueOf(rowNumber), "name", "Name cannot be null");
                validationResult.addError(error);
            } catch (InvalidNameFormatException ie) {
                ImportError error = new ImportError( String.valueOf(rowNumber), "name", "Format of name is not valid");
                validationResult.addError(error);
            } catch (NullAddressException ne) {
                ImportError error = new ImportError( String.valueOf(rowNumber), "address", "Name cannot be null");
                validationResult.addError(error);
            } catch (InvalidAddressFormatException ia) {
                ImportError error = new ImportError( String.valueOf(rowNumber), "address", "Format of address is not valid");
                validationResult.addError(error);
            }
        }
        return validationResult;
    }
}
