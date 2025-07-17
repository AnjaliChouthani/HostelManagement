package com.hostelproject.HostelManagement.Repository;


import com.hostelproject.HostelManagement.Entity.ComplaintMaintainece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface  ComplaintRepository extends JpaRepository<ComplaintMaintainece,Long> {


}
