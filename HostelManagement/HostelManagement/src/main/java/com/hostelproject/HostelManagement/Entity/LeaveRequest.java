package com.hostelproject.HostelManagement.Entity;
import com.hostelproject.HostelManagement.Entity.LeaveStatus;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@JsonBackReference
     @ManyToOne
      @JoinColumn(name="student_id")
    private Student student;
@Enumerated(EnumType.STRING)
   private LeaveStatus leaveStatus;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;


    public LeaveRequest(Long id, Student student, LeaveStatus leaveStatus, String reason, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.student = student;
        this.leaveStatus = leaveStatus;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LeaveRequest() {
    }

    public LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
