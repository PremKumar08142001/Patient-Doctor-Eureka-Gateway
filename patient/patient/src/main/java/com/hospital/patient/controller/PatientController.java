package com.hospital.patient.controller;

import com.hospital.patient.dto.AuthenticationRequest;
import com.hospital.patient.dto.AuthenticationResponse;
import com.hospital.patient.model.Patient;
import com.hospital.patient.service.PatientService;
import com.hospital.patient.exception.ResourceNotFoundException;
import com.hospital.patient.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Patient> registerPatient(@Valid @RequestBody Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            throw new  ResourceNotFoundException(result.getAllErrors().toString());
        }
        return ResponseEntity.ok(patientService.registerPatient(patient));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
            return ResponseEntity.ok(patientService.login(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
            return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable Long id) {
            patientService.deletePatientById(id);
            return new ResponseEntity<>("Successfully deleted patient with Id: "+id, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
            return ResponseEntity.ok(patientService.updatePatient(id, patient));
    }
}
