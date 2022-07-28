package javaBasic;
import java.util.Scanner;

import org.testng.annotations.Test;
public class Topic_06_Condition_Statement_Exercise {
	Scanner scanner = new Scanner(System.in);

	
	public void TC01() {
		int num = scanner.nextInt();
		
		if(num %2 == 0) {
			System.out.println(num+" "+"La so chan");
		}
		System.out.println(num+" "+"La so le");


	}
	
	
	public void TC02() {
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		if(a<b) {
			System.out.println(a+" "+"nho hon"+b);
		}else if(a>b) {
			System.out.println(a+" "+"nho hon"+b);
		
		}
		else System.out.println(a+" "+"bang "+b);

		
	}
	
	public void TC03() {
		String nameStudentA = scanner.nextLine();
		String nameStudentB = scanner.nextLine();

		if(nameStudentA.contains(nameStudentB)) {
			System.out.println("Ten A trung ten B");
		
		}
		else System.out.println("Ten A khong trung ten B");


		
	}
	
	public void TC04() {
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();

		if(a>b && a>c) {
			System.out.println(a+" "+"A lon nhat");
		}else if(b>c) {
			System.out.println(b+" "+"B lon nhat");
		
		}
		else System.out.println(c+" "+"C lon nhat ");

		
	}
	
	
	public void TC05() {
		float studentPoint = scanner.nextFloat();
		
		if(0<=studentPoint && studentPoint<=5) {
			System.out.println(studentPoint+" "+"la diem D");
		}else if(5<studentPoint && studentPoint<7.5) {
			System.out.println(studentPoint+" "+"la diem C");

		}
		else if(7.5<studentPoint && studentPoint<8.5) {
			System.out.println(studentPoint+" "+"la diem B");

		}else if(8.5<=studentPoint && studentPoint<=10) {
			System.out.println(studentPoint+" "+"la diem A");
		}
		else System.out.println(studentPoint+" "+"khong hop le");


		
	}
	
	@Test
	public void TC06() {
		int month = scanner.nextInt();
		
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
			System.out.println("thang nay co 31 ngay");
		}else if(month==2) {
			System.out.println("thang nay co 28 ngay");

		}
		else if(month==4||month==6||month==9||month==11) {
			System.out.println("thang nay co 30 ngay");
		
	}
	}
}
