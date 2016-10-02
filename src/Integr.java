

public class Integr {
	
	public double InFunction(double x) //Подынтегральная функция
	{
	  return Math.sin(x); //Например, sin(x)
	}
	
	public double integrate(double a, double b, int n) {
		
	    double result = 0; 
	    double h = (b - a) / n; //Шаг сетки
	    
	    for(int i = 0; i < n; i++)
	    {
	        result += InFunction(a + h * i) * h;//InFunction(a + h * (i + 0.5)); 
	    }
	    //result *= h;
	    
	    return result;    
	}

	public static void main(String[] args) {

		Integr integr = new Integr();
		double integral = integr.integrate(Math.PI, 2*Math.PI, 10000000);
		System.out.println("The value of the integral is: " + integral);

	}

}
