package com.perennial.spring.csvexport.csvfileexport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportError {
    private String rowNumber;
    private String columnName;
    private String errorDetails;
}