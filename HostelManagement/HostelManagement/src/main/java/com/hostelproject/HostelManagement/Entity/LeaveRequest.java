package com.hostelproject.HostelManagement.Entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


     @ManyToOne
      @JoinColumn(name="student_id")
    private Student student;


    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;


    public LeaveRequest(Long id, Student student, String reason, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.student = student;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LeaveRequest() {
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
