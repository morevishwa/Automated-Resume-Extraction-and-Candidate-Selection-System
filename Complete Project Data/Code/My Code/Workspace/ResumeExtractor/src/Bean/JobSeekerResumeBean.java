package Bean;

public class JobSeekerResumeBean {
	private String name;
	private String resume_name;
	private String resume_Location;
	private String email_ID;
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JobSeekerResumeBean() {

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResume_name() {
		return resume_name;
	}
	public void setResume_name(String resume_name) {
		this.resume_name = resume_name;
	}
	public String getResume_Location() {
		return resume_Location;
	}
	public void setResume_Location(String resume_Location) {
		this.resume_Location = resume_Location;
	}
	public String getEmail_ID() {
		return email_ID;
	}
	public void setEmail_ID(String email_ID) {
		this.email_ID = email_ID;
	}
	
}
