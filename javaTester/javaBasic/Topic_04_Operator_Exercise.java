package javaBasic;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_04_Operator_Exercise {

	@Test
	public void TC01_swapnumber() {
		
		int a =5;
		int b =6;
		
		a=a+b;
		b=a-b;
		a=a-b;
		System.out.println("A là"+a);
		System.out.println("B là"+b);

		
	}
	
	@Test
	public void TC02_countage() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		String name = scanner.nextLine();
		int age = scanner.nextInt();
		
		System.out.println("After 15 year age of"+name+ "will be" +" "+ (age+15));
	}
	
	@Test
	public void TC03_comparenumber() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		if(a>b) {
			System.out.println("true");
		}else if(a<b) {
			System.out.println("false");

		}
			
		
	}

}
