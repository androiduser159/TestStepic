package inoutfilesystem;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Test {

	/*
	 * метод, читающий входной поток и вычисляющий контрольную сумму 
	 * прочитанных данных.
	 * Контрольная сумма представляет собой число типа int. 
	 * Контрольная сумма пустого набора данных равна нулю.
	 * Контрольная сумма непустого набора данных вычисляется по следующей 
	 * рекуррентной формуле: Cn+1=rotateLeft(Cn) xor bn+1 , где Cn — контрольная 
	 * сумма первых n байт данных, rotateLeft — циклический сдвиг бит числа на 
	 * один бит влево (чтобы не изобретать велосипед, используйте Integer.rotateLeft),
	 *  bn — n-ный байт данных.
	 *  Пример
	 *  На вход подан InputStream, последовательно возвращающий три байта: 
	 *  0x33 0x45 0x01. В качестве контрольной суммы должно быть возвращено число 71.
	 */
	public static int checkSumOfStream(InputStream inputStream) throws IOException {
		int result = 0;
		int read = inputStream.read();
		while (read >= 0) {
			System.out.print((byte) read + " ");
			result = Integer.rotateLeft(result, 1) ^ read;
			read = inputStream.read();
		}
		System.out.print(result + " ");
		return result;
	}
	/*
	 *  метод, который зачитает данные из InputStream и преобразует их в строку,
	 *  используя заданную кодировку. 
	 *  InputStream последовательно возвращает четыре байта: 48 49 50 51.
	 *  Метод, вызванный для такого InputStream и кодировки ASCII, должен вернуть строку "0123".
	 */
	public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
		String result= "";
		Reader br = new InputStreamReader(inputStream, charset);
		int read = br.read();
		while (read >= 0) {
			result += (char)read;
			read = br.read();
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		byte[] data = {0x33, 0x45, 0x01};
		InputStream inputStream = new ByteArrayInputStream(data);
		Test test = new Test();
		test.checkSumOfStream(inputStream);

		System.out.println(Arrays.toString("1".getBytes("UTF-8")));
		
		byte[] data1 = {48, 49, 50, 51};
		InputStream inputStream1 = new ByteArrayInputStream(data1);
		System.out.println(readAsString(inputStream1, StandardCharsets.US_ASCII));
		
		byte[] data2 = {65};
		InputStream inputStream2 = new ByteArrayInputStream(data2);
		System.out.println(readAsString(inputStream2, StandardCharsets.US_ASCII));
	}

}
