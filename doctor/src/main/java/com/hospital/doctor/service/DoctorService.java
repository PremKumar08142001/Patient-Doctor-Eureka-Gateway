package com.hospital.doctor.service;

import com.hospital.doctor.exception.DoctorNotFoundException;
import com.hospital.doctor.model.Doctor;
import com.hospital.doctor.repository.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private IDoctorRepository doctorRepository;

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> findDoctorsBySpecialization(String specialization) {
        List<Doctor> doctors = doctorRepository.findBySpecialization(specialization);
        if (doctors.isEmpty()) {
            throw new DoctorNotFoundException(specialization);
        }
        return doctors;
    }

    public Optional<Doctor> findDoctorById(Long id) {
        return Optional.ofNullable(doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException(id)));
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctorById(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new DoctorNotFoundException(id);
        }
        doctorRepository.deleteById(id);
    }
}
