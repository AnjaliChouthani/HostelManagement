package com.hostelproject.HostelManagement.Repository;

import com.hostelproject.HostelManagement.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Optional<Room> findByRoomNumber(String roomNumber);
}
