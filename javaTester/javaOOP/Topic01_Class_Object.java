package javaOOP;

public class Topic01_Class_Object {
	private int id;
	private float pointKnowledge;
	private float pointPractice;
	private String fullName;
	
	protected Topic01_Class_Object(String fullName, int iD, float pointKnowledge, float pointPractice) {
		super();
		this.fullName = fullName;
		this.id = iD;
		this.pointKnowledge = pointKnowledge;
		this.pointPractice = pointPractice;
	}
	
	private int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	private float getPointKnowledge() {
		return pointKnowledge;
	}

	private void setPointKnowledge(float pointKnowledge) {
		this.pointKnowledge = pointKnowledge;
	}

	private float getPointPractice() {
		return pointPractice;
	}

	private void setPointPractice(float pointPractice) {
		this.pointPractice = pointPractice;
	}

	private String getFullName() {
		return fullName;
	}

	private void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	private  float getAvergarePoint () {
		return (this.pointKnowledge+this.pointPractice*2)/3;
	}
	
	private void showStudentInfo() {
		System.out.println("*****************************");
		System.out.println("StudentID"+getId());
		System.out.println("StudentName"+getFullName());
		System.out.println("StudentKnowledge"+getPointKnowledge());
		System.out.println("StudentPractice"+getPointPractice());
		System.out.println("Studentaverage"+getAvergarePoint());
		System.out.println("*****************************");
	}
	
	public static void main(String[] args) {
		Topic01_Class_Object student1= new Topic01_Class_Object("Nhut test", 123242, 7.0f, 8.0f);
		student1.showStudentInfo();
	}

}
