package com.javapractice.java.Entity;

public class Student {
     private int id;
     private String name;
     private String lastname;
     private int age;
     private String deptname;
     private String gender;
     private int joinedYear;
     private String city;

     private int rank;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getDeptname() {
        return deptname;
    }

    public String getGender() {
        return gender;
    }

    public int getJoinedYear() {
        return joinedYear;
    }

    public String getCity() {
        return city;
    }

    public int getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public void setJoinedYear(int joinedYear) {
        this.joinedYear = joinedYear;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Student(int id, String name, String lastname, int age, String deptname, String gender, int joinedYear, String city, int rank) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.deptname = deptname;
        this.gender = gender;
        this.joinedYear = joinedYear;
        this.city = city;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", deptname='" + deptname + '\'' +
                ", gender='" + gender + '\'' +
                ", joinedYear=" + joinedYear +
                ", city='" + city + '\'' +
                ", rank=" + rank +
                '}';
    }
}
