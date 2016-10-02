/*
 * ���������� �����, ����������� ������ ������� ������, ������ �� �������.
 * ����� getCallerClassAndMethodName() ������ ���������� ��� ������ � ������, 
 * ������ ������ �����, ��������� ������ ��������� �����. ��� null 
 * (������� ������, � �� ������ "null"), ���� �����, ��������� 
 * getCallerClassAndMethodName() �������� ������ ����� � ���������, 
 * �.�. ��� ����� �� �������.
 * 
 * ��� ������� ��� ��������� ������ �������:
 * null
 * org.stepic.java.example.Test#main
 */
package org.stepic.java.example;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Test {
    

    private static void anotherMethod() {
        //System.out.println(getCallerClassAndMethodName());
    	anotherMethod2();
    }
    
    private static void anotherMethod2() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
    	StackTraceElement[] ste = new  Throwable().getStackTrace();
    	//StackTraceElement[] ste = Thread.currentThread().getStackTrace();
    	
    	for (StackTraceElement el : ste) {
    		System.out.println(el.getClassName()+ " " +el.getMethodName());
    	}
    	if (ste.length < 3) return null;
        return ste[2].getClassName() +  "#" + ste[2].getMethodName();
    }
    
    /*
     * ������ � ������ "org.stepic.java.logging.ClassA" �������� ��������� ���� �������.
     * ������ � ������ "org.stepic.java.logging.ClassB" �������� ������ ��������� ������
     *  WARNING � ���������.
     *  ��� ���������, ��������� �� ����������� �������� �� ������� "org.stepic.java", 
     *  ���������� �� ����������� ��������� ���������� � ������� � ������� XML (*) � 
     *  �� ������������ ����������� ������������ �� ������� "org.stepic", "org" � "".
     */
    private static void configureLogging() {
		final Logger LOGGER1 = Logger.getLogger("org.stepic.java.logging.ClassA");
		LOGGER1.setLevel(Level.ALL);
		final Logger LOGGER2 =Logger.getLogger("org.stepic.java.logging.ClassB");
		LOGGER2.setLevel(Level.WARNING);
		
		final Logger LOGGER3 = Logger.getLogger("org.stepic.java");
		ConsoleHandler consoleHandler = new ConsoleHandler();
		LOGGER3.setLevel(Level.ALL);
		consoleHandler.setLevel(Level.ALL);
		XMLFormatter formatter = new XMLFormatter();
		
		LOGGER3.addHandler(consoleHandler);
		consoleHandler.setFormatter(formatter);
		LOGGER3.setUseParentHandlers(false);
	}
    
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        System.out.println("****************************");
        anotherMethod();
    }
}
