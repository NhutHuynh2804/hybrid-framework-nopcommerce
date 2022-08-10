package javaBasic;
import java.util.Scanner;

import org.testng.annotations.Test;
public class Topic_11_Array_Exercise {
	Scanner scanner = new Scanner(System.in);
	int numArray []= {2,7,6,8,9};
	private Object name;
	private Object id;
	private Object age;

	@Test
	public void TC01() {
		int maxNum=0;

		for(int i=0; i<numArray.length;i++) {
			if(maxNum<numArray[i]) {
				maxNum=numArray[i];
			}
		} System.out.println("So max la"+maxNum);

	  
	}
	
	@Test
	public void TC02() {
		System.out.println("Tong la"+(numArray[0]+ numArray[numArray.length-1]));
		
	}
	
	@Test
	public void TC03() {

		for(int i=0; i<numArray.length;i++) {
			if(numArray[i]%2==0) {
				System.out.println("So chan la"+numArray[i]);
			}

	}
	
	}
	
	@Test
	public void TC04() {
		int tong=0;
		for(int i=0; i<numArray.length;i++) {
			if(!(numArray[i]%2==0)&& numArray[i]>0) {
				tong+=numArray[i];
			}
	  

	}			System.out.println("tong so le la:"+tong);

	}
	
	@Test
	public void TC05() {
		int arrnum[] ={3,-7,2,5,9,-6,10,12};
		
		for(int i=0; i<arrnum.length;i++) {
			if(arrnum[i]>=0&&arrnum[i]<=10) {
				System.out.println("So can tim la:"+arrnum[i]);
			}
		}
		
		}
	

	
	@Test
	public void TC06() {
		int arrnum[] ={3,-7,2,5,9,-6,10,12};
		int tong=0;
		for(int i=0; i<arrnum.length;i++) {
			tong+=arrnum[i];
		}System.out.println("So tong la:"+tong);
		System.out.println("Trung binh cong la:"+tong/(arrnum.length));

		
		}

	
	
	
	
	
	public void TC07_Student(Object age) {
		
		class Student{
			String name;
			int age;
			int id;
		}
		
		
		Student(name,age,id); {
			this.name = name;
			this.age=age;
			this.id=id;
			
			
		}
		
	
}

	private void Student(Object string, Object age, Object id) {
		// TODO Auto-generated method stub
		Student[] students= new Student[3];
		
		
	}
}
	


