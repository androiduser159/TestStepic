/*
 * AsciiCharSequence, реализующий компактное хранение последовательности 
 * ASCII-символов (их коды влезают в один байт) в массиве байт.
 */
import java.util.Arrays;


public class AsciiCharSequence implements CharSequence {
	
	private byte[] arr;

	public AsciiCharSequence(byte[] arr) {
		super();
		this.arr = arr;
	}

	@Override
	public char charAt(int i) {
		return (char) arr[i];
	}

	@Override
	public int length() {
		return arr.length;
	}


	@Override
	public String toString() {
		return new String(arr);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return new AsciiCharSequence(Arrays.copyOfRange(arr, start, end));
	}

	public static void main(String[] args) {
		
	}
}
