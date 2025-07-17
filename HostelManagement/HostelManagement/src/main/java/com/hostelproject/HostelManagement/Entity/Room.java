package com.hostelproject.HostelManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private String roomType;
    @JsonIgnore
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Student> studentList;
    private  int capacity;
    private int currentNumberStudent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentNumberStudent() {
        return currentNumberStudent;
    }

    public void setCurrentNumberStudent(int currentNumberStudent) {
        this.currentNumberStudent = currentNumberStudent;
    }

    public Room() {
    }

    public Room(Long id, String roomType, List<Student> studentList, int capacity, int currentNumberStudent,String roomNumber) {
        this.id = id;
        this.roomType = roomType;
        this.studentList = studentList;
        this.capacity = capacity;
        this.currentNumberStudent = currentNumberStudent;
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


}
