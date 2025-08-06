package com.hostelproject.HostelManagement.Services;


import com.hostelproject.HostelManagement.Entity.ComplaintMaintainece;
import com.hostelproject.HostelManagement.Entity.Student;
import com.hostelproject.HostelManagement.Repository.ComplaintRepository;
import com.hostelproject.HostelManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;
    @Autowired
    StudentRepository studentRepository;
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

    public ComplaintMaintainece updateComplaint(Long id, ComplaintMaintainece upadtedComplaint) {

       ComplaintMaintainece complaint= complaintRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found Complaint  "));


          complaint.setIssueCreated(upadtedComplaint.getIssueCreated());
        complaint.setStatus(upadtedComplaint.getStatus());
        complaint.setIssueType(upadtedComplaint.getIssueType());
        return  complaintRepository.save(complaint);
    }

    public ResponseEntity<String> deleteComplaint(Long id){
       ComplaintMaintainece complaint= complaintRepository.findById(id).orElseThrow(()-> new RuntimeException("No Complaint Found with this id "+id));

      Student student=complaint.getStudent();
        if(!complaint.getStatus()){
              return new ResponseEntity<>("Not resolved issue with id "+id +" issue type "+complaint.getIssueType(),HttpStatus.OK);
        }
        student.getComplaintMaintaineceList().remove(complaint);
        complaintRepository.delete(complaint);
              return new ResponseEntity<>("Succesffully Delated complaint "+ id,HttpStatus.OK);
    }

}
