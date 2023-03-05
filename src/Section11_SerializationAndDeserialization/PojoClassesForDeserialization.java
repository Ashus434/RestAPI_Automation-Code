package Section11_SerializationAndDeserialization;

public class PojoClassesForDeserialization {

	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private courses courses; // Here the return type is courses type for courses. Because  it has a child pojo class that is courses. And we have written that because there is child Json class called as courses.
	private String linkedIn;
	
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	
	
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	
	
	public courses getCourses() {
		return courses;
	}
	public void setCourses(courses courses) {
		this.courses = courses;
	}
	
	
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
}
