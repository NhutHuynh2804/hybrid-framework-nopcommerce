package javaBasic;
import java.util.Scanner;

import org.testng.annotations.Test;
public class Topic_08_LoopStatement_for_foreach_Exercise {
	Scanner scanner = new Scanner(System.in);

	
	public void TC01() {
		int number = scanner.nextInt();
		for (int num=1; num<=number;num++) {
			System.out.print(" "+num);
		}
	}
	
	
	public void TC02() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();

		for (int num=numberA; num<=numberB;num++) {
			System.out.print(" "+num);
		}
	}
	
	
	public void TC03() {
		int tong=0;
		for (int num=1; num<=10;num++) {
			
			if(num%2==0) {
			
				tong=tong+num;

			}
		}		System.out.println("tong la"+tong);

	}
	
	
	public void TC04() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();
		int tong =0;

		for (int num=numberA; num<=numberB;num++) {
			tong+=num;

		}
		System.out.println("tong la"+tong);

	}
	
	
	public void TC05() {
		int numberA = scanner.nextInt();
		int tong =0;

		for (int num=0; num<=numberA;num++) {
			if(!(num%2==0)) {
				tong+=num;

		}
	}
		System.out.println("tong la"+tong);

		

	}
	
	
	public void TC06() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();

		for (int num=numberA; num<=numberB;num++) {
			if(num%3==0) {
				System.out.print(" "+num);

		}
	}

		

	}
	
	@Test
	public void TC07() {
		int number = scanner.nextInt();
		int giaiThua=1;
		for (int num=1; num<=number;num++) {
			giaiThua*=num;

		}
		   System.out.print(" "+giaiThua);

	}			
	
	
	}			

	


