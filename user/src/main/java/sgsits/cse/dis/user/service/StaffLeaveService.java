package sgsits.cse.dis.user.service;

import org.springframework.stereotype.Component;

import sgsits.cse.dis.user.message.request.ApplyStaffLeaveForm;
import sgsits.cse.dis.user.message.request.StaffLeaveSettingsForm;
import sgsits.cse.dis.user.model.StaffLeaveSettings;


@Component
public interface StaffLeaveService {
    
    Long applyLeave(ApplyStaffLeaveForm applyStaffLeaveForm);
    long updateSettings(StaffLeaveSettingsForm staffLeaveSettingsForm);
    StaffLeaveSettings getSettings();
}