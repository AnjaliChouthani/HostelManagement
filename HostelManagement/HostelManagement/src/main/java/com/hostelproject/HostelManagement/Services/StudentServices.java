package com.hostelproject.HostelManagement.Services;

import com.hostelproject.HostelManagement.Entity.Room;
import com.hostelproject.HostelManagement.Entity.Student;
import com.hostelproject.HostelManagement.Repository.RoomRepository;
import com.hostelproject.HostelManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {

    @Autowired
    private RoomServices roomServices;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    public ResponseEntity<String> addStudent(Student student) throws Exception {
        //incomplete as when student is created then room should assign  it
        //check room avaialable and then assign it
       List<Room>roomList= roomServices.getAvailableRoom();

            if(roomList.isEmpty()){
                throw new Exception("All the Rooms are Allocated");
            }

           Room assignedRoom= roomList.get(0);
            //set student
         student.setRoom(assignedRoom);
           Student savedStudent=studentRepository.save(student);
           //update
        assignedRoom.setCurrentNumberStudent(assignedRoom.getCurrentNumberStudent()+1);
        roomRepository.save(assignedRoom);

        studentRepository.save(student);
        return new ResponseEntity<>("Save Student Successfully with Room Number "+assignedRoom.getRoomNumber(), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteStudent(Long id, String name) {
        //check name exits and id
      Optional<Student> studentDetails=studentRepository.findById(id);
      if(!studentDetails.isPresent()){           //check in database the id is exist or not
          return new ResponseEntity<>("Not Found Student "+ id,HttpStatus.NOT_FOUND);
      }

      Student student=studentDetails.get();

      if(!student.getName().equalsIgnoreCase(name)){
            return new ResponseEntity<>("Student "+ name+" Not Found ",HttpStatus.NOT_FOUND);
      }
     Room room= student.getRoom();
        if(room!=null){
            //Remove from Student List
            List<Student> studentList=room.getStudentList();
            studentList.remove(student);
            room.setStudentList(studentList);
            //decrement the count
              int updatedStudent=room.getCurrentNumberStudent()-1;
                room.setCurrentNumberStudent(updatedStudent);
            //break the relation of student from room
            student.setRoom(null);
             roomRepository.save(room);
        }
        studentRepository.delete(student);
return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }
//get service method
    public ResponseEntity<List<Student>> getAllStudent() {
         List<Student>optionalList= studentRepository.findAll();
         if(optionalList.isEmpty())  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         return new ResponseEntity<>(optionalList,HttpStatus.OK);
    }
}
