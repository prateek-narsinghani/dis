package sgsits.cse.dis.user.message.response;

import java.util.List;

import sgsits.cse.dis.user.model.StaffProfile;
import sgsits.cse.dis.user.model.StudentProfile;

public class BatchData {
	
	private List<StudentProfile> students;
	private StaffProfile guide,coguide;
	private String session;
	
	public BatchData(List<StudentProfile> students, StaffProfile guide, StaffProfile coguide, String session) {
		super();
		this.students = students;
		this.guide = guide;
		this.coguide = coguide;
		this.session = session;
	}

	public List<StudentProfile> getStudents() {
		return students;
	}

	public void setStudents(List<StudentProfile> students) {
		this.students = students;
	}

	public StaffProfile getGuide() {
		return guide;
	}

	public void setGuide(StaffProfile guide) {
		this.guide = guide;
	}

	public StaffProfile getCoguide() {
		return coguide;
	}

	public void setCoguide(StaffProfile coguide) {
		this.coguide = coguide;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	
	
}
