package genericsColectionsStreams;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  ���������, ������� ��������� �� System.in ������������������ ����� �����,
 *  ����������� ���������, ����� ������ �� ��� ��� �����, ������� �� ������
 *  ��������, � ����� ������� ������������ ������������������ � �������� 
 *  ������� � System.out.
 *  ��� ����� ������� � int. ������� ����� � ������������������ ���������� � ����.
 *  Sample Input:
 *    1 2 3 4 5 6 7
 *  Sample Output: *    
 *    6 4 2
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		int n = 0;
		int num;
		while (sc.hasNextInt()) {
			num = sc.nextInt();
			if (n % 2 != 0) {
				list.add(num);
			}
			n++;
		}
		sc.close();
		for (int i = list.size() - 1; i >= 0; i--) {
			System.out.print(list.get(i) + " ");
		}
		
	}

}
