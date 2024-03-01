package com.assignment.csvuploader.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Medicine {

    @Id
    private String code;

    private String source;

    private String codeListCode;

    private String displayValue;

    private String longDescription;

    private String fromDate;

    private String toDate;

    private Integer sortingPriority;

}
