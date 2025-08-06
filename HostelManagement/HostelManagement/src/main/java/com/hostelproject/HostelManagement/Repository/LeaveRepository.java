package com.hostelproject.HostelManagement.Repository;


import com.hostelproject.HostelManagement.Entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRepository  extends JpaRepository<LeaveRequest,Long> {






    Optional<LeaveRequest> findByReasonAndStartDateAndEndDateAndStudent_Id(String reason, LocalDate startDate, LocalDate endDate, Long rollno);
}
