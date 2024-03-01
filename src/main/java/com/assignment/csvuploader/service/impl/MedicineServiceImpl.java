package com.assignment.csvuploader.service.impl;

import com.assignment.csvuploader.exception.MedicineNotFoundException;
import com.assignment.csvuploader.model.Medicine;
import com.assignment.csvuploader.model.FileRepresentation;
import com.assignment.csvuploader.repository.MedicineRepository;
import com.assignment.csvuploader.service.MedicineService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.List;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MedicineServiceImpl implements MedicineService {


    @Autowired
    private final MedicineRepository medicineRepository;


    @Override
    public ResponseEntity<String> uploadMedicines(MultipartFile file) {
        try {
            medicineRepository.saveAll(parseFileToMedicine(file));
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save medicines");
        }
    }

    private List<Medicine> parseFileToMedicine(MultipartFile file) throws IOException {
        try {
            InputStream inputStream = file.getInputStream();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            HeaderColumnNameMappingStrategy<FileRepresentation> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
            mappingStrategy.setType(FileRepresentation.class);
            CsvToBean<FileRepresentation> csvToBean =
                    new CsvToBeanBuilder<FileRepresentation>(reader)
                            .withMappingStrategy(mappingStrategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> Medicine.builder()
                            .code(csvLine.getCode())
                            .source(csvLine.getSource())
                            .codeListCode(csvLine.getCodeListCode())
                            .displayValue(csvLine.getDisplayValue())
                            .longDescription(csvLine.getLongDescription())
                            .fromDate(csvLine.getFromDate())
                            .toDate(csvLine.getToDate())
                            .sortingPriority(csvLine.getSortingPriority())
                            .build()
                    )
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("Failed to deserialise file");
        }

    }

    @Override
    public List<Medicine> getMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine getMedicine(String code) {
        return medicineRepository.findById(code)
                .orElseThrow(() -> new MedicineNotFoundException("This medicine does not exist"));
    }

    @Override
    public ResponseEntity<String> deleteMedicines() {
        medicineRepository.deleteAll();
        return ResponseEntity.ok("All records successfully deleted");
    }
}
