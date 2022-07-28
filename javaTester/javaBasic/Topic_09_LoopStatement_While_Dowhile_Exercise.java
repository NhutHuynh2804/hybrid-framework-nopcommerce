package javaBasic;
import java.util.Scanner;

import org.testng.annotations.Test;
public class Topic_09_LoopStatement_While_Dowhile_Exercise {
	Scanner scanner = new Scanner(System.in);

	public void TC01() {
		int number = scanner.nextInt();
		while (number<=100) {
			if(number%2==0) {
				System.out.println("day so la"+number);
			}
			number++;
		}
	  
	}
	
	
	public void TC01_Dowhile() {
		int number = scanner.nextInt();
		
		do {
			if(number%2==0) {
				System.out.println("day so la"+number);
			}
			number++;
			
		} while (number<=100);

		
	}
	
	public void TC02() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();

		while (numberA<=numberB) {
			if(numberA%3==0 && numberA%5==0) {
				System.out.println("day so la chia het"+numberA);
			}
			numberA++;
		}
	  
	}
	
	
	public void TC02_Do_while() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();

		
		do {
			if(numberA%3==0 && numberA%5==0) {
				System.out.println("day so la"+numberA);
			}
			numberA++;
			
		} while (numberA<=numberB);
	  
	}
	
	
	public void TC03() {
		int tong=0,num=0;
		int numberA = scanner.nextInt();

		while(num<=numberA) {
			
			if(!((num%2)==0)) {
			
				tong=tong+num;

			}
			num++;
		}		System.out.println("tong la"+tong);

	}
	
	
	public void TC03_Dowhile() {
		int tong=0,num=0;
		int numberA = scanner.nextInt();
		do {
			if(!((num%2)==0)) {
				
				tong=tong+num;
			}
			num++;
	        
		}while(num<=numberA);
		System.out.println("tong la"+tong);
	}
	
	
	public void TC04() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();

		while (numberA<=numberB) {
			if(numberA%3==0) {
				System.out.println("day so la chia het"+numberA);
			}
			numberA++;
		}
	  
		

	}
	
	
	
	public void TC04_Dowhile() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();

		do {
			if(numberA%3==0) {
				System.out.println("day so la chia het"+numberA);
			}
			numberA++;
			
		} while (numberA<=numberB);

	}
	
	
	public void TC05() {
		int numberA = scanner.nextInt();
		int giaithua=1,num=1;
		while (num<=numberA) {
			giaithua*=num;	
			num++;
			}
			System.out.println("giai thua la "+giaithua);

		}
	

	
	public void TC05_Dowhile() {
		int numberA = scanner.nextInt();
		int giaithua=1,num=1;
		do {
			giaithua*=num;	
			num++;
			
		} while (num<=numberA);
		System.out.println("giai thua la "+giaithua);

		}	

	
	
	
	public void TC06() {
		int i=1,tong=0;
		while (i <=10) {
			if(i%2==0) {
				tong+=i;
			}
			i++;
			
		}				System.out.println("tong cac so "+tong);

	
	
	}		
	
	@Test
	public void TC06_Dowhile() {
		int i=1,tong=0;
		
		do {
			if(i%2==0) {
				tong+=i;
			}
			i++;
		} while (i <=10);
		System.out.println("tong cac so "+tong);
		
}
}
	


