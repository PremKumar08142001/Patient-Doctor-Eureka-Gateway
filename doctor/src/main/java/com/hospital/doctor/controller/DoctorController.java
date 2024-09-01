package com.hospital.doctor.controller;
import com.hospital.doctor.exception.DoctorNotFoundException;
import com.hospital.doctor.exception.InvalidDataFoundException;
import com.hospital.doctor.model.Doctor;
import com.hospital.doctor.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.findAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.findDoctorById(id).orElseThrow(() -> new DoctorNotFoundException(id));
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/specialization/{specialization}")
    public List<Doctor> getDoctorsBySpecialization(@PathVariable String specialization) {
        return doctorService.findDoctorsBySpecialization(specialization);
    }
    @PostMapping
    public ResponseEntity<Doctor>  createDoctor(@Valid @RequestBody Doctor doctor, BindingResult result) {
            if (result.hasErrors()) {
                throw new InvalidDataFoundException(result.getAllErrors().toString());
            }
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        Doctor doctor = doctorService.findDoctorById(id).orElseThrow(() -> new DoctorNotFoundException(id));
        doctor.setName(doctorDetails.getName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setExpertise(doctorDetails.getExpertise());
        doctor.setAvailability(doctorDetails.getAvailability());

        Doctor updatedDoctor = doctorService.saveDoctor(doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>("Successfully deleted doctor with Id: "+id, HttpStatus.ACCEPTED);
    }
}
