package sgsits.cse.dis.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

// import org.springframework.data.annotation.CreatedDate;

@Entity
public class StaffLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,unique = true)
    private Long leaveId;

    private String createdDate;
    
    private String modifiedBy;
    
    private String modifiedDate;
    
    @Column(nullable = false)
    private String appliedBy;
    
    private String details;
    
    @Column(nullable = false)
    private String fromDate;
    
    @Column(nullable = false)
    private String toDate;

    private String halfdayFullday;
    
    private String remarks;
    
    private String status;
    
    private String subject;
    
    private String typeOfLeave;

    private String userId;
    
    public StaffLeave(){}

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getAppliedBy() {
        return appliedBy;
    }

    public void setAppliedBy(String appliedBy) {
        this.appliedBy = appliedBy;
    }

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

    @Override
    public String toString() {
        return "StaffLeave [appliedBy=" + appliedBy + ", createdDate=" + createdDate + ", details=" + details
                + ", fromDate=" + fromDate + ", halfdayFullday=" + halfdayFullday + ", leaveId=" + leaveId
                + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", remarks=" + remarks + ", status="
                + status + ", subject=" + subject + ", toDate=" + toDate + ", typeOfLeave=" + typeOfLeave + "]";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}