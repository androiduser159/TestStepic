/*
 * Реализуйте метод, позволяющий другим методам узнать, откуда их вызвали.
 * Метод getCallerClassAndMethodName() должен возвращать имя класса и метода, 
 * откуда вызван метод, вызвавший данный утилитный метод. Или null 
 * (нулевую ссылку, а не строку "null"), если метод, вызвавший 
 * getCallerClassAndMethodName() является точкой входа в программу, 
 * т.е. его никто не вызывал.
 * 
 * При запуске эта программа должна вывести:
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
     * Логгер с именем "org.stepic.java.logging.ClassA" принимал сообщения всех уровней.
     * Логгер с именем "org.stepic.java.logging.ClassB" принимал только сообщения уровня
     *  WARNING и серьезнее.
     *  Все сообщения, пришедшие от нижестоящих логгеров на уровень "org.stepic.java", 
     *  независимо от серьезности сообщения печатались в консоль в формате XML (*) и 
     *  не передавались вышестоящим обработчикам на уровнях "org.stepic", "org" и "".
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
