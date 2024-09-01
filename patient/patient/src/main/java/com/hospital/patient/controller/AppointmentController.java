package com.hospital.patient.controller;

import com.hospital.patient.model.Appointment;
import com.hospital.patient.service.AppointmentService;
import com.hospital.patient.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/bookAppointment")
    public ResponseEntity<Appointment> bookAppointment(@Valid @RequestBody Appointment appointment, BindingResult result) {
        if (result.hasErrors()) {
            throw new  ResourceNotFoundException(result.getAllErrors().toString());
        }
        return ResponseEntity.ok(appointmentService.bookAppointment(appointment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {

            return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable Long id) {
        appointmentService.deleteAppointmentById(id);
        return new ResponseEntity<>("Successfully deleted appointment with Id: "+id, HttpStatus.ACCEPTED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
            return ResponseEntity.ok(appointmentService.updateAppointment(id, appointment));
    }

    @GetMapping("/current-month")
    public ResponseEntity<Iterable<Appointment>> getAppointmentsForCurrentMonth() {
        return ResponseEntity.ok(appointmentService.getAppointmentsForCurrentMonth());
    }
}
