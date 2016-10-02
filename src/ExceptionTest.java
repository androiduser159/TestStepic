import java.util.logging.Logger;


public class ExceptionTest {
	
	public static double sqrt(double x) {
		if (x < 0.0) {
			throw new java.lang.IllegalArgumentException
				("Expected non-negative number, got " + x);
		}
		return Math.sqrt(x);
	}
	
	

	public static void main(String[] args) {
		System.out.println(sqrt(9));

	}

}
