package sgsits.cse.dis.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sgsits.cse.dis.user.model.StaffLeaveSettings;

@Repository
public interface StaffLeaveSettingsRepository extends JpaRepository<StaffLeaveSettings,Long> {
    @Modifying
    @Query(
            value = "truncate table staff_leave_settings",
            nativeQuery = true
    )
    void truncateMyTable();

    StaffLeaveSettings findTopByOrderByIdDesc();
}