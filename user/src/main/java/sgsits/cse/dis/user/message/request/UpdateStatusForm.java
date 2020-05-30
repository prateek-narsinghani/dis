package sgsits.cse.dis.user.message.request;

import javax.validation.constraints.NotBlank;

public class UpdateStatusForm {
    
    @NotBlank(message="leaave id cannot be blank")
    Long leaveId;

    @NotBlank(message="status cannot be blank")
    String status;
    
    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}