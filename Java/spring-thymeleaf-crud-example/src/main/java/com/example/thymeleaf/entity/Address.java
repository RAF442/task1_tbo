package com.example.thymeleaf.entity;

import com.example.thymeleaf.validation.StudentFieldType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "address")
@EqualsAndHashCode(of = {"id"})
public class Address {

    @Id
    private String id;

    @Column(name = "zip_code")
    private String zipCode;

    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "fk_student")
    private Student student;

    public void setZipCode(String zipCode) {
        this.zipCode = StudentFieldType.ZIP_CODE.requireValid(zipCode);
    }

    public void setStreet(String street) {
        this.street = StudentFieldType.STREET.requireValid(street);
    }

    public void setNumber(String number) {
        this.number = StudentFieldType.NUMBER.requireValid(number);
    }

    public void setComplement(String complement) {
        this.complement = StudentFieldType.COMPLEMENT.requireValid(complement);
    }

    public void setDistrict(String district) {
        this.district = StudentFieldType.DISTRICT.requireValid(district);
    }

    public void setCity(String city) {
        this.city = StudentFieldType.CITY.requireValid(city);
    }

    public void setState(String state) {
        this.state = StudentFieldType.STATE.requireValid(state);
    }

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
