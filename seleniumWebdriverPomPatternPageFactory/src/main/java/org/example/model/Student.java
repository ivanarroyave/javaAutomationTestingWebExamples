package org.example.model;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String mobile;
    private String dateOfBirthDayInFormat;
    private String dateOfBirthDay;
    private String dateOfBirthMonth;
    private String dateOfBirthYear;
    private String subjects;
    private String hobbies;
    private String picture;
    private String address;
    private String state;
    private String city;

    public Student() {
        this.firstName = "Ivan";
        this.lastName = "Arroyave";
        this.email = "d@gmail.com";
        this.gender = "Male";
        this.mobile = "0123456789";
        this.dateOfBirthDayInFormat = "023";
        this.dateOfBirthDay = "23";
        this.dateOfBirthMonth = "July";
        this.dateOfBirthYear = "2000";
        this.subjects = "Maths";
        this.hobbies = "Music";
        this.picture = "smile-icon.jpg";
        this.address = "Street 123";
        this.state = "Haryana";
        this.city = "Karnal";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDateOfBirthDayInFormat() {
        return dateOfBirthDayInFormat;
    }

    public void setDateOfBirthDayInFormat(String dateOfBirthDayInFormat) {
        this.dateOfBirthDayInFormat = dateOfBirthDayInFormat;
    }

    public String getDateOfBirthDay() {
        return dateOfBirthDay;
    }

    public void setDateOfBirthDay(String dateOfBirthDay) {
        this.dateOfBirthDay = dateOfBirthDay;
    }

    public String getDateOfBirthMonth() {
        return dateOfBirthMonth;
    }

    public void setDateOfBirthMonth(String dateOfBirthMonth) {
        this.dateOfBirthMonth = dateOfBirthMonth;
    }

    public String getDateOfBirthYear() {
        return dateOfBirthYear;
    }

    public void setDateOfBirthYear(String dateOfBirthYear) {
        this.dateOfBirthYear = dateOfBirthYear;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
