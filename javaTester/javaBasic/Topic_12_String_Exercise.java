package javaBasic;
import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_12_String_Exercise {
	Scanner scanner = new Scanner(System.in);
	int numArray []= {2,7,6,8,9};
	
	String auto ="Automation Testing 345 Tutorials Online 789";
	
	public void TC01() {
		String autoString= scanner.nextLine();
		 char character[]=autoString.toCharArray();
		 int number=0;
		 for (char c : character) {
			if(c<='Z'&& c>='A') {
				number++;
			}
		}System.out.println("Chu viet hoa:"+number);
	  
	}
	
	
	
	public void TC02() {
		
		boolean compareValue=auto.contains("Testing");
		boolean compareStart=auto.startsWith("Automation");
		boolean compareEnd=auto.endsWith("Online");
		int index= auto.indexOf("Tutorials");
		String replace= auto.replace("Online", "Offline");
		

		 char character[]=auto.toCharArray();
		 int numbera=0;
		 int numberb=0;

		 for (char c : character) {
			if(c=='a') {
				numbera++;
			}
			if(c<='9'&& c>='0') {
				numberb++;
			}
			
		}System.out.println("So chu a la:"+numbera);
		
		System.out.println("Chuoi co Testing hay khong:"+compareValue);
		System.out.println("Chuoi co bat dau:"+compareStart);
		System.out.println("Chuoi co ket thuc:"+compareEnd);
		System.out.println("Vi tri tu :"+index);
		System.out.println("Chuoi moi"+replace);
		System.out.println("chu so la:"+numberb);	
	
}
	
	public void TC03() {
		String autoString= scanner.nextLine();
		StringBuffer reverse =new StringBuffer(autoString);
		reverse.reverse().toString();
		System.out.print("Chuoi in nguoc lai"+reverse);
	  
	}
	
	@Test
	public void TC04() {
		String autoString= scanner.nextLine();
		char arrayName[]= autoString.toCharArray();
		for(int i=arrayName.length-1;i>=0;i--) {
			System.out.print(arrayName[i]);
		}

	}
	
}


