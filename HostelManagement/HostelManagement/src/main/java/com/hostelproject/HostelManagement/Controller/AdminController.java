package com.hostelproject.HostelManagement.Controller;


import com.hostelproject.HostelManagement.Entity.ComplaintMaintainece;
import com.hostelproject.HostelManagement.Entity.LeaveRequest;
import com.hostelproject.HostelManagement.Entity.Room;
import com.hostelproject.HostelManagement.Entity.Student;
import com.hostelproject.HostelManagement.Services.ComplaintService;
import com.hostelproject.HostelManagement.Services.LeaveService;
import com.hostelproject.HostelManagement.Services.RoomServices;
import com.hostelproject.HostelManagement.Services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    StudentServices studentServices;
      @Autowired
    RoomServices roomServices;

      @Autowired
    LeaveService leaveService;
    //student api's
    @PostMapping("/addStudent")
      public ResponseEntity<String>addStudent(@RequestBody Student student){
           return studentServices.addStudent(student);
      }
    @DeleteMapping("/deleteStudent")
      public ResponseEntity<String>deleteStudent(@RequestParam Long id,@RequestParam String name){
           return studentServices.deleteStudent(id,name);
      }
      @GetMapping("/getAllStudent")
    public ResponseEntity<List<Student>>getAllStudent(){
        return studentServices.getAllStudent();
      }

//      //room API's

    @PostMapping("/addRoom")
    public ResponseEntity<String>AddRoom(@RequestBody Room room){
               return roomServices.AddRoom(room);
    }


    @DeleteMapping("/delRoom")

    public ResponseEntity<String>deleteRoom(@RequestParam String roomNumber){

        return roomServices.deleteRoom(roomNumber);
    }
    //getAllRooms


    @GetMapping("/getAllRoom")
    public List<Room>getAllRoom(){
        return roomServices.getAllRoom();
    }
    //GetAvailable Rooms


    @GetMapping("/getAvailableRoom")
    public List<Room>getAvailableRoom(){
        return roomServices.getAvailableRoom();
    }
    //GetAssignedRooms

    @GetMapping("/getAssignedRoom")
    public List<Room>getAssignedRoom(){
        return roomServices.getAssignedRoom();
    }

    @GetMapping("/getByRoomNo/{roomNumber}")
    public Room getByRoomNumber(@PathVariable String roomNumber){
        return roomServices.getRoomByNo(roomNumber);
    }

    //leave API
      @GetMapping("/getAllLeave")
    public List<LeaveRequest>getAllLeaveRequest(){
        return leaveService.getAllLeaveRequest();
      }

      @GetMapping("getLeaveByName/{rollno}")
    public List<LeaveRequest>getLeaveRequestByName(@PathVariable String rollno){
        return (List<LeaveRequest>) leaveService.getLeaveRequestByRollNo(rollno);
      }


    //update leave status

    @PutMapping("/approvedLeave/{id}")
    public ResponseEntity<?>ApprovedLeaveReq(@PathVariable Long id){
        return leaveService.ApprovedLeaveReq(id);
    }

    @PutMapping("/rejectLeave/{id}")

    public ResponseEntity<?>rejectLeaveReq(@PathVariable Long id){
          return leaveService.rejectLeaveReq(id);
    }

}
