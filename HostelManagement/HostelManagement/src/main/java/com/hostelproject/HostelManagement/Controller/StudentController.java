package com.hostelproject.HostelManagement.Controller;

import com.hostelproject.HostelManagement.Entity.ComplaintMaintainece;
import com.hostelproject.HostelManagement.Entity.LeaveRequest;
import com.hostelproject.HostelManagement.Entity.Student;
import com.hostelproject.HostelManagement.Services.ComplaintService;
import com.hostelproject.HostelManagement.Services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentC")
public class StudentController {
    @Autowired
    private ComplaintService complaintService;
   @Autowired
    private LeaveService leaveService;

    @PostMapping
    public ResponseEntity<String>createComplaint(@RequestBody ComplaintMaintainece complaintMaintainece){
       return complaintService.createComplaint(complaintMaintainece);
    }
    @GetMapping
    public List<ComplaintMaintainece> getAllStudent(){
        return complaintService.getAllComplaint();
    }
    @PutMapping("/update/{id}")
    public ComplaintMaintainece updateComplaint(@PathVariable Long id,@RequestBody ComplaintMaintainece upadtedComplaint){
     return complaintService.updateComplaint(id,upadtedComplaint);
    }

    @DeleteMapping("/{id}")
    //complaint id
    public ResponseEntity<String>deleteComplaint(@PathVariable Long id){
           return  complaintService.deleteComplaint(id);
    }



    //leave request api's ------ create leave

    @PostMapping("/createLeave")
    public ResponseEntity<?>createLeave(@RequestBody LeaveRequest leaveRequest){
        return leaveService.createLeave(leaveRequest);

    }

    @GetMapping("/GetLeaveByStudent/{rollno}")
    public ResponseEntity<?>studentLeave(@PathVariable String rollno){
        return leaveService.studentLeave(rollno);
    }
}
