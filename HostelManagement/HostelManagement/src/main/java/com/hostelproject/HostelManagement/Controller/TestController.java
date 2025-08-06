package com.hostelproject.HostelManagement.Controller;


import com.hostelproject.HostelManagement.Entity.Room;
import com.hostelproject.HostelManagement.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping
    public void test(){
        Room room1=new Room(null,"Non-AC",null,3,0,"F-101");
        Room room2=new Room(null,"Non-AC",null,3,0,"F-102");
        Room room3=new Room(null,"Non-AC",null,3,0,"F-103");
        Room room4=new Room(null,"Non-AC",null,3,0,"F-104");
        Room room5=new Room(null,"Non-AC",null,3,0,"S-201");
        Room room6=new Room(null,"Non-AC",null,3,0,"S-202");
        Room room7=new Room(null,"Non-AC",null,3,0,"S-203");
        Room room8=new Room(null,"Non-AC",null,3,0,"S-204");
        Room room9=new Room(null,"Non-AC",null,3,0,"T-301");
        Room room10=new Room(null,"Non-AC",null,3,0,"T-302");
        Room room11=new Room(null,"Non-AC",null,3,0,"T-303");
        Room room12=new Room(null,"Non-AC",null,3,0,"T-304");
       roomRepository.saveAll(List.of(room1,room2,room3,room4,room5,room6,room7,room8,room9,room10,room11,room12));
    }


}
