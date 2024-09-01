package com.hospital.patient.repository;

import com.hospital.patient.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    Iterable<Appointment> findByAppointmentDateTimeBetween(LocalDate startDate, LocalDate endDate);
}
