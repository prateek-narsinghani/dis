package sgsits.cse.dis.user.repo;

import java.util.List;

// import com.google.common.base.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sgsits.cse.dis.user.model.StaffLeave;

@Repository
public interface StaffLeaveRepository extends CrudRepository<StaffLeave, Long> {
    
    StaffLeave findByLeaveId(Long leaveId);
    StaffLeave findByAppliedBy(String appliedBy);
}