package sgsits.cse.dis.user.service;

import java.util.List;

import org.springframework.stereotype.Component;

import sgsits.cse.dis.user.message.request.ApplyStaffLeaveForm;
import sgsits.cse.dis.user.message.request.StaffLeaveSettingsForm;
import sgsits.cse.dis.user.message.request.UpdateStatusForm;
import sgsits.cse.dis.user.message.response.StaffLeaveLeftResponse;
import sgsits.cse.dis.user.model.StaffLeave;
import sgsits.cse.dis.user.model.StaffLeaveSettings;


@Component
public interface StaffLeaveService {
    
    Long applyLeave(ApplyStaffLeaveForm applyStaffLeaveForm);
    long updateSettings(StaffLeaveSettingsForm staffLeaveSettingsForm);
    StaffLeaveSettings getSettings();
    List<StaffLeave> getLeavesByStatus(String status);
    void updateStatusByLeaveId(UpdateStatusForm updateStatus);
    List<StaffLeaveLeftResponse> getLeaveLeft(String userName);
    List<StaffLeave> getAllLeavesByName(String name);
}