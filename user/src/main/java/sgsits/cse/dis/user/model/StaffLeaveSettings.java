package sgsits.cse.dis.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// import org.hibernate.annotations.GenericGenerator;

@Entity
public class StaffLeaveSettings {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,unique = true)
    private Long id;
    
    @Column(nullable=false)
    private long maxLeaves;



    public long getMaxLeaves() {
        return maxLeaves;
    }

    public void setMaxLeaves(long maxLeaves) {
        this.maxLeaves = maxLeaves;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}