package com.hostelproject.HostelManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rollno;
    private String name;
    private String phoneNo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<ComplaintMaintainece>complaintMaintaineceList;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<LeaveRequest> leaveRequestList;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<ComplaintMaintainece> getComplaintMaintaineceList() {
        return complaintMaintaineceList;
    }

    public void setComplaintMaintaineceList(List<ComplaintMaintainece> complaintMaintaineceList) {
        this.complaintMaintaineceList = complaintMaintaineceList;
    }


    public List<LeaveRequest> getLeaveRequestList() {
        return leaveRequestList;
    }

    public void setLeaveRequestList(List<LeaveRequest> leaveRequestList) {
        this.leaveRequestList = leaveRequestList;
    }


}
