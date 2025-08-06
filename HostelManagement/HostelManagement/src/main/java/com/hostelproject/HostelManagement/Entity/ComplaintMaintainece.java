package com.hostelproject.HostelManagement.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ComplaintMaintainece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String issueType;
    private boolean status=false;
    private LocalDate issueCreated;
    @ManyToOne
    @JoinColumn(name="student_id")
    @JsonBackReference
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getIssueCreated() {
        return issueCreated;
    }

    public void setIssueCreated(LocalDate issueCreated) {
        this.issueCreated = issueCreated;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ComplaintMaintainece(Long id, String issueType, boolean status, LocalDate issueCreated, Student student) {
        this.id = id;
        this.issueType = issueType;
        this.status = status;
        this.issueCreated = issueCreated;
        this.student = student;
    }

    public ComplaintMaintainece() {
    }
}
