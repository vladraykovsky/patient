package com.parients.model;

import com.parients.comparator.CommentComparator;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Patient implements Serializable{
   private long patient_id;
   private String name;
   private String surname;
   private String date_of_birth;
   private String country;
   private String state;
   private String address;
   private String sex;

  static private long patient_id_add;

   private Set<Comment> setComment = new TreeSet<>(new CommentComparator());


    public Patient(long patient_id, String name, String surname, String date_of_birth, String country, String state, String address, String sex) {
        this.patient_id = patient_id;
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.state = state;
        this.address = address;
        this.sex = sex;
    }


    public Patient(long patient_id, String name, String surname, String date_of_birth, String country, String state, String address, String sex, Set<Comment> setComment) {
        this.patient_id = patient_id;
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.state = state;
        this.address = address;
        this.sex = sex;
        this.setComment = setComment;
    }

    public Patient(String name, String surname, String date_of_birth, String country, String state, String address, String sex) {
        patient_id_add++;
        this.patient_id=patient_id_add;
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.state = state;
        this.address = address;
        this.sex = sex;
    }

    public Patient(){}




    public long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(long patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Set<Comment> getSetComment() {
        return setComment;
    }

    public void setSetComment(Set<Comment> setComment) {
        this.setComment = setComment;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_id=" + patient_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", setComment=" + setComment +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return patient_id == patient.patient_id &&
                Objects.equals(name, patient.name) &&
                Objects.equals(surname, patient.surname) &&
                Objects.equals(date_of_birth, patient.date_of_birth) &&
                Objects.equals(country, patient.country) &&
                Objects.equals(state, patient.state) &&
                Objects.equals(address, patient.address) &&
                Objects.equals(sex, patient.sex) &&
                Objects.equals(setComment, patient.setComment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(patient_id, name, surname, date_of_birth, country, state, address, sex, setComment);
    }
}
