package com.hostelproject.HostelManagement.Services;


import com.hostelproject.HostelManagement.Entity.Room;
import com.hostelproject.HostelManagement.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServices {

    @Autowired
    RoomRepository roomRepository;
    //add room
    public ResponseEntity<String> AddRoom(Room room) {
        //check if room is already in the database then return already exist room
       Optional<Room> roomOptional= roomRepository.findByRoomNumber(room.getRoomNumber());

       if(roomOptional.isPresent()){
           return new ResponseEntity<>("Already Existed the Room Number "+room.getRoomNumber(), HttpStatus.CONFLICT);
       }
        roomRepository.save(room);
        return new ResponseEntity<>("Add Room", HttpStatus.CREATED);
    }
    //delete Room
    public ResponseEntity<String> deleteRoom(String roomNumber) {

       Optional<Room>roomOptional= roomRepository.findByRoomNumber(roomNumber);
       if(!roomOptional.isPresent()){
           return new ResponseEntity<>("Room Number "+roomNumber+" Not Found",HttpStatus.NOT_FOUND);
       }
       Room roomdetails=roomOptional.get();
       if(roomdetails.getStudentList()!=null && !roomdetails.getStudentList().isEmpty()){
           return new ResponseEntity<>("Room has Students ",HttpStatus.BAD_REQUEST);
       }
       roomRepository.delete(roomdetails);
       return new ResponseEntity<>("Delete Room Successfully",HttpStatus.OK);
    }

    public List<Room> getAllRoom() {              //Right

        List<Room>roomList=roomRepository.findAll();
        return roomList.isEmpty()?new ArrayList<>():roomList;
    }

    public List<Room> getAvailableRoom() {
        List<Room>list=roomRepository.findAll();
       return list.stream().filter(room->{return room.getCurrentNumberStudent()<room.getCapacity();}).collect(Collectors.toList());
    }


    public List<Room> getAssignedRoom() {
      List<Room>roomList=   roomRepository.findAll();
    return  roomList.stream().filter(room-> room.getCurrentNumberStudent()>=1 && room.getCurrentNumberStudent()<=room.getCapacity()).collect(Collectors.toList());
    }

    public Room getRoomByNo(String roomNumber) {
       Optional<Room>optionalRoom= roomRepository.findByRoomNumber(roomNumber);
       if(!optionalRoom.isPresent()){
           throw new RuntimeException("Room With "+roomNumber+" is not Found ");
       }
        return optionalRoom.get();
    }
}
