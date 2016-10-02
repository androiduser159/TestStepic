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
	 * �����, �������� ������� ����� � ����������� ����������� ����� 
	 * ����������� ������.
	 * ����������� ����� ������������ ����� ����� ���� int. 
	 * ����������� ����� ������� ������ ������ ����� ����.
	 * ����������� ����� ��������� ������ ������ ����������� �� ��������� 
	 * ������������ �������: Cn+1=rotateLeft(Cn) xor bn+1 , ��� Cn � ����������� 
	 * ����� ������ n ���� ������, rotateLeft � ����������� ����� ��� ����� �� 
	 * ���� ��� ����� (����� �� ���������� ���������, ����������� Integer.rotateLeft),
	 *  bn � n-��� ���� ������.
	 *  ������
	 *  �� ���� ����� InputStream, ��������������� ������������ ��� �����: 
	 *  0x33 0x45 0x01. � �������� ����������� ����� ������ ���� ���������� ����� 71.
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
	 *  �����, ������� �������� ������ �� InputStream � ����������� �� � ������,
	 *  ��������� �������� ���������. 
	 *  InputStream ��������������� ���������� ������ �����: 48 49 50 51.
	 *  �����, ��������� ��� ������ InputStream � ��������� ASCII, ������ ������� ������ "0123".
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
