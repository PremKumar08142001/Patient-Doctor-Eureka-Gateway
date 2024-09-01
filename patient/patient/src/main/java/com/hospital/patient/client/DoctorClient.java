package com.hospital.patient.client;

import com.hospital.patient.dto.DoctorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "doctor-service")
public interface DoctorClient {

    @GetMapping("/doctors/{id}")
    DoctorDTO getDoctorById(@PathVariable("id") Long id);

    @GetMapping("/doctors")
    List<DoctorDTO> getAllDoctors();

    @GetMapping("/specialization/{specialization}")
    List<DoctorDTO> getDoctorsBySpecialization(@PathVariable("specialization") String specialization);
}
