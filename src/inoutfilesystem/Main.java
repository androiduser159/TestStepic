
package inoutfilesystem;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	
	/*
	 * На Unix-системах конец строки обозначается символом с кодом 10 ('\n'), 
	 * на Windows — двумя последовательными символами с кодами 13 и 10 ('\r' '\n').
	 * программа, которая будет преобразовывать переводы строк из формата Windows 
	 * в формат Unix. 
	 * Данные в формате Windows подаются программе в System.in, преобразованные 
	 * данные должны выводиться в System.out.
	 * Требуется заменить все вхождения пары символов '\r' и '\n' на один символ '\n'.
	 * Если на входе встречается одиночный символ '\r', за которым не следует '\n',
	 * то символ '\r' выводится без изменения.
	 * Кодировка входных данных такова, что символ '\n' представляется байтом 10, 
	 * а символ '\r' — байтом 13. Поэтому программа может осуществлять фильтрацию 
	 * на уровне двоичных данных, не преобразуя байты в символы.
	 * Пример
	 * Из System.in зачитаны следующие байты: 65 13 10 10 13.
	 * В System.out должны быть выведены байты: 65 10 10 13
	 */
	public static void processText(InputStream inputStream) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		BufferedOutputStream out = new BufferedOutputStream(System.out);
		int next = 0;
		int prev = br.read();
		if (prev < 0) next = -1;
		
		while (next >= 0) {
			next = br.read();
			if (next >= 0) {
				if (prev == 13 && next == 10) {
					prev = next;
				}				
				else {
					System.out.print(prev + " ");
					out.write(prev);
					prev = next;
				}
			}
			else {
				if (!(prev == 13 && next == 10)) {
					System.out.print(prev + " ");
					out.write(prev);
				}
			}
			
		}
		out.flush();
	}
	
	
	/*
	 * Напишите программу, читающую текст из System.in и выводящую в System.out 
	 * сумму всех встреченных в тексте вещественных чисел с точностью 
	 * до шестого знака после запятой. Числом считается последовательность символов,
	 * отделенная от окружающего текста пробелами или переводами строк 
	 * и успешно разбираемая методом Double.parseDouble.
	 * Sample Input 1:
	 *   1 2 3
	 * Sample Output 1:
	 *   6.000000
	 * Sample Input 2:
	 *   a1 b2 c3
	 * Sample Output 2:
	 *   0.000000
	 * Sample Input 3:
	 *   -1e3
	 *   18 .111 11bbb
	 * Sample Output 3:
	 *   -981.889000
	 */
	public static void processNumber(InputStream inputStream) {
		Scanner sc = new Scanner(inputStream);
		double num = 0.0;		
		String str;
		while (sc.hasNext()) {
			str = sc.next();
			try {
				num += Double.parseDouble(str);
			} catch (NumberFormatException e) {
				
			}
		}		
		System.out.printf("%.6f", num);
	}
	
	public static void main(String[] args) throws IOException {
		byte[] data = {65,0,13,10,10,13};
		byte[] data1 = {123,0};
		byte[] data2 = {1, 2, 3};
		InputStream inputStream = new ByteArrayInputStream(data);
		InputStream inputStream2 = new ByteArrayInputStream(data2);
		//processText(System.in);
		processText(inputStream);
		//processNumber(inputStream2);
		//processNumber(System.in);
		
		InputStream inputStream3 = new FileInputStream ("text.txt");
		processNumber(inputStream3);
		
		System.out.println();
		String link = "dotwar.com";
		System.out.println(link.endsWith(".com/"));
		String str = "ATGTGTCACTGA";
		System.out.println(str.substring(3, str.length() - 3));
	}
}
