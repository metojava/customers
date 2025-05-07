package com.example.customers;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {

    private @Id @GeneratedValue Long id;

    String fullName;
    String ssn;
    String dateOfBirth;

    public Customer() {
    }

    public Customer( String fullName, String ssn, String dateOfBirth) {
        this.fullName = fullName;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer(Long id, String fullName, String ssn, String dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Customer))
            return false;
        Customer customer = (Customer) o;
        return Objects.equals(this.id, customer.id) && Objects.equals(this.fullName, customer.fullName)
                && Objects.equals(this.ssn, customer.ssn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.fullName, this.ssn);
    }

    @Override
    public String toString() {
        return "Customer {" + "id=" + this.id + ", full name='" + this.fullName + '\'' + 
          ", Social Security Number = '" + this.ssn + '\'' + ", Date of Birth = '" + this.dateOfBirth + '\'' +'}';
    }
}