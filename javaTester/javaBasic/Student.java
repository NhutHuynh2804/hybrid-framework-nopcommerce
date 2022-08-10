package javaBasic;

public class Student {
	
		String name;
		int age;
		int id;
	
	
	
	public Student(String name,int age,int id) {
	
		this.name = name;
		this.age=age;
		this.id=id;
		
		
	}
	
	public void display() {
		System.out.println("Name:"+name);
		System.out.println("Age:"+age);
		System.out.println("ID:"+id);

	}
	public static void main(String[] args) {
		Student[] student = new Student[3];
		student[0]= new Student("Nhut", 20, 234);
		student[1]= new Student("Qui", 40, 21134);
		student[2]= new Student("Tra", 27, 356234);
		
		for(int i =0; i<3;i++) {
			student[i].display();
		}
	}
}

