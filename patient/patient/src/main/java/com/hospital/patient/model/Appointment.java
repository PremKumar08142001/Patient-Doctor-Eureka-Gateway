package com.hospital.patient.model;
import jakarta.validation.constraints.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Patient ID is required")
    private Long patientId;
    @NotNull(message = "Doctor ID is required")
    private Long doctorId;
    private String patientName;
    private String doctorName;
    private String doctorSpecialization;
    private String doctorExpertise;
    private String doctorAvailability;
    @NotNull(message = "Appointment date and time are required")
    @Future(message = "Appointment date and time must be in the future")
    private LocalDate appointmentDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getDoctorExpertise() {
        return doctorExpertise;
    }

    public void setDoctorExpertise(String doctorExpertise) {
        this.doctorExpertise = doctorExpertise;
    }

    public String getDoctorAvailability() {
        return doctorAvailability;
    }

    public void setDoctorAvailability(String doctorAvailability) {
        this.doctorAvailability = doctorAvailability;
    }

    public LocalDate getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDate appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSpecialization='" + doctorSpecialization + '\'' +
                ", doctorExpertise='" + doctorExpertise + '\'' +
                ", doctorAvailability='" + doctorAvailability + '\'' +
                ", appointmentDateTime=" + appointmentDateTime +
                '}';
    }
}
