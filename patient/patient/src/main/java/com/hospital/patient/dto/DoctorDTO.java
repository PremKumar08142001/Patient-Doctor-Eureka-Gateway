package com.hospital.patient.dto;

public class DoctorDTO {
    private Long id;
    private String name;
    private String specialization;
    private String expertise;
    private String availability;

    public DoctorDTO() {}

    public DoctorDTO(String name, String specialization, String expertise, String availability) {
        this.name = name;
        this.specialization = specialization;
        this.expertise = expertise;
        this.availability = availability;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", expertise='" + expertise + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}


