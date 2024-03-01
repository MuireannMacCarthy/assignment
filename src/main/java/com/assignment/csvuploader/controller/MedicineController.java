package com.assignment.csvuploader.controller;


import com.assignment.csvuploader.model.Medicine;
import com.assignment.csvuploader.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/medicines")
    public ResponseEntity<String> uploadMedicine(@RequestParam("file") MultipartFile file) {

        return medicineService.uploadMedicines(file);

    }

    @GetMapping("/medicines")
    public List<Medicine> getAllMedicines() {

        return medicineService.getMedicines();
    }

    @GetMapping("/medicine/{code}")
    public Medicine getMedicineByCode(@PathVariable String code) {

        return medicineService.getMedicine(code);
    }

    @DeleteMapping("/medicines")
    public ResponseEntity<String> deleteMedicines() {
        return medicineService.deleteMedicines();
    }

}
