
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;



public class MyTest {
	
	
	
	public static void main(String[] args) {
		System.out.println('\t' + '\u0003');
		int value = Math.abs(-4);
		System.out.println((value & (value - 1)) == 0 && value != 0);
		
		System.out.println((value%2 == 0 && value != 0) || value == 1 || value == -1);
		int a = 32;
		char c = (char) ('\\' + a);
		System.out.println(c);
		System.out.println((1 << (3 - 1)) ^ 15);
		String str1 = "abcd_-,dcba";
		String str2 = str1.replaceAll("[\\W_]", "");
		StringBuilder sb = new StringBuilder(str2).reverse();
		System.out.println(sb);
		System.out.println(str2);
		System.out.println(str2.equalsIgnoreCase(sb.toString()));
		int n = 3;
		BigInteger bi = BigInteger.ONE;
		for (int i = 1; i <= n; i++) {
			bi = bi.multiply(new BigInteger(Integer.toString(i)));			
		}
		System.out.println(bi.toString());
		System.out.println();
		
		int[] a1 = {0, 2, 2, 10,10,20};
		int[] a2 = {1, 3, 5, 7, 8,10,10};
		int[] a3 = new int[a1.length + a2.length];
		int index1 = 0, index2 = 0;
		for (int i= 0; i < a3.length; i++) {
			System.out.print("i: " + i);	
			
			if (a1[index1] <= a2[index2]) {
				System.out.println("  a1: " + index1);
				a3[i] = a1[index1];				
				index1++;				
			}			
			else if (a1[index1] > a2[index2]) {
				System.out.println("   a2: " + index2);
				a3[i] = a2[index2];
				index2++;
			}		
			
			if (index1 >= a1.length) {
				System.arraycopy(a2, index2, a3, i + 1, a2.length - index2);
				break;
			}
			
			if (index2 >= a2.length) {
				System.arraycopy(a1, index1, a3, i + 1, a1.length - index1);
				break;
			}
			
		}
		System.out.println(Arrays.toString(a3));
	}
	
}
