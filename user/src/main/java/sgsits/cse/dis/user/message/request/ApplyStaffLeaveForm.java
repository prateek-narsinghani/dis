package sgsits.cse.dis.user.message.request;

import javax.validation.constraints.NotBlank;

public class ApplyStaffLeaveForm {
    
    private String details;
     
    @NotBlank(message = "From date cannot be blank")
    private String fromDate;

    @NotBlank(message = "To date cannot be blank")
    private String toDate;
    
    private String halfdayFullday;
    
    private String remarks;
    
    private String stauts;
    
    private String subject;
    
    private String typeOfLeave;

    private String appliedBy;

    private String userId;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public String getHalfdayFullday() {
        return halfdayFullday;
    }

    public void setHalfdayFullday(String halfdayFullday) {
        this.halfdayFullday = halfdayFullday;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTypeOfLeave() {
        return typeOfLeave;
    }

    public void setTypeOfLeave(String typeOfLeave) {
        this.typeOfLeave = typeOfLeave;
    }

    public String getAppliedBy() {
        return appliedBy;
    }

    public void setAppliedBy(String appliedBy) {
        this.appliedBy = appliedBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}