package com.hostelproject.HostelManagement.Services;


import com.hostelproject.HostelManagement.Entity.ComplaintMaintainece;
import com.hostelproject.HostelManagement.Repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;


    public ResponseEntity<String> createComplaint(ComplaintMaintainece complaintMaintainece) {


        if(complaintMaintainece.getStudent()==null || complaintMaintainece.getIssueType()==null){
            return new ResponseEntity<>("Both student and issue are required ", HttpStatus.BAD_REQUEST);
        }

        complaintMaintainece.setStatus(false);
        complaintMaintainece.setIssueCreated(LocalDate.now());


        complaintRepository.save(complaintMaintainece);
        return new ResponseEntity<>("Complaint Created Sucessfully ",HttpStatus.CREATED);
    }

    public List<ComplaintMaintainece> getAllComplaint() {
        List<ComplaintMaintainece> listOfComplaint=complaintRepository.findAll();
        if(listOfComplaint.isEmpty()){
            return new ArrayList<>();
        }
        return listOfComplaint;

    }
}
