package Utils;

import java.util.Scanner;

public class InputManger {
	private static Scanner sc = new Scanner(System.in);

	public static int getIntVal(int start, int end) {
		while (true) {
			int val = 0;
			try {
				System.out.println(start + " ~ " + end + " 사이 값을 입력하세요.");
				val = sc.nextInt();
				sc.nextLine();
				if (val < start || val > end) {
					System.out.println("다시 입력하세요.");
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				sc.nextLine();
				continue;
			}
			return val;
		}
	}
	
	public static String getStrVal(String showStr) {
		while (true) {
			String val = "";
			try {
				System.out.println(showStr);
				val = sc.next();
				sc.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
				sc.nextLine();
				continue;
			}
			return val;
		}
	}
}
