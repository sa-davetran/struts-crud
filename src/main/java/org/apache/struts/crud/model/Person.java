package org.apache.struts.crud.model;

import java.util.Arrays;

/**
 * Models a Person who registers.
 * 
 * Person is Cloneable just an implemention technique for in-memory daos.
 *
 * @author bruce phillips
 * @author antonio sanchez
 */
public class Person implements Cloneable {
    private Integer personId;
    private String firstName;
    private String lastName;
    private String sport;
    private String gender;
    private Country country = new Country("", "");
    private Integer yearOfBirth;
    private Car[] carModels;
    private Pet[] petModels;
    private String email;
    private String phoneNumber;
    
    public Person()  {
    
    }
    
    public Person(Integer id, String firstName, String lastName, String sport, 
                String gender, Country country, Integer yearOfBirth, Car[] carModels, Pet[] petModels,
                String email, String phoneNumber) {
        this.personId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
        this.gender = gender;
        this.country = country;
        this.yearOfBirth = yearOfBirth;
        this.carModels = carModels;
        this.petModels = petModels;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setCarModels(Car[] carModels) {
        this.carModels = carModels;
    }

    public Car[] getCarModels() {
        return carModels;
    }
    
    public void setPetModels(Pet[] petModels) {
        this.petModels = petModels;
    }

    public Pet[] getPetModels() {
        return petModels;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Id: " + getPersonId() + " | "
                + "First Name: " + getFirstName() + " | "
                + " Last Name:  " + getLastName() + " | "
                + " Favorite Sport: " + getSport() + " | "
                + " Gender: " + getGender() + " | "
                + " Country: " + getCountry() + " | "
                + " Year of Birth: " + getYearOfBirth() + " | "
                + " Car models: " + Arrays.asList(getCarModels()) + " | "
                + " Pet models: " + (getPetModels() != null ? Arrays.asList(getPetModels()) : "none") + " | "
                + " Email: " + getEmail() + " | "
                + " Phone: " + getPhoneNumber();
    }
}
