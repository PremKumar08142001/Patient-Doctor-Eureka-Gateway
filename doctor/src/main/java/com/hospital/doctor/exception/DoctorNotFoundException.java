package com.hospital.doctor.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(Long id) {
        super("Doctor with ID " + id + " not found");
    }

    public DoctorNotFoundException(String specialization) {
        super("No doctors found with specialization " + specialization);
    }
    public DoctorNotFoundException(String errorIn,String message) {
        super(errorIn + message);
    }

}
