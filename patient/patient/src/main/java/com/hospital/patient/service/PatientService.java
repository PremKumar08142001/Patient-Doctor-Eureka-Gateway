package com.hospital.patient.service;

import com.hospital.patient.dto.AuthenticationRequest;
import com.hospital.patient.dto.AuthenticationResponse;
import com.hospital.patient.model.Patient;
import com.hospital.patient.util.JwtUtil;
import com.hospital.patient.repository.PatientRepository;
import com.hospital.patient.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public Patient registerPatient(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        return patientRepository.save(patient);
    }
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Optional<Patient> patient = patientRepository.findByEmail(authenticationRequest.getEmail());
        log.info("User logging in"+patient.toString());
        if (patient.isPresent() && passwordEncoder.matches(authenticationRequest.getPassword(), patient.get().getPassword())) {
            final String jwt = jwtUtil.generateToken(authenticationRequest.getEmail());
            log.info("jwt"+jwt);
            return new AuthenticationResponse(jwt, "successfully logged in");
        }
        log.info("Please enter valid credentials");
        throw new ResourceNotFoundException("Please enter valid credentials");
    }
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void deletePatientById(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }

    public Patient updatePatient(Long id, Patient patient) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patient.setId(id);
        return patientRepository.save(patient);
    }

    public Optional<Patient> findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
}
