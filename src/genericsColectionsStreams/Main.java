package genericsColectionsStreams;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  программа, которая прочитает из System.in последовательность целых чисел,
 *  разделенных пробелами, затем удалит из них все числа, стоящие на четных
 *  позициях, и затем выведет получившуюся последовательность в обратном 
 *  порядке в System.out.
 *  Все числа влезают в int. Позиции чисел в последовательности нумеруются с нуля.
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
