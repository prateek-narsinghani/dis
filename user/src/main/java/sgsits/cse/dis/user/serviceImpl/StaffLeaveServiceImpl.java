package sgsits.cse.dis.user.serviceImpl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sgsits.cse.dis.user.message.request.ApplyStaffLeaveForm;
import sgsits.cse.dis.user.message.request.StaffLeaveSettingsForm;
import sgsits.cse.dis.user.message.request.UpdateStatusForm;
import sgsits.cse.dis.user.message.response.StaffLeaveLeftResponse;
import sgsits.cse.dis.user.model.StaffLeave;
import sgsits.cse.dis.user.model.StaffLeaveLeft;
import sgsits.cse.dis.user.model.StaffLeaveSettings;
import sgsits.cse.dis.user.model.StaffProfile;
import sgsits.cse.dis.user.repo.StaffLeaveLeftRepository;
import sgsits.cse.dis.user.repo.StaffLeaveRepository;
import sgsits.cse.dis.user.repo.StaffLeaveSettingsRepository;
import sgsits.cse.dis.user.repo.StaffRepository;
import sgsits.cse.dis.user.service.StaffLeaveService;

@Component
public class StaffLeaveServiceImpl implements StaffLeaveService, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private StaffLeaveRepository staffLeaveRepository;

    @Autowired
    private StaffLeaveSettingsRepository staffLeaveSettingsRepository;

    @Autowired
    private StaffLeaveLeftRepository staffLeaveLeftRepository;

    @Autowired
    private StaffRepository staffRepository;

    public long getDays(String fromDate, String toDate) throws ParseException {
        String one = fromDate;
        String two = toDate;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        long diff = myFormat.parse(two).getTime() - myFormat.parse(one).getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
    }

    @Override
    @Transactional
    public Long applyLeave(ApplyStaffLeaveForm applyStaffLeaveForm) {

        String userId = applyStaffLeaveForm.getUserId();
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (!staffLeaveLeftRepository.existsByUserIdAndYear(userId, year)) {
            long maxLeaves = staffLeaveSettingsRepository.findTopByOrderByIdDesc().getMaxLeaves();
            StaffLeaveLeft staffLeaveLeft = new StaffLeaveLeft();
            staffLeaveLeft.setUserId(userId);
            staffLeaveLeft.setYear(year);
            staffLeaveLeft.setLeavesLeft(maxLeaves);
            staffLeaveLeft.setUsername(applyStaffLeaveForm.getAppliedBy());
            staffLeaveLeftRepository.save(staffLeaveLeft);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        StaffLeave staffLeave = new StaffLeave();
        staffLeave.setFromDate(applyStaffLeaveForm.getFromDate());
        staffLeave.setToDate(applyStaffLeaveForm.getToDate());
        staffLeave.setRemarks(applyStaffLeaveForm.getRemarks());
        staffLeave.setCreatedDate(simpleDateFormat.format(new Date()));
        staffLeave.setAppliedBy(applyStaffLeaveForm.getAppliedBy());
        staffLeave.setDetails(applyStaffLeaveForm.getDetails());
        staffLeave.setRemarks(applyStaffLeaveForm.getRemarks());
        staffLeave.setStatus("applied");
        staffLeave.setHalfdayFullday(applyStaffLeaveForm.getHalfdayFullday());
        staffLeave.setSubject(applyStaffLeaveForm.getSubject());
        staffLeave.setTypeOfLeave(applyStaffLeaveForm.getTypeOfLeave());
        staffLeave.setUserId(userId);

        StaffLeave test = staffLeaveRepository.save(staffLeave);
        return test.getLeaveId();
    }

    @Override
    @Transactional
    public long updateSettings(StaffLeaveSettingsForm staffLeaveSettingsForm) {
        StaffLeaveSettings staffLeaveSettings = new StaffLeaveSettings();
        staffLeaveSettings.setMaxLeaves(staffLeaveSettingsForm.getMaxLeaves());
        staffLeaveSettingsRepository.save(staffLeaveSettings);
        return staffLeaveSettings.getMaxLeaves();
    }

    @Override
    public StaffLeaveSettings getSettings() {
        return staffLeaveSettingsRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<StaffLeave> getLeavesByStatus(String status) {
        return staffLeaveRepository.findByStatusIgnoreCase(status);
    }

    @Override
    @Transactional
    public void updateStatusByLeaveId(UpdateStatusForm updateStatus)
    {   
        Long leaveId=updateStatus.getLeaveId();
        String status=updateStatus.getStatus();
        status = status.toLowerCase();
        // System.out.println("days left");
        staffLeaveRepository.updateStatusByLeaveId(leaveId, status);
        if(status.equals("approved"))
        {
            StaffLeave leave = staffLeaveRepository.findByLeaveId(leaveId);
            long days;
            days = 0;
            int year = Calendar.getInstance().get(Calendar.YEAR);
            try {
                days = getDays(leave.getFromDate(), leave.getToDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String userId=leave.getUserId();
            StaffLeaveLeft staffLeaveLeft = staffLeaveLeftRepository.findByUserIdAndYear(userId, year);
            long leavesLeft = staffLeaveLeft.getLeavesLeft() - days;
            // System.out.println("days left"+leavesLeft);
            staffLeaveLeftRepository.updateStaffLeaveLeftInfo(leavesLeft, userId, year);
        }
    }


    public List<StaffLeaveLeftResponse> getLeaveLeft(String userName)
    {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        long leavesLeft;
        List<StaffProfile> staff = staffRepository.findByNameContainingIgnoreCase(userName);
        List<StaffLeaveLeftResponse> res = new ArrayList<StaffLeaveLeftResponse>();
        for(StaffProfile s:staff)
        {
            StaffLeaveLeftResponse r=new StaffLeaveLeftResponse();
            if (!staffLeaveLeftRepository.existsByUserIdAndYear(s.getUserId(),year)) {
                leavesLeft = staffLeaveSettingsRepository.findTopByOrderByIdDesc().getMaxLeaves();
            }
            else{
                StaffLeaveLeft st=staffLeaveLeftRepository.findByUserIdAndYear(s.getUserId(),year);
                leavesLeft=st.getLeavesLeft();
            }
            
            r.setLeavesLeft(leavesLeft);
            r.setUserName(s.getName());
            res.add(r);
        }
        return res;
    }

    public List<StaffLeave> getAllLeavesByName(String name)
    {
        List<StaffLeave> leaves = new ArrayList<StaffLeave>();
        Optional<StaffProfile> s=staffRepository.findByName(name);
        if(s.isPresent())
        {
            StaffProfile st=s.get();
            leaves = staffLeaveRepository.findByUserId(st.getUserId());
        }
        return leaves;
    }
}
