package com.hostelproject.HostelManagement.Services;

import com.hostelproject.HostelManagement.Entity.LeaveRequest;
import com.hostelproject.HostelManagement.Entity.LeaveStatus;
import com.hostelproject.HostelManagement.Entity.Student;
import com.hostelproject.HostelManagement.Repository.LeaveRepository;
import com.hostelproject.HostelManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService{

    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    StudentRepository studentRepository;
    public List<LeaveRequest> getAllLeaveRequest() {
        return leaveRepository.findAll();
    }

    public ResponseEntity<?> getLeaveRequestByRollNo(String rollno) {
      Optional<Student>student= studentRepository.findByRollno(rollno);
      if(!student.isPresent()) {return new ResponseEntity<>("Not Found any User with this roll no", HttpStatus.BAD_REQUEST);}

      Student details=student.get();
      List<LeaveRequest>list=details.getLeaveRequestList();
      return new ResponseEntity<>(list,HttpStatus.OK);
    }

    public ResponseEntity<?> createLeave(LeaveRequest leaveRequest) {
        //get the student from the leaverequest
                Student student= leaveRequest.getStudent();
                //validate student found or not
                Optional<Student> details=studentRepository.findById(student.getId());
                if(!details.isPresent())  {
                    return new ResponseEntity<>("not found student with this Id  ",HttpStatus.NOT_FOUND);
                }
                //if found then check for duplication
        Student s=details.get();
        Long rollno=leaveRequest.getStudent().getId();
       String reason= leaveRequest.getReason();
        LocalDate startDate=leaveRequest.getStartDate();
        LocalDate endDate=leaveRequest.getEndDate();

      Optional<LeaveRequest> optionalLeaveRequest=  leaveRepository.findByReasonAndStartDateAndEndDateAndStudent_Id(reason,startDate,endDate,rollno);
      if(optionalLeaveRequest.isPresent()) {return new ResponseEntity<>("ALready Leave Request is existed with same reason ,same starting and end date ",HttpStatus.NOT_ACCEPTABLE); }
        leaveRequest.setStudent(s);
        leaveRequest.setLeaveStatus(LeaveStatus.PENDING);
      leaveRepository.save(leaveRequest);
      return new ResponseEntity<>("Successfully Created your LeaveRequest ",HttpStatus.CREATED);
    }

    public ResponseEntity<?> ApprovedLeaveReq(Long id) {

        Optional<LeaveRequest> optionalLeaveRequest=leaveRepository.findById(id);

        if (!optionalLeaveRequest.isPresent()){
            return new ResponseEntity<>("Not found leave request with  this leave id ",HttpStatus.NOT_FOUND);
        }
        LeaveRequest leaveReq=optionalLeaveRequest.get();
        if(leaveReq.getLeaveStatus()==LeaveStatus.PENDING|| leaveReq.getLeaveStatus()==LeaveStatus.REJECTED){
            leaveReq.setLeaveStatus(LeaveStatus.APPROVED);
            leaveRepository.save(leaveReq);
            return new ResponseEntity<>("Approved your leave request sucessfully",HttpStatus.OK);
        }

        return new ResponseEntity<>("Leave Status already approved  ",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> rejectLeaveReq(Long id) {
           Optional<LeaveRequest>optionalLeaveRequest=leaveRepository.findById(id);

           if(!optionalLeaveRequest.isPresent()){
               return new ResponseEntity<>("Not found any Leave Request with this id ",HttpStatus.NOT_FOUND);
           }
        LeaveRequest leaveRequest=optionalLeaveRequest.get();
           if(leaveRequest.getLeaveStatus()==LeaveStatus.PENDING|| leaveRequest.getLeaveStatus()==LeaveStatus.APPROVED){
               leaveRequest.setLeaveStatus(LeaveStatus.REJECTED);
               leaveRepository.save(leaveRequest);
               return new ResponseEntity<>("Your Leave is not Approved by Hostel ",HttpStatus.OK);
           }

           return new ResponseEntity<>("Leave is already Rejected by Hostel ",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> studentLeave(String rollno) {
      Optional<Student>optionalStudent=  studentRepository.findByRollno(rollno);
      if(!optionalStudent.isPresent()){
          return new ResponseEntity<>("student with this roll no not found",HttpStatus.NOT_FOUND);
      }
     Student student= optionalStudent.get();
      return new ResponseEntity<>(student.getLeaveRequestList(),HttpStatus.OK);
    }
}

