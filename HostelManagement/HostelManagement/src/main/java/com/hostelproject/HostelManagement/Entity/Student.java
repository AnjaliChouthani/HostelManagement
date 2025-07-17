package com.hostelproject.HostelManagement.Entity;

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
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<ComplaintMaintainece>complaintMaintaineceList;

    public Student(List<ComplaintMaintainece> complaintMaintaineceList) {
        this.complaintMaintaineceList = complaintMaintaineceList;
    }

    public List<ComplaintMaintainece> getComplaintMaintaineceList() {
        return complaintMaintaineceList;
    }

    public void setComplaintMaintaineceList(List<ComplaintMaintainece> complaintMaintaineceList) {
        this.complaintMaintaineceList = complaintMaintaineceList;
    }

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
      private List<LeaveRequest> leaveRequestList;

    public Student(Long id, String rollno, String name, String phoneNo, Room room, List<LeaveRequest> leaveRequestList) {
        this.id = id;
        this.rollno = rollno;
        this.name = name;
        this.phoneNo = phoneNo;
        this.room = room;
        this.leaveRequestList = leaveRequestList;
    }

    public List<LeaveRequest> getLeaveList() {
        return leaveRequestList;
    }

    public void setLeaveList(List<LeaveRequest> leaveRequestList) {
        this.leaveRequestList = leaveRequestList;
    }



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


}
