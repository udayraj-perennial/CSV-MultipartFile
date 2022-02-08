package com.perennial.spring.csvexport.csvfileexport.helper;

import com.perennial.spring.csvexport.csvfileexport.model.ImportError;
import com.perennial.spring.csvexport.csvfileexport.model.Student;
import com.perennial.spring.csvexport.csvfileexport.utilities.ErrorReport;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Id", "Name", "Address"};

    public static boolean hasCSVFormat(MultipartFile file) {


        return TYPE.equals(file.getContentType());
    }

   /* public static ByteArrayInputStream studentsToCSV(List<Student> students) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream(); CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Student student : students) {
                List<String> data = Arrays.asList(String.valueOf(student.getId()), student.getName(), student.getAddress());

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static List<Student> csvToStudents(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Student> students = new ArrayList<Student>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();


            for (CSVRecord csvRecord : csvRecords) {
                Student student = new Student(Long.parseLong(csvRecord.get("Id")), csvRecord.get("Name"), csvRecord.get("Address"));

                students.add(student);
            }

            return students;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }*/

    /*public static Map<Long, List<Optional<String>>> csvToMap(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            Map<Long, List<Optional<String>>> students = new HashMap<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            long rowNumber = 1;


            for (CSVRecord csvRecord : csvRecords) {
                //Student student = new Student(Long.parseLong(csvRecord.get("Id")), csvRecord.get("Name"), csvRecord.get("Address"));


                //students.put(student);

                List<Optional<String>> listObj = new ArrayList<>();

                listObj.add(Optional.ofNullable(csvRecord.get("id")));
                listObj.add(Optional.ofNullable(csvRecord.get("name")));
                listObj.add(Optional.ofNullable(csvRecord.get("address")));

                students.put(rowNumber++, listObj);

            }

            return students;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }*/

    public static Map<Long, List<Optional<String>>> csvToMap(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()))
        {

            Map<Long, List<Optional<String>>> students = new HashMap<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            long rowNumber = 1;

            for (CSVRecord csvRecord : csvRecords) {

                List<Optional<String>> listObj = new ArrayList<>();

                listObj.add(Optional.ofNullable(csvRecord.get("id")));
                listObj.add(Optional.ofNullable(csvRecord.get("name")));
                listObj.add(Optional.ofNullable(csvRecord.get("address")));

                students.put(rowNumber++, listObj);
            }


            return students;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

   /* public static ByteArrayInputStream errorToCSV(List<ImportError> importErrorList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        String temporaryDir = System.getProperty("java.io.tmpdir");


        try (ByteArrayOutputStream out = new ByteArrayOutputStream(); CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (ImportError importError : importErrorList) {
                List<String> data = Arrays.asList(String.valueOf(importError.getRowNumber()), importError.getColumnName(), importError.getErrorDetails());
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }*/
}