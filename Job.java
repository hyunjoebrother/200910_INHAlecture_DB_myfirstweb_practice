package examples.dto;

public class Job {
	
	private Integer jobId;
	private String description;
	
	//생성자
	public Job(Integer jobId, String description) {
		super();
		this.jobId = jobId;
		this.description = description;
	}
	
	//각 멤버함수 getter/setter
	public Integer getJobId() {
		return jobId;
	}
	
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	//toString
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", description=" + description + "]";
	}

	
}
