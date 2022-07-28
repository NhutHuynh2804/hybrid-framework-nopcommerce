package javaBasic;
import java.util.Scanner;

import org.testng.annotations.Test;
public class Topic_07_Condition_Statement_Switchcase_Exercise {
	Scanner scanner = new Scanner(System.in);


	public void TC01() {
		int num = scanner.nextInt();
		switch (num) {
		case 1:
		System.out.println("Tieng anh la One"+ num);
			break;
		case 2:
			System.out.println("Tieng anh la Two"+ num);
				break;
		case 3:
			System.out.println("Tieng anh la Three"+ num);
				break;
		case 4:
			System.out.println("Tieng anh la Four"+ num);
				break;
		case 5:
			System.out.println("Tieng anh la Five"+ num);
				break;
		case 6:
			System.out.println("Tieng anh la Six"+ num);
				break;
		case 7:
			System.out.println("Tieng anh la Seven"+ num);
				break;
		case 8:
			System.out.println("Tieng anh la Eight"+ num);
				break;
		case 9:
			System.out.println("Tieng anh la Nine"+ num);
				break;
		case 10:
			System.out.println("Tieng anh la Ten"+ num);
				break;

		default:
			System.out.println("Nhap tu 1-10"+ num);

			break;
		}

	}
	
	@Test
	public void TC02() {
		
		String operator = scanner.nextLine();
	
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		
		switch (operator) {
		case "+":
		System.out.println("Tong 2 so la"+ (a+b));
			break;
		case "-":
			System.out.println("Hieu 2 so la"+ (a-b));
				break;
		case "*":
			System.out.println("Tich 2 so la"+ (a*b));
				break;
		case "/":
			System.out.println("Thuong 2 so la"+ (a/b));
				break;
		case "%":
			System.out.println("Chia lay du la"+ (a%b));
				break;
	}
 }
	@Test
	public void TC03() {
		int month = scanner.nextInt();
		
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
		System.out.println("Thang co 31 ngay la:"+ month);
			break;
		case 2:
			System.out.println("Thang co 28 ngay la:"+ month);
				break;
				
		case 4:
		case 6:
		case 9:
		case 11:
		System.out.println("Thang co 30 ngay la:"+ month);
			break;

		default:
			System.out.println("Nhap thang tu 1-12");

			break;
	}
	
}
}
