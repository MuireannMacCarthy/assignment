package com.assignment.csvuploader.service;

import com.assignment.csvuploader.model.Medicine;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface MedicineService {

    ResponseEntity<String> uploadMedicines(MultipartFile file);

    List<Medicine> getMedicines();

    Medicine getMedicine(String code);

    ResponseEntity<String> deleteMedicines();
}
