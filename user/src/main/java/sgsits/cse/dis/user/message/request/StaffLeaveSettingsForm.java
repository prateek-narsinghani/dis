package sgsits.cse.dis.user.message.request;

import javax.validation.constraints.NotBlank;

public class StaffLeaveSettingsForm {
    
    @NotBlank(message = "max leaves cannot be blank")
    private long maxLeaves;

    public long getMaxLeaves() {
        return maxLeaves;
    }

    public void setMaxLeaves(long maxLeaves) {
        this.maxLeaves = maxLeaves;
    }
}