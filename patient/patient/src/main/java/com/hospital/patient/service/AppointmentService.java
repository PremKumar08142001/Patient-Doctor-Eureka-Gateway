package com.hospital.patient.service;

import com.hospital.patient.client.DoctorClient;
import com.hospital.patient.model.Appointment;
import com.hospital.patient.model.Patient;
import com.hospital.patient.dto.DoctorDTO;
import com.hospital.patient.repository.AppointmentRepository;
import com.hospital.patient.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorClient doctorClient;
    @Autowired
    private PatientService patientService;
    public Appointment bookAppointment(Appointment appointment) {
        // Fetch patient details
        Patient patient=patientService.getPatientById(appointment.getPatientId());
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found with id: " + appointment.getPatientId());
        }
        appointment.setPatientName(patient.getFirstName()+" "+patient.getLastName());

        // Fetch doctor details
        DoctorDTO doctor = doctorClient.getDoctorById( appointment.getDoctorId());
        if (doctor == null) {
            throw new ResourceNotFoundException("Doctor not found with id: " + appointment.getDoctorId());
        }
        appointment.setDoctorName(doctor.getName());
        appointment.setDoctorSpecialization(doctor.getSpecialization());
        appointment.setDoctorExpertise(doctor.getExpertise());
        appointment.setDoctorAvailability(doctor.getAvailability());

        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void deleteAppointmentById(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    public Appointment updateAppointment(Long appointmentId, Appointment appointmentDetails) {
        Appointment existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + appointmentId));
        Patient patient = patientService.getPatientById(appointmentDetails.getPatientId());
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found with id: " + appointmentDetails.getPatientId());
        }
        existingAppointment.setPatientName(patient.getFirstName() + " " + patient.getLastName());
        existingAppointment.setPatientId(appointmentDetails.getPatientId());
        DoctorDTO doctor = doctorClient.getDoctorById(appointmentDetails.getDoctorId());
        if (doctor == null) {
            throw new ResourceNotFoundException("Doctor not found with id: " + appointmentDetails.getDoctorId());
        }
        existingAppointment.setDoctorName(doctor.getName());
        existingAppointment.setDoctorSpecialization(doctor.getSpecialization());
        existingAppointment.setDoctorExpertise(doctor.getExpertise());
        existingAppointment.setDoctorAvailability(doctor.getAvailability());
        existingAppointment.setDoctorId(appointmentDetails.getDoctorId());
        existingAppointment.setAppointmentDateTime(appointmentDetails.getAppointmentDateTime());
        return appointmentRepository.save(existingAppointment);
    }


    public Iterable<Appointment> getAppointmentsForCurrentMonth() {
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        return appointmentRepository.findByAppointmentDateTimeBetween(startDate, endDate);
    }
}
